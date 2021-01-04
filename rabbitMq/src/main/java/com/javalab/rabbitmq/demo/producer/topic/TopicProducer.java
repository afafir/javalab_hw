package com.javalab.rabbitmq.demo.producer.topic;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.javalab.rabbitmq.demo.models.forTopic.DepartmentDto;
import com.javalab.rabbitmq.demo.producer.Producer;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component("TopicProducer")
public class TopicProducer implements Producer<DepartmentDto>{

    //exchange declaration
    @Value(value = "${name.department}")
    private String exchangeName;
    @Value(value = "${type.topic}")
    private String exchangeType;

    //binding key declaration
    @Value(value = "${department.all.binding}")
    private String allBindingKey;
    @Value(value = "${department.front.binding}")
    private String frontBindingKey;
    @Value(value = "${department.back.binding}")
    private String backBindingKey;

    //queue declaration
    @Value(value ="${department.statictics}")
    private String departmentStatsQueue;
    @Value( value = "${department.documents}")
    private String departmentsDocsQueue;



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
        channel.queueDeclare(departmentStatsQueue, true, false, false, null);
        channel.queueDeclare(departmentsDocsQueue, true, false, false, null);
        channel.queueBind(departmentStatsQueue, exchangeName, allBindingKey);
        channel.queueBind(departmentsDocsQueue, exchangeName, frontBindingKey);
        channel.queueBind(departmentsDocsQueue, exchangeName, backBindingKey);
    }


    @SneakyThrows
    @Override
    public void produce(DepartmentDto departmentDto, String routingKey) {
        channel.basicPublish(exchangeName,
                routingKey,
                null,
                objectMapper.writeValueAsBytes(departmentDto));

    }


}
