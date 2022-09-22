package com.jms;

import com.domain.CommissionedPrice;
import com.jms.config.JmsConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceSender
{
    private final JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 2000) // 2000 ms
    public void sendMessage()
    {
        CommissionedPrice message = CommissionedPrice.from("106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001");
        jmsTemplate.convertAndSend(JmsConfig.PRICE_QUEUE, message);
    }
}
