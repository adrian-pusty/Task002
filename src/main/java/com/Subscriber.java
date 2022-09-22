package com;

import com.domain.CommissionedPrice;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

interface Subscriber
{
    void onMessage(String message);
}

@Component
class PriceSubscriber implements Subscriber
{
    @JmsListener(destination = "price-queue")
    public void onMessage(String message)
    {
        CommissionedPrice price = CommissionedPrice.from(message);
        // todo add price to storage
        System.out.println(price);
    }
}
