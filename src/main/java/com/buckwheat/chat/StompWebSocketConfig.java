package com.buckwheat.chat;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSocketMessageBroker
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
//	@Override
//	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//		registry.addHandler(chatHandler, "/ws/chat")
//        .setAllowedOriginPatterns("http://*:8080", "http://*.*.*.*:8080")
//        .withSockJS()
//        .setClientLibraryUrl("https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.2/sockjs.js");
//        // Spring Boot에서 CORS 설정 시, .allowCredentials(true)와 .allowedOrigins("*") 동시 설정 불가
//	}
//	
//	@Override
//	public void registerStompEndpoints(StompEndpointRegistry registry) {
//		// WebSocket 또는 SockJS Client가 웹소켓 핸드셰이크 커넥션을 생성할 경로
//        registry.addEndpoint("/chat").withSockJS();  
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry config) {
//    	// /chat 경로로 시작하는 STOMP 메세지의 "destination" 헤더는 
//    	// @Controller 객체의 @MessageMapping 메서드로 라우팅된다.
//        config.setApplicationDestinationPrefixes("/chat");
//        
//        // 내장된 메세지 브로커를 사용해 Client에게 Subscriptions, Broadcasting 기능을 제공. 
//        // 또한 /topic, /queue로 시작하는 "destination" 헤더를 가진 메세지를 브로커로 라우팅한다.
//        config.enableSimpleBroker("/topic", "/queue"); 
//    }
	
	
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp/chat")
                .setAllowedOrigins("http://localhost:8080")
                .withSockJS();
    }

    /*어플리케이션 내부에서 사용할 path를 지정할 수 있음*/
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
    	log.debug(TeamColor.CSK + "configureMessageBroker");
    	
    	// Client에서 SEND 요청을 처리
        registry.setApplicationDestinationPrefixes("/pub"); // /topic, /queue
        registry.enableSimpleBroker("/sub");
    }
}
