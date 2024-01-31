package com.vini.socket.interfaces;


import org.java_websocket.WebSocket;

import com.vini.socket.models.ReceivedMessage;

public interface IMessageQueue {

    void put(WebSocket socket, String message);

    ReceivedMessage take();
}
