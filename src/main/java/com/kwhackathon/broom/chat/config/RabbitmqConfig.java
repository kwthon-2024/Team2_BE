package com.kwhackathon.broom.chat.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
// import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
    private static final String CHATTING_QUEUE_NAME = "chatting.queue";
    private static final String CHATTING_EXCHANGE_NAME = "chatting.exchange";
    private static final String CHATTING_ROUTING_KEY = "chatting.routtingkey";

    @Value("${spring.rabbitmq.host}")
    private String rabbitmqHost;
    @Value("${spring.rabbitmq.port}")
    private int rabbitmqPort;
    @Value("${spring.rabbitmq.virtual-host}")
    private String rabbitmqVirtualHost;
    @Value("${spring.rabbitmq.username}")
    private String rabbitmqUsername;
    @Value("${spring.rabbitmq.password}")
    private String rabbitmqPassword;

    // 스프링 빈으로 큐를 생성
    @Bean
    public Queue chattingQueue() {
        // auto delete를 true로 하면 메시지가 발생하면 다시 큐가 생기나?
        return new Queue(CHATTING_QUEUE_NAME, true);
    }

    // 스프링 빈으로 Exchange를 생성
    @Bean
    // public TopicExchange chattingExchange() {
    // // auto delete를 true로 하면 메시지가 발생하면 다시 exchange가 생기나?
    // return new TopicExchange(CHATTING_EXCHANGE_NAME);
    // }
    public DirectExchange chattingExchange() {
        return new DirectExchange(CHATTING_EXCHANGE_NAME);
    }

    // 큐와 exchange를 라우팅 키로 바인딩하고 바인딩 객체를 생성
    @Bean
    public Binding chattingBinding() {
        return BindingBuilder.bind(chattingQueue()).to(chattingExchange()).with(CHATTING_ROUTING_KEY);
    }

    // RabbitMQ 연결을 위한 ConnectionFactory 빈을 생성하여 반환
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(rabbitmqHost);
        connectionFactory.setPort(rabbitmqPort);
        connectionFactory.setVirtualHost(rabbitmqVirtualHost);
        connectionFactory.setUsername(rabbitmqUsername);
        connectionFactory.setPassword(rabbitmqPassword);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // JSON 형식의 메시지를 직렬화하고 역직렬할 수 있도록 설정
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    // jackson으로 메시지를 json으로 변환하는 converter를 생성
    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
