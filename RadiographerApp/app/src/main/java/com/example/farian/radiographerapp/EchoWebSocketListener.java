package com.example.farian.radiographerapp;


import com.example.farian.radiographerapp.json.ToJson;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * Created by F.Arian on 08.11.17.
 */

public class EchoWebSocketListener extends WebSocketListener {

    private static final int NORMAL_CLOSURE_STATUS = 1000;
    private WebSocket webSocket;
    private String replayMsg;
    private JsonNode receiveMsg;
    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        this.webSocket=webSocket;
        webSocket.send(ToJson.helloServer().toString()+ "\n");
       // webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !");
    }
    @Override
    public void onMessage(WebSocket webSocket, String message) {
        System.out.println("Receive Message in onMessage Client : " +message);
        if (message instanceof String) {
            this.messageConvector(message);
        }
    }
    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {

    }
    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(NORMAL_CLOSURE_STATUS, null);
        webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !");

    }
    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {


    }
    public void setReplayMsg(String replayMsg) {
        this.replayMsg = replayMsg;
    }

    public WebSocket getWebSocket() {
        return webSocket;
    }
    private void messageConvector(String message){
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getJsonFactory();
        JsonParser jp = null;
        try {
            jp = factory.createJsonParser(message);
            receiveMsg = mapper.readTree(jp);
            messageActor(receiveMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * message Actors management for messages from Client and answer back to client
     *
     * @param json
     */
    private synchronized void messageActor(JsonNode json) {
        String jsonKey = json.fields().next().getKey();
        //------------------Message Type ------------------------------
        switch (jsonKey) {
            case "Replay message":
                webSocket.send(ToJson.message("Start","I want to start").toString() +"\n");
                break;
            case "Start":
                webSocket.send(ToJson.message("Client","Thanks").toString() +"\n");
                break;
        }
    }


}
