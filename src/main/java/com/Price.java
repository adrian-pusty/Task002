package com;

import lombok.Builder;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@ToString
public class Price
{
    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");

    private Long id;
    private String instrumentName;
    private BigDecimal bid; // assume it means sell price
    private BigDecimal ask; // assume it means buy price
    private LocalDateTime timestamp;

    public static Price from(String line)
    {
        String[] row = line.split(",");

        return Price.builder()
                .id(Long.valueOf(row[0].trim()))
                .instrumentName(row[1].trim())
                .bid(new BigDecimal(row[2].trim()))
                .ask(new BigDecimal(row[3].trim()))
                .timestamp(LocalDateTime.from(FORMATTER.parse(row[4])))
                .build();
    }
}
