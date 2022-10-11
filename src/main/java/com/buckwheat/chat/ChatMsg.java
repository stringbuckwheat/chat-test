package com.buckwheat.chat;

import lombok.Data;

@Data
public class ChatMsg {
    private String roomId;
    private String writer;
    private String message;
}

