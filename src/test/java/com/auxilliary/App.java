package com.auxilliary;

import com.CommissionedPrice;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Log
public class App
{
    public static void main(String[] args) throws IOException
    {
        Properties properties = loadProperties();

        PriceReader reader = new PriceReader();
        List<CommissionedPrice> prices = reader.readPrices(properties.getProperty("feed.path"));
        prices.forEach(System.out::println);
        System.out.println();
    }

    private static Properties loadProperties() throws IOException
    {
        Properties props = new Properties();
        props.load(App.class.getClassLoader().getResourceAsStream("app.properties"));
        return props;
    }
}
