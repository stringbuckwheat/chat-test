package com.buckwheat.chat;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	
	private final ChatHandler chatHandler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// CORS: setAllowedOrigins("*"): 도메인이 다른 서버에서도 접속 가능하도록
		registry.addHandler(chatHandler, "ws/chat")
		.setAllowedOrigins("http://localhost:8080"); // 와일드카드 수정 - 보안의 이유
//		.withSockJS()
//		.setClientLibraryUrl("https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.2/sockjs.js");

		// sockjs CDN 주소를 입력해도 무관
        // https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.2/sockjs.js
		// http://localhost:8080/myapp/js/sock-client.js
        
        /*
        Spring Boot에서 CORS 설정 시, .allowCredentials(true)와 .allowedOrigins("*") 동시 설정 불가
        모든 주소를 허용하는 대신 특정 패턴만 허용하는 것으로 적용해야한다고 변동됨.
		http://localhost:8080 또는, IP 주소로 설정
        */
	}
}
