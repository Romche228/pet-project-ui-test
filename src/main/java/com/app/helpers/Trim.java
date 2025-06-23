package com.app.helpers;

import java.util.regex.Pattern;

public abstract class Trim
{
    public static String trim(String string, String trimSymbol)
    {
        String trimmed = leftTrim(string, trimSymbol);
        return rightTrim(trimmed, trimSymbol);
    }

    public static String leftTrim(String string, String trimSymbol)
    {
        trimSymbol = Pattern.quote(trimSymbol);
        return string.replaceAll("^" + trimSymbol + "+", "");
    }

    public static String rightTrim(String string, String trimSymbol)
    {
        trimSymbol = Pattern.quote(trimSymbol);
        return string.replaceAll(trimSymbol + "+$", "");
    }
}