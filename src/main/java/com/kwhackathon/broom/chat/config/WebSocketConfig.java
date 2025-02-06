package com.kwhackathon.broom.chat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
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
    @Value("${spring.rabbitmq.client-username}")
    private String rabbitmqClientUsername;
    @Value("${spring.rabbitmq.client-password}")
    private String rabbitmqClientPassword;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/pub") // 메시지 발행 경로의 prifix설정
                .enableStompBrokerRelay("/topic", "/exchange")
                .setRelayHost(rabbitmqHost)
                .setRelayPort(61613)
                .setVirtualHost(rabbitmqVirtualHost)
                .setSystemLogin(rabbitmqUsername)
                .setSystemPasscode(rabbitmqPassword)
                .setClientLogin(rabbitmqClientUsername)
                .setClientPasscode(rabbitmqClientPassword);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 이거 시큐리티랑 하면 * 안됨
        registry.addEndpoint("/chat").setAllowedOriginPatterns(
                "http://localhost:8080",
                "https://broom.life");
    }
}
