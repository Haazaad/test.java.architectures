package ru.haazad.java.architectures.adapters;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitmqAdapter {
    private static final String QUEUE = "testQueue";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    public Queue queue() {
        return new Queue(QUEUE, false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("webapp_exchange");
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(QUEUE);
    }

    @RabbitListener(queues = QUEUE)
    public void consume(String in) {
        log.debug("Message read from queue: " + in);
    }

    public void sendMessage(String message) {
        log.debug("Send message: " + message);
        rabbitTemplate.convertAndSend(QUEUE, message);
    }
}
