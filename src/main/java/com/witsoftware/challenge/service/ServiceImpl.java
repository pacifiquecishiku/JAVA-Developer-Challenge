package com.witsoftware.challenge.service;

import com.witsoftware.challenge.dto.OperandosDTO;
import com.witsoftware.challenge.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.witsoftware.challenge.conections.RabbitMQConection.*;
import static java.lang.String.format;

@Service
public class ServiceImpl implements IService {

    private IRabbitService rabbitService;

    @Autowired
    public ServiceImpl(IRabbitService rabbitService) {
        this.rabbitService = rabbitService;
    }

    @Override
    //TODO
//    Fazer as validações antes de enviar a requisição pro Calculator
    public ResponseDTO sum(OperandosDTO operandos) {
        validaOperandos(operandos);
        rabbitService.send(SUM_QUEUE, operandos);
        return null;
    }

    @Override
    public ResponseDTO subtraction(OperandosDTO operandos) {
        validaOperandos(operandos);
        rabbitService.send(SUBTRACTION_QUEUE, operandos);
        return null;
    }

    @Override
    public ResponseDTO multiplication(OperandosDTO operandos) {
        validaOperandos(operandos);
        rabbitService.send(MULTIPLICATION_QUEUE, operandos);
        return null;
    }

    @Override
    public ResponseDTO division(OperandosDTO operandos) {
        validaOperandos(operandos);
        rabbitService.send(DIVISION_QUEUE, operandos);
        return null;
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
