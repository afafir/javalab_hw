package com.javalab.rabbitmq.demo.producer.direct;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javalab.rabbitmq.demo.models.forDirect.VacationDto;
import com.javalab.rabbitmq.demo.producer.Producer;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@RequiredArgsConstructor
@Component("DirectProducer")
public class DirectProducer implements Producer<VacationDto> {

    @Value(value = "${name.vacation}")
    private String exchangeName;
    @Value(value = "${type.direct}")
    private String exchangeType;

    @Value(value = "${name.vacation.paid}")
    private String paidVacationQueue;
    @Value(value = "${name.vacation.unpaid}")
    private String unpaidVacationQueue;


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
        channel.queueDeclare(paidVacationQueue, true, false, false, null);
        channel.queueDeclare(unpaidVacationQueue, true, false, false, null);
        channel.queueBind(paidVacationQueue, exchangeName, paidVacationQueue);
        channel.queueBind(unpaidVacationQueue, exchangeName, unpaidVacationQueue);

    }


    @SneakyThrows
    @Override
    public void produce(VacationDto vacationDto, String routingKey) {
        channel.basicPublish(exchangeName,
                routingKey,
                null,
                objectMapper.writeValueAsBytes(vacationDto));

    }
}
