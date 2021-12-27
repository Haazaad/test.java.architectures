package ru.haazad.java.architectures.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Value("${rabbitmq.queues.test}")
    private String queue;

    @Bean
    public Queue queue() {
        return new Queue(queue, false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("webapp_exchange");
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(String.valueOf(queue));
    }
}
