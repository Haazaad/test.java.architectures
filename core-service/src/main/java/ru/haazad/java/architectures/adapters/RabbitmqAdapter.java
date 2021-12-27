package ru.haazad.java.architectures.adapters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitmqAdapter {
    private static final String QUEUE = "testQueue";
    private final ObjectMapper mapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = QUEUE)
    public void consume(String in) {
        log.debug("Message read from queue: " + in);
    }

    public void sendMessage(Object message) throws JsonProcessingException {
        log.debug("Send message: " + mapper.writeValueAsString(message));
        rabbitTemplate.convertAndSend(QUEUE, mapper.writeValueAsString(message));
    }
}
