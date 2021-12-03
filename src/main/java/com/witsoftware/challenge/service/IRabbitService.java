package com.witsoftware.challenge.service;

import dtos.OperandosDTO;

public interface IRabbitService {
    Double send(String queueName, OperandosDTO operandosDTO);
}
