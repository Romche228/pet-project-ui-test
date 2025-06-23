package com.app;

import com.app.pages.*;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App
{
    public LoginScreen loginScreen;

    public App() throws IOException {
        loginScreen = new LoginScreen("");
    }
}
