package com.lk.shortlink.system.common.constant;

/**
 * RabbitMQ 常量类
 */

public class RabbitMQConstant {
    /**
     * 短链接状态交换机
     */
    public static final String STATS_EXCHANGE_NAME = "stats-exchange";

    /**
     * 短链接状态队列
     */
    public static final String STATS_QUEUE_NAME = "stats-queue";

    /**
     * 短链接状态路由键
     */
    public static final String STATS_ROUTING_KEY = "stats-routingKey";

    /**
     * 短链接状态死信交换机
     */
    public static final String DEAD_STATS_EXCHANGE_NAME = "stats-dead-exchange";

    /**
     * 短链接状态死信状态队列
     */
    public static final String DEAD_STATS_QUEUE_NAME = "stats-dead-queue";

    /**
     * 短链接状态死信路由键
     */
    public static final String DEAD_STATS_ROUTING_KEY = "stats-dead-routingKey";

    /**
     * mysql同步交换机
     */
    public static final String SYNCHRONIZATION_EXCHANGE_NAME = "mysql";

    /**
     * mysql同步队列
     */
    public static final String SYNCHRONIZATION_QUEUE_NAME = "binlog";

    /**
     * mysql同步路由键
     */
    public static final String SYNCHRONIZATION_ROUTING_KEY = "mysql-binlog";

    /**
     * mysql同步死信交换机
     */
    public static final String DEAD_SYNCHRONIZATION_EXCHANGE_NAME = "dead-mysql";

    /**
     * mysql同步状态队列
     */
    public static final String DEAD_SYNCHRONIZATION_QUEUE_NAME = "dead-binlog";

    /**
     * mysql同步死信路由键
     */
    public static final String DEAD_SYNCHRONIZATION_ROUTING_KEY = "dead-mysql-binlog";
}
