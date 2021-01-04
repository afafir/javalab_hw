package com.javalab.rabbitmq.demo.controller;


import com.javalab.rabbitmq.demo.models.forFanout.UserDto;
import com.javalab.rabbitmq.demo.producer.Producer;
import com.javalab.rabbitmq.demo.producer.fanout.FanoutProducer;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BonusController {


    @Qualifier("FanoutProducer")
    private final Producer<UserDto> producer;

    @PostMapping(
            value = "/getHelp"
    )
    public void produceHelpPdfs(@RequestBody @Valid UserDto userDto){
        producer.produce(userDto, StringUtils.EMPTY);
    }






}
