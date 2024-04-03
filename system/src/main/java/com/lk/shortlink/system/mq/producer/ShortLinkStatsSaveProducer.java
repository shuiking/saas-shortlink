package com.lk.shortlink.system.mq.producer;

import cn.hutool.core.util.RandomUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 短链接监控状态保存消息队列生产者
 */

@Component
public class ShortLinkStatsSaveProducer {
    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送延迟消费短链接统计
     */
    public void send(String exchange, String routingKey, Map<String, String> producerMap) {
        rabbitTemplate.convertAndSend(exchange, routingKey, producerMap);
    }
}
