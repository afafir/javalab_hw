package com.javalab.rabbitmq.demo.consumer.fanout;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.javalab.rabbitmq.demo.models.forFanout.UserDto;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.Connection;
import java.util.concurrent.TimeoutException;

@Component
@DependsOn("FanoutProducer")
@RequiredArgsConstructor
public class FinancialHelpConsumer {

    @Value("${name.bonus.financialHelp}")
    private String queue;

    private final ConnectionFactory connectionFactory;
    private final ObjectMapper objectMapper;
    private Channel channel;


    @PostConstruct
    @SneakyThrows
    public void start() {
        channel = connectionFactory.newConnection().createChannel();
        consume();
    }

    public void consume() {
        try {
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                try {
                    UserDto userDto = objectMapper.readValue(message.getBody(), UserDto.class);
                    System.out.println(userDto);
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (IOException e) {
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                }
            };
            channel.basicConsume(queue, false, deliverCallback, consumerTag -> {
            });
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }


}
