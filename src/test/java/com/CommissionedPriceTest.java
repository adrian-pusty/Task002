package com;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class CommissionedPriceTest {
    public static final CommissionedPrice price = CommissionedPrice.from("106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001");

    @Test
    public void fromTest()
    {
        assertEquals(106, price.getId());
        assertEquals("EUR/USD", price.getInstrumentName());
        assertEquals(0, new BigDecimal("1.1011").compareTo(price.getAsk()));
        assertEquals(0, new BigDecimal("1.1988").compareTo(price.getBid()));
        assertEquals(2020, price.getTimestamp().getYear());
        assertEquals(Month.JUNE, price.getTimestamp().getMonth());
        assertEquals(1, price.getTimestamp().getDayOfMonth());
        assertEquals(1, price.getTimestamp().getSecond());
    }
}