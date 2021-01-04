package com.javalab.rabbitmq.demo.consumer.direct;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javalab.rabbitmq.demo.models.forDirect.VacationDto;
import com.javalab.rabbitmq.demo.models.forFanout.UserDto;
import com.javalab.rabbitmq.demo.service.ReportService;
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

@Component
@DependsOn("DirectProducer")
@RequiredArgsConstructor
public class PaidVacationConsumer {

    @Value("${name.vacation.paid}")
    private String queue;

    private final ConnectionFactory connectionFactory;
    private final ObjectMapper objectMapper;
    private final ReportService reportService;
    private Channel channel;


    @PostConstruct
    @SneakyThrows
    public void start() {
        channel = connectionFactory.newConnection().createChannel();
        consume();
    }

    @SneakyThrows
    public void consume() {
        try {
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                try {
                    VacationDto vacationDto = objectMapper.readValue(message.getBody(), VacationDto.class);
                    System.out.println(vacationDto);
                    reportService.getVacationDocument(vacationDto);
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
