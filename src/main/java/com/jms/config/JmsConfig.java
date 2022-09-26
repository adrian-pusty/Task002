package com.jms.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfig
{
    public final static String PRICE_QUEUE = "price-queue";

    @Bean
    public MessageConverter messageConverter()
    {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();

        ObjectMapper mapper = new ObjectMapper();
        // needed to resolve: org.springframework.jms.support.converter.MessageConversionException: Could not map JSON object [CommissionedPrice(id=106, instrumentName=EUR/USD, bid=1.1988000, ask=1.1011000, timestamp=2020-06-01T12:01:01.001)]; nested exception is com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.domain.CommissionedPrice["timestamp"])
        // solution from here: https://github.com/FasterXML/jackson-modules-java8#registering-modules
        mapper.findAndRegisterModules();
        converter.setObjectMapper(mapper);

        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}

