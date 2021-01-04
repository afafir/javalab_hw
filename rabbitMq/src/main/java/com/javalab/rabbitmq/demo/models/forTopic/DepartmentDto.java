package com.javalab.rabbitmq.demo.models.forTopic;

import com.javalab.rabbitmq.demo.models.BasicDto;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentDto extends BasicDto {
    private String department;
    private String name;
    private String surname;
}
