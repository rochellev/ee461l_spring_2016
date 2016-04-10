package com.wifidirect.milan.wifidirect.listeners;

/**
 * Created by milan on 30.11.15..
 * https://github.com/MilanNz/Java-Socket-Client-Server
 */
public interface MessageListener {
    void onMessageReceived(String response);
    void onConnected(boolean isConnected);
}
