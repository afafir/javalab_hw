package com.javalab.rabbitmq.demo.controller;

import com.javalab.rabbitmq.demo.models.forDirect.VacationDto;
import com.javalab.rabbitmq.demo.models.forTopic.DepartmentDto;
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
public class DepartmentController {
    @Qualifier("TopicProducer")
    private final Producer<DepartmentDto> producer;


    @PostMapping(
            value = "/getFrontDocument"
    )
    public void produceFrontPdf(@RequestBody @Valid DepartmentDto departmentDto){
        departmentDto.setDepartment("front");
        producer.produce(departmentDto, "department.front");
    }

    @PostMapping(
            value = "/getBackDocument"
    )
    public void produceBackPdf(@RequestBody @Valid DepartmentDto departmentDto){
        departmentDto.setDepartment("producer");
        producer.produce(departmentDto, "department.back");
    }


}
