package com.witsoftware.challenge.service;

import com.witsoftware.challenge.dto.OperandosDTO;
import com.witsoftware.challenge.dto.ResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements IService {

    @Override
    public ResponseDTO sum(OperandosDTO operandos) {
        {
            try {
                double operandoA = Double.valueOf(operandos.getA());
                double operandoB = Double.valueOf(operandos.getB());

                return new ResponseDTO.Builder()
                        .com(operandoA + operandoB)
                        .build();
            } catch (NumberFormatException e) {
                throw new RuntimeException("Não foi possível convertir os números");
            }
        }
    }

    @Override
    public ResponseDTO subtraction(OperandosDTO operandos) {
        return null;
    }

    @Override
    public ResponseDTO multiplication(OperandosDTO operandos) {
        return null;
    }

    @Override
    public ResponseDTO division(OperandosDTO operandos) {
        return null;
    }
}
