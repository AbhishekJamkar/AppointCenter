package com.zosh.messaging;

import com.zosh.payload.dto.UserDTO;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class UserEventProducer {
//    private final RabbitTemplate rabbitTemplate;

//    public void userCreatedEvent(UserDTO userDTO){
//        rabbitTemplate.convertAndSend("user-queue",userDTO);
//    }
}
