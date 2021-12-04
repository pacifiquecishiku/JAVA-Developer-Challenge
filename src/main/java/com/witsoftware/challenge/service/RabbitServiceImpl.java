package com.witsoftware.challenge.service;

import com.witsoftware.challenge.ChallengeApplication;
import dtos.OperandosDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class RabbitServiceImpl implements IRabbitService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChallengeApplication.class);

    private DefaultTemplate rabbitTemplate;

    @Autowired
    public RabbitServiceImpl(DefaultTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public Double send(String queueName, OperandosDTO operandosDTO) {
        LOGGER.info(format("Enviando para a fila \'%s\', os operandos %s e %s ...", queueName, operandosDTO.getA(), operandosDTO.getB()));
        return (Double) rabbitTemplate.convertSendAndReceive(queueName, operandosDTO);
    }
}
