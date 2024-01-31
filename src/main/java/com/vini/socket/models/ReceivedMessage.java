package com.vini.socket.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.java_websocket.WebSocket;

public class ReceivedMessage {

    public WebSocket socket;
    public StructuredMessage data;

		public boolean valid = false;

    public ReceivedMessage(WebSocket socket, String message) {
        this.socket = socket;

        ObjectMapper oMapper = new ObjectMapper();
        try {
            this.data = oMapper.readValue(message, StructuredMessage.class);
        } catch (JsonProcessingException e) {
            return;
        }
				this.valid = true;
    }
}
