package com.androidjson.firebasegooglelogin_androidjsoncom.connection;

import android.util.Log;

import com.androidjson.firebasegooglelogin_androidjsoncom.json.ToJson;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.Personal;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.dosimeter.FilmBadge;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.dosimeter.TLD;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.processing.Developer;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
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

    private static WebSocket webSocket;
    private Request request;
    private String replayMsg;
    private JsonNode receiveMsg;
    private OkHttpClient client;
    private Log log;
    private static final String TAG = "Client";

    private EchoWebSocketListener listener;
    private String receiveMessage;
    private Gson gson;
    public Personal personal;
    private FilmBadge filmBadge;
    private TLD tld;
    private List<Personal> personals;
    private boolean isNewUser;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public Client() {
        this.setClient(new OkHttpClient());
        setListener(new EchoWebSocketListener(this));
        start();
        gson = new Gson();
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

    public Personal getPersonal() {
        if (personal != null) {
            tld = personal.getTld();
            filmBadge = personal.getFilmBadge();
        }
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public List<Personal> getPersonals() {
        return personals;
    }

    public void setPersonals(List<Personal> personals) {
        this.personals = personals;
    }


    public String getReceiveMessage() {
        return receiveMessage;
    }

    public void setReceiveMessage(String receiveMessage) {
        this.receiveMessage = receiveMessage;
    }


    public void setClient(OkHttpClient okHttpClient) {
        this.client = okHttpClient;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public static String getTAG() {
        return TAG;
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

    public boolean isNewUser() {
        return isNewUser;
    }

    public void setNewUser(boolean newUser) {
        isNewUser = newUser;
    }

    public void start() {
        request = new Request.Builder().url("ws://192.168.178.50:9000/getsocket").build();
        if (request != null && listener != null) {
            setWebSocket(client.newWebSocket(request, listener));
        } else {
            log.e(TAG, "Maybe server is not running");
            client.dispatcher().executorService().shutdown();
        }

    }


    @Override
    public String toString() {
        return "Client[" +
                "webSocket=" + webSocket +
                ", replayMsg='" + replayMsg + '\'' +
                ", receiveMsg=" + receiveMsg +
                ", client=" + client +
                ", log=" + log +
                ", listener=" + listener +
                ", receiveMessage='" + receiveMessage + '\'' +
                ", gson=" + gson +
                ", filmBadge=" + filmBadge +
                ", tld=" + tld +
                ", personal=" + personal +
                ", personals=" + personals +
                ", isNewUser=" + isNewUser +
                "]";
    }

}


final class EchoWebSocketListener extends WebSocketListener {
    Gson gson = new Gson();
    private final int NORMAL_CLOSURE_STATUS = 1000;
    private Client c;

    public EchoWebSocketListener(Client c) {
        this.c = c;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        webSocket.send(ToJson.helloServer().toString() + "\n");
        // webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !");
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        System.out.println("Receive Message in onMessage Client : " + message);
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

    private void messageConvector(String message) {
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getJsonFactory();
        JsonParser jp = null;
        try {
            jp = factory.createJsonParser(message);
            JsonNode jsonNode = mapper.readTree(jp);
            messageActor(jsonNode);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * message Actors management for messages from Client and answer back to client
     *
     * @param json
     */
    private synchronized void messageActor(JsonNode json) throws JsonProcessingException, JsonMappingException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        String jsonKey = json.fields().next().getKey();
        //------------------Message Type ------------------------------
        switch (jsonKey) {
            case "Replay message":
                c.getWebSocket().send(ToJson.message("Start", "I want to start").toString() + "\n");
                break;
            case "Start":
                c.getWebSocket().send(ToJson.message("Client", "Thanks").toString() + "\n");
                /**
                 * send request to receive list of Personals
                 */
                c.getWebSocket().send(ToJson.message("PERSONALSLIST", "I NEED PERSONAL LIST").toString() + "\n");
                break;
            case "Warning answer":
                c.setReceiveMessage(json.get("Warning answer").textValue());
                break;
            case "FilmBadge":
                FilmBadge filmBadge = gson.fromJson(String.valueOf(json), FilmBadge.class);
                break;
            case "developerToReady":

                TypeReference<List<Developer>> developers = new TypeReference<List<Developer>>() {
                };
                List<Developer> developerList = mapper.readValue(String.valueOf(json), developers);
                break;
            case "USER_IS_NEW":
                boolean userIsNew = json.get("USER_IS_NEW").get("Message").asBoolean();
                c.setNewUser(userIsNew);
                if (!userIsNew) {
                    c.setPersonal(gson.fromJson(String.valueOf(json), Personal.class));
                }

                break;
            case "USER_IN_LOBBY":
                Personal per = gson.fromJson(String.valueOf(json.get("USER_IN_LOBBY")), Personal.class);
                c.setPersonal(per);
                /**
                 * send request to receive list of Personals
                 */
                c.getWebSocket().send(ToJson.message("PERSONALSLIST", "I NEED PERSONAL LIST").toString() + "\n");


                System.out.println("Personal in Client E-Mail" + c.getPersonal().getEmail());


                break;
            case "PERSONAL_SEND":
                List<Personal> personalList = gson.fromJson(json.get("PERSONAL_SEND").asText(), new TypeToken<List<Personal>>() {
                }.getType());
                c.setPersonals(personalList);
                break;

        }

    }

}

