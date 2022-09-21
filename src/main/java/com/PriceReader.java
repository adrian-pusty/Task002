package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PriceReader
{
    public List<String> readLines(String fileName) throws FileNotFoundException
    {
        List<String> lines = new ArrayList<>();
        try(Scanner s = new Scanner(new File(fileName)))
        {
            while(s.hasNext())
            {
                lines.add(s.next());
            }
        }
        return lines;
    }
}
