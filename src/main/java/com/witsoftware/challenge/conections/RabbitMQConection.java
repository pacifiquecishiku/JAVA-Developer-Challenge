package com.witsoftware.challenge.conections;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static org.springframework.amqp.core.Binding.DestinationType.QUEUE;

@Component
public class RabbitMQConection {

    public static final String DEFAULT_EXCHANGE = "amq.direct";
    public static final String SUM_QUEUE = "sum";
    public static final String SUBTRACTION_QUEUE = "subtraction";
    public static final String MULTIPLICATION_QUEUE = "multiplication";
    public static final String DIVISION_QUEUE = "division";

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
        Queue sumQueue = queue(SUM_QUEUE);
        Queue subQueue = queue(SUBTRACTION_QUEUE);
        Queue multQueue = queue(MULTIPLICATION_QUEUE);
        Queue divQueue = queue(DIVISION_QUEUE);

        Exchange exchange = directExchange(DEFAULT_EXCHANGE);

        amqpAdmin.declareQueue(sumQueue);
        amqpAdmin.declareQueue(subQueue);
        amqpAdmin.declareQueue(multQueue);
        amqpAdmin.declareQueue(divQueue);

        amqpAdmin.declareExchange(exchange);

        amqpAdmin.declareBinding(binding(sumQueue, exchange));
        amqpAdmin.declareBinding(binding(subQueue, exchange));
        amqpAdmin.declareBinding(binding(multQueue, exchange));
        amqpAdmin.declareBinding(binding(divQueue, exchange));
    }
}
