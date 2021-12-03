package com.witsoftware.challenge.service;

import dtos.OperandosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static consts.RabbitMQ.*;
import static java.lang.String.format;

@Service
public class ServiceImpl implements IService {

    private IRabbitService rabbitService;

    @Autowired
    public ServiceImpl(IRabbitService rabbitService) {
        this.rabbitService = rabbitService;
    }

    @Override
    public Double sum(OperandosDTO operandos) {
        validaOperandos(operandos);
        return rabbitService.send(SUM_QUEUE, operandos);
    }

    @Override
    public Double subtraction(OperandosDTO operandos) {
        validaOperandos(operandos);
        return rabbitService.send(SUBTRACTION_QUEUE, operandos);
    }

    @Override
    public Double multiplication(OperandosDTO operandos) {
        validaOperandos(operandos);
        return rabbitService.send(MULTIPLICATION_QUEUE, operandos);
    }

    @Override
    public Double division(OperandosDTO operandos) {
        validaOperandos(operandos);
        return rabbitService.send(DIVISION_QUEUE, operandos);
    }

    private void validaOperandos(OperandosDTO operandos) {
        Double a = null;
        try {
            a = Double.valueOf(operandos.getA());
            Double.valueOf(operandos.getB());
        } catch (NumberFormatException exception) {
            String message = "Não foi possível converter o valor : %s";
            if (a == null) {
                throw new NumberFormatException(format(message, operandos.getA()));
            }
            throw new NumberFormatException(format(message, operandos.getB()));
        }
    }
}
