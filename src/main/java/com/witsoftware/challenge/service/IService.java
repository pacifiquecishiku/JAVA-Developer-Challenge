package com.witsoftware.challenge.service;

import com.witsoftware.challenge.dto.ResponseDTO;
import dtos.OperandosDTO;

public interface IService {
    ResponseDTO sum(OperandosDTO operandos);

    ResponseDTO subtraction(OperandosDTO operandos);

    ResponseDTO multiplication(OperandosDTO operandos);

    ResponseDTO division(OperandosDTO operandos);
}
