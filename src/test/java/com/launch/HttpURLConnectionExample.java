package com.launch;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.*;

import com.app.AppConfig;

public abstract class HttpURLConnectionExample {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36";
    private static final String contourCode = AppConfig.getConfigParameter("contour_code");
    private static final String vaultAutotestSecretUrl = contourCode + "test";

    public static JSONObject getSecret() throws IOException {

        URL obj = new URL(vaultAutotestSecretUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("X-Vault-Token", AppConfig.getConfigParameter("vaultToken"));
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(
                con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // return result
        String jsonString = response.toString();
        return new JSONObject(jsonString);
    }
}