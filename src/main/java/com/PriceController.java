package com;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

//temporarily reads from text file
@Controller
@RequestMapping("prices")
@RequiredArgsConstructor
public class PriceController
{
    @Value("${feed.path}")
    private String feedPath;
    private final PriceReader priceReader;

    @GetMapping(value="/{left}/{right}", produces = "application/json")
    public @ResponseBody CommissionedPrice getPrice(@PathVariable String left, @PathVariable String right) throws IOException
    {
        return priceReader.readPrices(feedPath).stream()
                .filter(price -> (left + "/" + right).toUpperCase().equals(price.getInstrumentName()))
                .max(Comparator.comparing(CommissionedPrice::getTimestamp))
                .orElse(CommissionedPrice.empty());
    }

    @GetMapping(value="/", produces = "application/json")
    public @ResponseBody
    List<CommissionedPrice> getPath() throws IOException
    {
        return priceReader.readPrices(feedPath);
    }
}