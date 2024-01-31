package com.vini.socket.lib;

import com.vini.socket.interfaces.IMessageQueue;
import com.vini.socket.models.ReceivedMessage;

import org.java_websocket.WebSocket;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MessageQueue implements IMessageQueue {

    private static int MAX_ITEMS = 16;

    private BlockingQueue<ReceivedMessage> queue = new ArrayBlockingQueue<>(MAX_ITEMS);

    @Override
    public void put(WebSocket socket, String message) {
        ReceivedMessage data = new ReceivedMessage(socket, message);

        if (!data.valid) {
            return;
        }

        try {
            this.queue.put(data);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ReceivedMessage take() {
        try {
            return this.queue.take();

        } catch (InterruptedException e) {
            return null;
        }
    }
}
