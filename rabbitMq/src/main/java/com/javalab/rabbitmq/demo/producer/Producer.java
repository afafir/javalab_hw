package com.javalab.rabbitmq.demo.producer;

import com.javalab.rabbitmq.demo.models.BasicDto;

public interface Producer<T extends BasicDto> {
    void produce(T dto, String routingKey);




}
