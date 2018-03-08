package tadas.luksas.services;

import tadas.luksas.beans.Game;
import tadas.luksas.beans.Suvis;
import tadas.luksas.beans.User;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GameServiceImpl implements GameServiceInterface {
    public static final String SERVER_URL = "http://192.168.1.35:8080";
    public static final String CREATE_USER_METHOD_NAME = "/create_user";
//http://localhost:8080/create_user?name=Tomas&email=tomas.puplauskas@gmail.com

    @Override
    public User createUser(String name, String email) {
        String url = SERVER_URL + CREATE_USER_METHOD_NAME + "?name=" + name + "&email=" + email;
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);

            HttpResponse response = client.execute(request);

            String resp = getResponceAsString(response.getEntity().getContent());

            return convertUser(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private User convertUser(String body) throws ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(body);
        if (obj instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) obj;
            String name = (String) jsonObject.get("name");
            String email = (String) jsonObject.get("email");
            String id = (String) jsonObject.get("id");
            User user = new User();
            user.setUserId(id);
            user.setEmail(email);
            user.setName(name);
            return user;
        }

        return null;
    }

    private String getResponceAsString(InputStream inputStream) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }


    public Game joinGame(String id) {
        String url = SERVER_URL + "/join?user_id=" + id;
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);

            HttpResponse response = client.execute(request);

            String resp = getResponceAsString(response.getEntity().getContent());

            return convertGame(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;


    }

    public Game convertGame(String body) throws ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(body);
        if (obj instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) obj;
            String id = (String) jsonObject.get("id");
            String nextTurn = (String) jsonObject.get("nextTurnForUserId");
            String status = (String) jsonObject.get("status");

            Game game = new Game();
            List<Suvis> suviai = new ArrayList<>();
            for (Object objc : (JSONArray) jsonObject.get("events")) {

                JSONObject jobj = (JSONObject) objc;
                JSONObject coordinate = (JSONObject) jobj.get("coordinate");
                String column = (String) coordinate.get("column");
                long row = (long) coordinate.get("row");
                boolean hit = (Boolean) jobj.get("hit");
                String userId = (String) jobj.get("userId");

                Suvis suvis = new Suvis();
                suvis.setUserId(userId);
                suvis.setColumn(column);
                suvis.setRow(row);
                suvis.setHit(hit);

                suviai.add(suvis);

            }
            game.setNextTurn(nextTurn);
            game.setEvents(suviai);
            game.setGameId(id);
            game.setStatus(status);
            game.setNextTurn(nextTurn);


            return game;
        }

        return null;
    }


    @Override
    public Game setupShips(String gameId, String userId, List<String> laivai) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < laivai.size(); i++) {
            if (i < laivai.size() - 1) {
                sb.append(laivai.get(i)).append("!");
            } else {
                sb.append(laivai.get(i));
            }
        }
       // String url = SERVER_URL + "/setup?game_id=" + gameId + "&user_id=" + userId + "&data=" + sb;
               String url = SERVER_URL + "/setup?game_id=" + gameId + "&user_id=" + userId + "&data=K0-K3!L1-L3!L5-L7!M2-M3!M5-M6!M8-M9!M0-M0!S9-S9!R8-R8!R5-R5";
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);

            HttpResponse response = client.execute(request);

            String resp = getResponceAsString(response.getEntity().getContent());

            return convertGame(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

      public Game gameStatus(String gameId) {
        String url = SERVER_URL + "/status?game_id=" + gameId;
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);

            HttpResponse response = client.execute(request);

            String resp = getResponceAsString(response.getEntity().getContent());

            return convertGame(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Game shot(String gameId, String userId, String raideSauti, int skaiciusSauti) {

               String url = SERVER_URL + "/turn?game_id=" + gameId + "&user_id=" + userId + "&data=" + raideSauti + skaiciusSauti;
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);

            HttpResponse response = client.execute(request);

            String resp = getResponceAsString(response.getEntity().getContent());

            return convertGame(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}




