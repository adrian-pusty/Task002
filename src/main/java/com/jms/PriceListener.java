package com.jms;

import com.domain.CommissionedPrice;
import com.jms.config.JmsConfig;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

interface Subscriber
{
    void onMessage(String message);
}

@Component
public class PriceListener implements Subscriber
{
    @JmsListener(destination = JmsConfig.PRICE_QUEUE)
    public void onMessage(String message)
    {
        CommissionedPrice price = CommissionedPrice.from(message);
        //todo add price to storage?
        System.out.println("\nReceived: " + price + "\n");
    }
}