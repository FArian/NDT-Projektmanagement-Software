package com.example.farian.ndtapplication.network;

import com.example.farian.ndtapplication.controller.Login;
import com.example.farian.ndtapplication.json.TOJSON;
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

public class Client extends Observable {

    private WebSocketEcho listener;
    private OkHttpClient okHttpClient;
    private static int id;
    public WebSocket webSocket;
    private Login log;
    private JsonNode receiveJsonMessage;
    private String clientStatus;
    private Request request;


    public Client() {
        this.setLog(new Login("ClientLog"));
        this.getLog().getLogin().info("Client started");
        this.setClientStatus("start");

    }



    private class WebSocketEcho extends WebSocketListener {
        private static final int NORMAL_CLOSURE_STATUS = 9999;

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            //super.onOpen(webSocket, response);
            System.out.println("Opening Socket");
            webSocket.send(TOJSON.helloServer().toString() + "\n");
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            // super.onMessage(webSocket, text);
            getLog().getLogin().info("Receive message : " + text + " from server");
            this.converterMessage(text);


        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            super.onMessage(webSocket, bytes);
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            // super.onClosing(webSocket, code, reason);
            webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !");
            System.out.println("Closing : " + code + " / " + reason);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            super.onFailure(webSocket, t, response);
        }

        private void converterMessage(String text) {
            ObjectMapper mapper = new ObjectMapper();
            JsonFactory factory = mapper.getJsonFactory();
            JsonParser jp = null;
            try {
                jp = factory.createJsonParser(text);
                receiveJsonMessage = mapper.readTree(jp);
                this.actorMessage(receiveJsonMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        private synchronized void actorMessage(JsonNode json) {
            String jsonKey = json.fields().next().getKey();
            switch (jsonKey) {
                case "Hello":
                    getWebSocket().send(TOJSON.helloServer().toString() + "\n");
                    break;
            }
        }
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }


    public WebSocketEcho getListener() {
        return listener;
    }

    public void setListener(WebSocketEcho listener) {
        this.listener = listener;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Client.id = id;
    }

    public WebSocket getWebSocket() {
        return webSocket;
    }

    public void setWebSocket(WebSocket webSocket) {
        this.webSocket = webSocket;
    }

    public Login getLog() {
        return log;
    }

    public void setLog(Login log) {
        this.log = log;
    }

    public JsonNode getReceiveJsonMessage() {
        return receiveJsonMessage;
    }

    public void setReceiveJsonMessage(JsonNode receiveJsonMessage) {
        this.receiveJsonMessage = receiveJsonMessage;
    }

    public String getClientStatus() {
        return clientStatus;
    }

    public void setClientStatus(String clientStatus) {
        this.clientStatus = "Client status: " + clientStatus;
    }

}

