package com.javalab.rabbitmq.demo.producer.fanout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javalab.rabbitmq.demo.models.forFanout.UserDto;
import com.javalab.rabbitmq.demo.producer.Producer;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@RequiredArgsConstructor
@Component("FanoutProducer")
public class FanoutProducer implements Producer<UserDto> {

    @Value(value = "${name.bonus}")
    private String exchangeName;
    @Value(value = "${type.fanout}")
    private String exchangeType;

    @Value(value = "${name.bonus.dms}")
    private String dmsQueue;
    @Value(value = "${name.bonus.gym}")
    private String gymQueue;
    @Value(value = "${name.bonus.financialHelp}")
    private String financialHelpQueue;

    private final ConnectionFactory connectionFactory;
    private final ObjectMapper objectMapper;

    private Channel channel;

    @SneakyThrows
    @PostConstruct
    private void constructProducer() {
        channel = connectionFactory
                .newConnection()
                .createChannel();
        channel.exchangeDeclare(exchangeName, exchangeType);
        channel.queueDeclare(dmsQueue, true, false, false, null);
        channel.queueDeclare(gymQueue, true, false, false, null);
        channel.queueDeclare(financialHelpQueue, true, false, false, null);
        channel.queueBind(dmsQueue, exchangeName, StringUtils.EMPTY);
        channel.queueBind(gymQueue, exchangeName, StringUtils.EMPTY);
        channel.queueBind(financialHelpQueue, exchangeName, StringUtils.EMPTY);
    }


    @SneakyThrows
    @Override
    public void produce(UserDto userDto, String routingKey) {
        channel.basicPublish(exchangeName,
                StringUtils.EMPTY,
                null,
                objectMapper.writeValueAsBytes(userDto));

    }
}
