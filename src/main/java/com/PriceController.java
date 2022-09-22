package com;

import com.domain.CommissionedPrice;
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
@RequiredArgsConstructor
@RequestMapping("prices")
public class PriceController
{
    @Value("${feed.path}")
    private String feedPath;
    private final PriceReader priceReader;

    @GetMapping(value="/", produces = "application/json")
    public @ResponseBody
    List<CommissionedPrice> getPrices() throws IOException
    {
        return priceReader.readPrices(feedPath);
    }

    @GetMapping(value="/{leftCurrency}/{rightCurrency}", produces = "application/json")
    public @ResponseBody CommissionedPrice getPrice(@PathVariable String leftCurrency, @PathVariable String rightCurrency) throws IOException
    {
        return priceReader.readPrices(feedPath).stream()
                .filter(price -> matches(leftCurrency, rightCurrency, price))
                .max(Comparator.comparing(CommissionedPrice::getTimestamp))
                .orElse(CommissionedPrice.empty());
    }

    private boolean matches(String leftCurrency, String rightCurrency, CommissionedPrice price)
    {
        String pair = leftCurrency + "/" + rightCurrency;
        return pair.toUpperCase().equals(price.getInstrumentName());
    }
}