package com;

import lombok.extern.java.Log;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Log
public class App
{
    public static void main(String[] args) throws IOException {
        Properties properties = loadProperties();

        PriceReader reader = new PriceReader();
        List<String> strings = reader.readLines(properties.getProperty("feed.path"));
        System.out.println(strings);
    }

    private static Properties loadProperties() throws IOException {
        Properties props = new Properties();
        props.load(App.class.getClassLoader().getResourceAsStream("app.properties"));
        return props;
    }
}
