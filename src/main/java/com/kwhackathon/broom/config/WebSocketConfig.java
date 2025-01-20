package com.kwhackathon.broom.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//    private final JwtChannelInterceptor jwtChannelInterceptor;

    @Value("${spring.rabbitmq.username}")
    private String rabbitUser;
    @Value("${spring.rabbitmq.password}")
    private String rabbitPw;
    @Value("${spring.rabbitmq.host}")
    private String rabbitHost;
    @Value("${spring.rabbitmq.virtual-host}")
    private String rabbitVh;
    @Value("${spring.rabbitmq.default-port}")
    private int rabbitDefaultPort;
//    @Value("${spring.rabbitmq.port}")
//    private int rabbitPort;
//    @Value("${spring.rabbitmq.user-username}")
//    private String rabbitUser_username;
//    @Value("${spring.rabbitmq.user-password}")
//    private String rabbitUser_password;

    @Value("${app.allowed-origins}")
    private String allowedOrigins;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        List<String> origins = Arrays.asList(allowedOrigins.split(","));

        System.out.println("WebSocket 엔드포인트 등록됨");
        registry.addEndpoint("/chat")
                //.setAllowedOriginPatterns("*")
                .setAllowedOriginPatterns(origins.toArray(new String[0]));
                //.withSockJS();//->ws 가 아닌 http로 테스트가능 (백엔드 한정)
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        //메시지 브로커 설정
        registry.setPathMatcher(new AntPathMatcher("."));

        //sub
        //registry.enableSimpleBroker("/sub"); -> 내장 메시지 브로커


        registry.enableStompBrokerRelay("/topic","/queue", "/exchange", "/amq/topic")
                .setRelayHost(rabbitHost)
                .setRelayPort(rabbitDefaultPort)//rabbitMQ 기본 포트
                .setSystemLogin(rabbitUser)
                .setSystemPasscode(rabbitPw)
//                .setClientLogin(rabbitUser_username)
//                .setClientPasscode(rabbitUser_password)
                .setVirtualHost(rabbitVh); // virtual host 설정 추가


        //client로부터 메시지를 받을 api prefix 설정
        //pub
        registry.setApplicationDestinationPrefixes("/pub");

        // 개인 메시지 전송 경로 설정
        //.setUserDestinationPrefix("/user"); // 개인 대상 경로 설정
    }

    // 인증
//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(jwtChannelInterceptor);
//    }
}
