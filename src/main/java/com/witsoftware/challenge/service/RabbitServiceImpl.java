package com.witsoftware.challenge.service;

import dtos.OperandosDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitServiceImpl implements IRabbitService {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void send(String queueName, OperandosDTO operandosDTO) {
        Object o = rabbitTemplate.convertSendAndReceive(queueName, operandosDTO);
    }
}
