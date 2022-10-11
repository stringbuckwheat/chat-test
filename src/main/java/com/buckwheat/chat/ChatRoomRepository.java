package com.buckwheat.chat;

import java.util.*;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

@Repository
public class ChatRoomRepository {
	private Map<String, ChatRoom> chatRoomMap;
	
	// 의존성 주입이 이루어진 후 초기화를 수행
	@PostConstruct
	private void init() {
		chatRoomMap = new LinkedHashMap<>();
	}
	
	public List<ChatRoom> findAllRooms(){
		List<ChatRoom> result = new ArrayList<>(chatRoomMap.values());
		
		// 가짜 데이터
		ChatRoom chatRoom = new ChatRoom();
		chatRoom.setName("첫번째 채팅방");
		chatRoom.setRoomId("1");
		
		result.add(chatRoom);
		
		Collections.reverse(result); // create_date DESC
		
		return result;
	}
	
	public ChatRoom findRoomById(String id) {
		return chatRoomMap.get(id);
	}
	
	public ChatRoom createChatRoom(String name) {
		ChatRoom room = ChatRoom.create(name);
		chatRoomMap.put(room.getRoomId(), room);
		
		return room;
	}
}
