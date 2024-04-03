package com.lk.shortlink.system.mq.initialize;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import static com.lk.shortlink.system.common.constant.RabbitMQConstant.*;

/**
 * 初始化 RabbitMQ 交换机和队列
 */

@Configuration
public class MQInitializeTask {
    /**
     * 短链接状态交换机
     */
    @Bean
    public DirectExchange shortLinkStatsExchange() {
        return new DirectExchange(STATS_EXCHANGE_NAME);
    }

    /**
     * 短链接状态队列
     */
    @Bean
    public Queue shortLinkStatsQueue() {
        return QueueBuilder.durable(STATS_QUEUE_NAME).build();
    }

    /**
     * 短链接状态队列和交换机绑定
     */
    @Bean
    public Binding ShortLinkStatsBinding() {
        return BindingBuilder
                .bind(shortLinkStatsQueue()).
                to(shortLinkStatsExchange()).
                with(STATS_ROUTING_KEY);
    }

    /**
     * mysql同步交换机
     */
    @Bean
    public Exchange exchange() {
        return new DirectExchange(SYNCHRONIZATION_EXCHANGE_NAME);
    }

    /**
     * mysql同步队列
     */
    @Bean
    public Queue queue() {
        return QueueBuilder.durable(SYNCHRONIZATION_QUEUE_NAME).build();
    }

    /**
     * mysql同步队列与交换机绑定
     */
    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(SYNCHRONIZATION_ROUTING_KEY)
                .noargs();
    }
}
