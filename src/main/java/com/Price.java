package com;

import lombok.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@ToString
@Builder(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class Price
{
    protected static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");

    private final Long id;
    private final String instrumentName;
    private final BigDecimal bid; // assume it means sell price
    private final BigDecimal ask; // assume it means buy price
    private final LocalDateTime timestamp;

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

class CommissionedPrice extends Price
{
    private static final MathContext MC = new MathContext(4);
    private static final BigDecimal HUNDRED = new BigDecimal("100");
    private static final BigDecimal COMMISSION = new BigDecimal("0.1");
    private static final BigDecimal BID_MUL = HUNDRED.subtract(COMMISSION).divide(HUNDRED, MC);
    private static final BigDecimal ASK_MUL = HUNDRED.add(COMMISSION).divide(HUNDRED, MC);

    private CommissionedPrice(Long id, String instrumentName, BigDecimal bid, BigDecimal ask, LocalDateTime timestamp)
    {
        super(id, instrumentName, bid, ask, timestamp);
    }

    public static CommissionedPrice from(Price price)
    {
        return new CommissionedPrice(price.getId(), price.getInstrumentName(), price.getBid().multiply(BID_MUL), price.getAsk().multiply(ASK_MUL), price.getTimestamp());
    }

    public static CommissionedPrice from(String line)
    {
        String[] row = line.split(",");

        Long id = Long.valueOf(row[0].trim());
        String instrumentName = row[1].trim();
        BigDecimal bid = new BigDecimal(row[2].trim()).multiply(BID_MUL);
        BigDecimal ask = new BigDecimal(row[3].trim()).multiply(ASK_MUL);
        LocalDateTime timestamp = LocalDateTime.from(FORMATTER.parse(row[4]));

        return new CommissionedPrice(id, instrumentName, bid, ask, timestamp);
    }
}
