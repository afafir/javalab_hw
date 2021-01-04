package com.javalab.rabbitmq.demo.controller;

import com.javalab.rabbitmq.demo.models.forDirect.VacationDto;
import com.javalab.rabbitmq.demo.producer.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class VacationController {

    @Qualifier("TopicProducer")
    private final Producer<VacationDto> producer;

    @Value(value = "${name.vacation.paid}")
    private String paidVacationKey;
    @Value(value = "${name.vacation.unpaid}")
    private String unpaidVacationKey;

    @PostMapping(
            value = "/getPaidVacation"
    )
    public void producePaidVacationPdf(@RequestBody @Valid VacationDto vacationDto){
        producer.produce(vacationDto, paidVacationKey);
    }

    @PostMapping(
            value = "/getUnpaidVacation"
    )
    public void produceUnpaidVacationPdf(@RequestBody @Valid VacationDto vacationDto){
        producer.produce(vacationDto, unpaidVacationKey);
    }


}
