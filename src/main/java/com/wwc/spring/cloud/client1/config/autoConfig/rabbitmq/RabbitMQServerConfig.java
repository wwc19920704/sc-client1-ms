package com.wwc.spring.cloud.client1.config.autoConfig.rabbitmq;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * #
 * <summary>
 * <p>队列服务器配置</p>
 * </summary>
 * <br/><br/>
 * <hr/>
 * <strong>date: 2020-08-08</strong>
 * <br/>
 *
 * @author wwc
 * @version 1.0
 */
@Configuration
public class RabbitMQServerConfig {


    @Bean
    /**
     * RabbitMQServerConfigProperty类型的bean存在才开始生成bean
     */
    @ConditionalOnBean(RabbitMQServerConfigProperty.class)
    public ConnectionFactory connectionFactory(@Autowired RabbitMQServerConfigProperty
                                                           rabbitMQServerConfigProperty) {
        System.err.println("-------------ConnectionFactory-------->start");
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost(rabbitMQServerConfigProperty.getHost());
        cachingConnectionFactory.setPort(rabbitMQServerConfigProperty.getPort());
        cachingConnectionFactory.setUsername(rabbitMQServerConfigProperty.getUsername());
        cachingConnectionFactory.setPassword(rabbitMQServerConfigProperty.getPassword());
        cachingConnectionFactory.setPublisherConfirms(true);
        cachingConnectionFactory.setPublisherReturns(true);
        cachingConnectionFactory.setRequestedHeartBeat(rabbitMQServerConfigProperty.getHeartbeat());
        System.err.println("-------------ConnectionFactory-------->finish");
        return cachingConnectionFactory;
    }

    @Bean
    @ConditionalOnBean(RabbitMQServerConfigProperty.class)
    public RabbitTemplate rabbitTemplate(ConnectionFactory connection) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connection);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }

    @Bean
    @ConditionalOnBean(RabbitMQServerConfigProperty.class)
    public RabbitAdmin rabbitAdmin(ConnectionFactory connection) {
        return new RabbitAdmin(connection);
    }
}
