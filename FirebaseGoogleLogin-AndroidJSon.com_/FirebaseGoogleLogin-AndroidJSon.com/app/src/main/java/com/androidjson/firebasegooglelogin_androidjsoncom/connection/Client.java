package com.androidjson.firebasegooglelogin_androidjsoncom.connection;
import android.util.Log;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.androidjson.firebasegooglelogin_androidjsoncom.json.*;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * Created by F.Arian on 22.11.17.
 */

public class Client {

    private WebSocket webSocket;
    private String replayMsg;
    private JsonNode receiveMsg;
    private OkHttpClient client;
    private Log log;
    private static final String TAG = "Client";
    private static final int NORMAL_CLOSURE_STATUS = 1000;
    private EchoWebSocketListener listener;
    private boolean isNewUser;


    public Client(){
        this.setClient(new OkHttpClient());
        setListener(new EchoWebSocketListener());
        this.setWebSocket(null);
        this.start();
        isNewUser=true;
    }


    public void setWebSocket(WebSocket webSocket) {
        this.webSocket = webSocket;
    }

    public void setReplayMsg(String replayMsg) {
        this.replayMsg = replayMsg;
    }

    public void setReceiveMsg(JsonNode receiveMsg) {
        this.receiveMsg = receiveMsg;
    }

    public EchoWebSocketListener getListener() {
        return listener;
    }

    public void setListener(EchoWebSocketListener listener) {
        this.listener = listener;

    }
    public void setClient(OkHttpClient okHttpClient) {
        this.client =okHttpClient;
    }

    public void setLog(Log log) {
        this.log = log;
    }


    public static String getTAG() {
        return TAG;
    }

    public static int getNormalClosureStatus() {
        return NORMAL_CLOSURE_STATUS;
    }

    public WebSocket getWebSocket() {
        return webSocket;
    }

    public String getReplayMsg() {
        return replayMsg;
    }

    public JsonNode getReceiveMsg() {
        return receiveMsg;
    }

    public OkHttpClient getClient() {
        return client;
    }

    public Log getLog() {
        return log;
    }
    public void start() {
        Request request = new Request.Builder().url("ws://10.176.87.194:9000/getsocket").build();
        if (request != null && listener != null) {
            WebSocket ws = client.newWebSocket(request, listener);
            webSocket = ws;
        } else {
            log.e(TAG, "Maybe server is not running");
            client.dispatcher().executorService().shutdown();
        }

    }

    public boolean isNewUser() {
        return isNewUser;
    }


    public void setIsNewUser(boolean newUser) {
        isNewUser = newUser;
    }



    private class EchoWebSocketListener extends WebSocketListener {



        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            //webSocket.send(ToJson.helloServer().toString()+ "\n");
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
            this.setReplayMsg(replayMsg);
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

                case "NEW_USER_ANSWER":
                 isNewUser =json.get("NEW_USER_ANSWER").get("Message").booleanValue();
                 setIsNewUser(isNewUser);

            }
        }

    }


}
