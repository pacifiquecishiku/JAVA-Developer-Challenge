package com.witsoftware.challenge.service;

import com.witsoftware.challenge.dto.OperandosDTO;

public interface IRabbitService {
    void send(String queueName, OperandosDTO operandosDTO);
}
