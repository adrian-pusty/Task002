package com.jms;

import com.domain.CommissionedPrice;
import com.jms.config.JmsConfig;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class PriceListener
{
    @JmsListener(destination = JmsConfig.PRICE_QUEUE)
    public void onMessage(String message)
    {
        CommissionedPrice price = CommissionedPrice.from(message);
        System.out.println(price);
    }
}
