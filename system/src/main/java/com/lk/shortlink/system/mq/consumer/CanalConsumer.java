package com.lk.shortlink.system.mq.consumer;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.lk.shortlink.system.common.enums.VailDateTypeEnum;
import com.lk.shortlink.system.dao.entity.ShortLinkDO;
import com.lk.shortlink.system.mq.entity.CanalMessage;
import io.swagger.models.auth.In;
import lombok.SneakyThrows;
import org.apache.catalina.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.Channel;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.lk.shortlink.system.common.constant.RedisKeyConstant.GOTO_IS_NULL_SHORT_LINK_KEY;
import static com.lk.shortlink.system.common.constant.RedisKeyConstant.GOTO_SHORT_LINK_KEY;

/**
 * mysql更新同步redis RabbitMQ 消费者
 */

@Component
public class CanalConsumer {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @SneakyThrows
    @RabbitListener(queues = {"binlog"}, ackMode = "MANUAL")
    public void businessQueue(@Payload byte[] message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException, IOException {
        try {
            // canal发送到rabbitmq的消息默认为二进制字节流，无法看懂，所以将二进制字节流转换为String类型
            String realMessage = new String(message, StandardCharsets.UTF_8);

            CanalMessage<ShortLinkDO> canalMessage = JSON.parseObject(realMessage, CanalMessage.class);
            System.out.println(realMessage);
            int index = canalMessage.getTable().lastIndexOf("_");

            List<Map<String, Object>> old = canalMessage.getOld();


            if ("t_link".equals(canalMessage.getTable().substring(0, index)) && canalMessage.getType().equals("UPDATE")) {
                List<ShortLinkDO> list = JSON.parseArray(JSON.parseObject(realMessage).getString("data"), ShortLinkDO.class);
                for (ShortLinkDO linkDO : list) {
                    if (checkKey(old, "enable_status")
                            && linkDO.getEnableStatus() == 1
                            && !Objects.equals(linkDO.getEnableStatus(), getKey(old, "enable_status"))) {
                        // 删除进入回收站删除缓存
                        stringRedisTemplate.delete(String.format(GOTO_SHORT_LINK_KEY, linkDO.getFullShortUrl()));

                    } else if (checkKey(old, "enable_status")
                            && linkDO.getEnableStatus() == 0
                            && !Objects.equals(getKey(old, "enable_status"), linkDO.getEnableStatus())) {
                        // 从回收站恢复删除缓存
                        stringRedisTemplate.delete(String.format(GOTO_IS_NULL_SHORT_LINK_KEY, linkDO.getFullShortUrl()));

                    } else if ((checkKey(old, "valid_date_type") && !Objects.equals(getKey(old, "valid_date_type"), linkDO.getValidDateType()))
                            || (checkKey(old, "valid_date") && !Objects.equals(getKey(old, "valid_date"), linkDO.getValidDate()))) {
                        stringRedisTemplate.delete(String.format(GOTO_SHORT_LINK_KEY, linkDO.getFullShortUrl()));

                        if ((checkKey(old, "valid_date")) && (DateUtil.parse(getKey(old, "valid_date").toString())).before(new Date())) {
                            if (Objects.equals(linkDO.getValidDateType(), VailDateTypeEnum.PERMANENT.getType())
                                    || linkDO.getValidDate().after(new Date())) {
                                stringRedisTemplate.delete(String.format(GOTO_IS_NULL_SHORT_LINK_KEY, linkDO.getValidDate()));
                            }
                        }
                    }
                }
            }
            // 手动ack,确认消息已被消费
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            // 宕机的情况
            // 消息重新放回队列
            channel.basicNack(deliveryTag, false, true);
            e.printStackTrace();
        }
    }

    /**
     * 获取修改前数据库的原始值
     *
     * @param old
     * @param key
     * @return
     */
    private Object getKey(List<Map<String, Object>> old, String key) {
        int i;
        for (i = 0; i < old.size(); i++) {
            if (old.get(i).containsKey(key)) {
                break;
            }
        }
        return old.get(i).get(key);
    }

    /**
     * 判断当前的字段是否被修改
     *
     * @param old
     * @param key
     * @return
     */
    private boolean checkKey(List<Map<String, Object>> old, String key) {
        for (Map<String, Object> map : old) {
            if (map.containsKey(key) && map.get(key) != null) {
                return true;
            }
        }
        return false;
    }
}
