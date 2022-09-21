package com;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PriceReader
{
    public List<Price> readPrices(String fileName) throws IOException
    {
        return readLines(fileName).stream()
                .map(Price::from)
                .collect(Collectors.toList());
    }

    private List<String> readLines(String path) throws IOException
    {
        List<String> lines = new ArrayList<>();

        InputStream inputStream = new FileInputStream(path);
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

        for (String line; (line = reader.readLine()) != null;)
        {
            lines.add(line);
        }
        return lines;
    }
}
