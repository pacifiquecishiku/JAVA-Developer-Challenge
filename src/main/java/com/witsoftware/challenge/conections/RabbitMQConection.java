package com.witsoftware.challenge.conections;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static org.springframework.amqp.core.Binding.DestinationType.QUEUE;

@Component
public class RabbitMQConection {

    private static final String DEFAULT_EXCHANGE = "amq.direct";
    private static final String DEFAULT_QUEUE = "calculatorQueue";

    private AmqpAdmin amqpAdmin;

    @Autowired
    public RabbitMQConection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue(String queueName) {
        return new Queue(queueName);
    }

    private DirectExchange directExchange(String exchangeName) {
        return new DirectExchange(exchangeName);
    }

    private Binding binding(Queue queue, Exchange exchange) {
        return new Binding(queue.getName(), QUEUE, exchange.getName(), queue.getName(), null);
    }

    @PostConstruct
    private void create() {
        Queue queue = queue(DEFAULT_QUEUE);
        Exchange exchange = directExchange(DEFAULT_EXCHANGE);
        Binding binding = binding(queue, exchange);
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareExchange(exchange);
        amqpAdmin.declareBinding(binding);
    }
}
