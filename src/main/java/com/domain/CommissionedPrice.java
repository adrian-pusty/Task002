package com.domain;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommissionedPrice implements Serializable
{
    static final long serialVersionUID = 1L;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");
    private static final MathContext MC = new MathContext(4);
    private static final BigDecimal HUNDRED = new BigDecimal("100");
    private static final BigDecimal COMMISSION = new BigDecimal("0.1");
    private static final BigDecimal BID_MUL = HUNDRED.subtract(COMMISSION).divide(HUNDRED, MC);
    private static final BigDecimal ASK_MUL = HUNDRED.add(COMMISSION).divide(HUNDRED, MC);

    private Long id;
    private String instrumentName;
    private BigDecimal bid; // assume it means sell price
    private BigDecimal ask; // assume it means buy price
    private LocalDateTime timestamp;

    public static CommissionedPrice from(String line)
    {
        String[] row = line.split(",");

        return CommissionedPrice.builder()
                .id(Long.valueOf(row[0].trim()))
                .instrumentName(row[1].trim())
                .ask(new BigDecimal(row[2].trim()).multiply(ASK_MUL))
                .bid(new BigDecimal(row[3].trim()).multiply(BID_MUL))
                .timestamp(LocalDateTime.from(FORMATTER.parse(row[4].trim())))
                .build();
    }

    public static CommissionedPrice empty()
    {
        return CommissionedPrice.builder().build();
    }

    @Override
    public String toString()
    {
        return id + ", " + instrumentName + ", " + bid + ", " + ask + ", " + timestamp.format(FORMATTER).trim();
    }
}
