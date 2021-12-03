package com.witsoftware.challenge.service;

import dtos.OperandosDTO;

public interface IService {
    Double sum(OperandosDTO operandos);

    Double subtraction(OperandosDTO operandos);

    Double multiplication(OperandosDTO operandos);

    Double division(OperandosDTO operandos);
}
