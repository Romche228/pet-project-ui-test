package com.app;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class AppConfig
{
    private static String jsonString;

    static {
        try {
            jsonString = new String(Files.readAllBytes(Paths.get("./config.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getConfigParameter(String parameterName) {
        JSONObject json = new JSONObject(jsonString);
        String parameterValue = (String) json.getString(parameterName);
        return parameterValue;
    }
}
