package com.buckwheat.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RestController
@RequiredArgsConstructor
public class StompChatController {
	@Autowired
	private final SimpMessagingTemplate template; // 특정 브로커로 메시지 전달
	
	// Cliet가 send할 수 있는 경로
	// stompConfig에서 설정한 applicationDestinationPrefixeds와 @MessageMapping 경로 병합
	// /pub/chat/enter
	@MessageMapping(value = "/chat/enter")
	public void enter(ChatMsg message) {
		// @MessageMapping: 웹소켓으로 들어오는 메시지 발행 처리
		// 클라이언트에서 "/pub/chat/enter"로 발행 요청을 하면
		// "/sub/chat/room/[roomId]로 메시지가 전송됨 - 메시지가 전송되는 값
		
		// 핸들러는 없어도 됨
		log.debug(TeamColor.CSK + "환영 인사!!!");
		
		message.setMessage(message.getWriter() + "님이 입장하였습니다.");
		template.convertAndSend("/sub/chat?roomId=" + message.getRoomId(), message);
		log.debug(TeamColor.CSK + "message: " + message);
		
	}
	
	@MessageMapping(value="/chat/message")
	public void message(ChatMsg message) {
		log.debug(TeamColor.CSK + "일반 메시지");
		template.convertAndSend("/sub/chat?roomId=" + message.getRoomId(), message);
		log.debug(TeamColor.CSK + "message: " + message);
	}
}
