package com.example.farian.ndtapplication.network;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Observable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * Created by F.Arian on 08.11.17.
 */

public class RgClient extends Observable {

    /**
     * is set true when the player has chosen a valid color
     */
    private boolean isValidColor = false;
    /**
     * is set true when there is a response to the color message
     */
    private boolean hasAnswer = false;
    /**
     * is true in the beginning and switched to false after the first two rounds
     */
    private boolean isGameStart = true;

    /**
     * Variable to check and set if the client is running correctly
     */
    private boolean running;

    private WebSocketEcho listener;
    private OkHttpClient okHttpClient;
    public WebSocket webSocket;
    private Request request;
    private JsonNode receiveMsg;
    private String clientStatus;

    public RgClient(String url) {
        okHttpClient = new OkHttpClient();
        this.listener = new WebSocketEcho();
        this.request = new Request.Builder().url(url).build();
        this.webSocket = okHttpClient.newWebSocket(request, listener);
    }


    private class WebSocketEcho extends WebSocketListener {
        private static final int NORMAL_CLOSURE_STATUS = 9999;

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            System.out.println("Opening Socket");
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            System.out.println("Message :" + text + " erhalten!");
            ObjectMapper mapper = new ObjectMapper();
            JsonFactory factory = mapper.getJsonFactory();
            JsonParser jp = null;
            try {
                jp = factory.createJsonParser(text);
                receiveMsg = mapper.readTree(jp);
                clientMsgActor(receiveMsg);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            super.onMessage(webSocket, bytes);
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !");
            System.out.println("Closing : " + code + " / " + reason);

        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            super.onFailure(webSocket, t, response);
        }
    }


    public synchronized void clientMsgActor(JsonNode json) {
        String jsonKey = json.fields().next().getKey();

        switch (jsonKey) {
            case "Hallo":
                break;
        }
    }

}


