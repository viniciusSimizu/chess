package com.vini.socket.lib;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vini.socket.enums.GameModeEnum;
import com.vini.socket.enums.MessageTypeEnum;
import com.vini.socket.models.ConnectMessage;
import com.vini.socket.models.ReceivedMessage;

public class GameManager implements Runnable {

    private MessageQueue queue;

    public GameManager(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            ReceivedMessage product = this.queue.take();
            this.handle(product);
        }
    }

    private void handle(ReceivedMessage msg) {
        MessageTypeEnum type;

        try {
            type = msg.data.getMessageType();
        } catch (IllegalArgumentException e) {
            return;
        }

        switch (type) {
            case MATCHING:
                this.matchingHandle(msg);
                break;

            default:
                break;
        }
    }

    private void matchingHandle(ReceivedMessage msg) {
        ObjectMapper oMapper = new ObjectMapper();
        ConnectMessage content;

        try {
            content = oMapper.readValue(msg.data.getContent(), ConnectMessage.class);
        } catch (JsonProcessingException e) {
            return;
        }

        GameModeEnum mode;

        try {
            mode = content.getType();
        } catch (IllegalArgumentException e) {
            return;
        }

        switch (mode) {
            case SOLO:
                break;

            default:
                break;
        }
    }
}
