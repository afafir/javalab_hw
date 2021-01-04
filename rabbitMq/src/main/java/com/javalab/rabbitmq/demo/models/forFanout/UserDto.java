package com.javalab.rabbitmq.demo.models.forFanout;

import com.javalab.rabbitmq.demo.models.BasicDto;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto extends BasicDto {
    private String name;
    private String surname;
}
