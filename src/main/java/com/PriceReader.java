package com;

import com.domain.CommissionedPrice;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
class PriceReader
{
    public List<CommissionedPrice> readPrices(String fileName) throws IOException
    {
        return readLines(fileName).stream()
                .map(CommissionedPrice::from)
                .collect(Collectors.toList());
    }

    private List<String> readLines(String path) throws IOException
    {
        List<String> lines = new ArrayList<>();

        InputStream inputStream = new FileInputStream(path);
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

        for (String line; (line = reader.readLine()) != null; ) {
            lines.add(line);
        }
        return lines;
    }
}