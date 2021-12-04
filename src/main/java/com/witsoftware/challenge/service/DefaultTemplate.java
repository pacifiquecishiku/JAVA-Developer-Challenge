package com.witsoftware.challenge.service;

import org.springframework.amqp.AmqpTimeoutException;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultTemplate extends RabbitTemplate {

    @Autowired
    public DefaultTemplate(ConnectionFactory connectionFactory) {
        super(connectionFactory);
        setReplyTimeout(2000);
    }

    @Override
    protected void replyTimedOut(String correlationId) {
        throw new AmqpTimeoutException("Ocorreu um timeOut ao tentar se conectar com o micro-serviço Calculator");
    }
}
