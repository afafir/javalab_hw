package com.javalab.rabbitmq.demo.consumer.topic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javalab.rabbitmq.demo.models.forDirect.VacationDto;
import com.javalab.rabbitmq.demo.models.forTopic.DepartmentDto;
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
import java.util.HashMap;
import java.util.Map;


@Component
@DependsOn("TopicProducer")
@RequiredArgsConstructor
public class StatisticsDepartmentConsumer {



    @Value("${department.statictics}")
    private String queue;

    private final ConnectionFactory connectionFactory;
    private final ObjectMapper objectMapper;
    private Channel channel;
    private Map<String, Integer> departmentsCalls;

    @PostConstruct
    @SneakyThrows
    public void start() {
        channel = connectionFactory.newConnection().createChannel();
        departmentsCalls = new HashMap<>();
        consume();
    }

    public void consume() {
        try {
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                try {
                    DepartmentDto departmentDto = objectMapper.readValue(message.getBody(), DepartmentDto.class);
                    System.out.println(departmentDto);
                    departmentsCalls.put(departmentDto.getDepartment(), departmentsCalls.getOrDefault(departmentDto.getDepartment(), 0) + 1);
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
