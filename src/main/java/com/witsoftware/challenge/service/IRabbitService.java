package com.witsoftware.challenge.service;

import dtos.OperandosDTO;

public interface IRabbitService {
    void send(String queueName, OperandosDTO operandosDTO);
}
