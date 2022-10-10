package com.buckwheat.chat;

import java.util.*;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ChatHandler extends TextWebSocketHandler{
	private static List<WebSocketSession> list = new ArrayList<>();
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage msg) throws Exception {
		String payload = msg.getPayload(); // 전송되는 데이터. header, meta data를 제외한 순수 데이터
		log.debug(TeamColor.CSK + "payload: " + payload);
		
		for(WebSocketSession wss : list) {
			wss.sendMessage(msg);
		}
	}
	
	// 클라이언트 접속 시 호출되는 메소드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		list.add(session); // 리스트에 세션을 담음
		log.debug(TeamColor.CSK + session + " 클라이언트 접속");
	}
	
	// 클라이언트가 나갔을 시
	@Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        list.remove(session); // 리스트에서 제거
		log.debug(TeamColor.CSK + session + " 클라이언트 접속 해제");
    }

}
