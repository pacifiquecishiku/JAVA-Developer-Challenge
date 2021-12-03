package com.witsoftware.challenge.controller;

import com.witsoftware.challenge.dto.ResponseDTO;
import com.witsoftware.challenge.service.IService;
import dtos.OperandosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private IService service;

    @GetMapping(
            value = "/sum",
            params = {"a", "b"}
    )
    public ResponseDTO sum(OperandosDTO operandos) {
        try {
            return service.sum(operandos);
        } catch (RuntimeException e) {
            return responseWithError(e);
        }
    }

    @GetMapping(
            value = "/subtract",
            params = {"a", "b"}
    )
    public ResponseDTO subtract(OperandosDTO operandos) {
        try {
            return service.subtraction(operandos);
        } catch (RuntimeException e) {
            return responseWithError(e);
        }
    }

    @GetMapping(
            value = "/multiply",
            params = {"a", "b"}
    )
    public ResponseDTO multiply(OperandosDTO operandos) {
        try {
            return service.multiplication(operandos);
        } catch (RuntimeException e) {
            return responseWithError(e);
        }
    }

    @GetMapping(
            value = "/divide",
            params = {"a", "b"}
    )
    public ResponseDTO divide(OperandosDTO operandos) {
        try {
            return service.division(operandos);
        } catch (RuntimeException e) {
            return responseWithError(e);
        }
    }

    private ResponseDTO responseWithError(RuntimeException e) {
        return new ResponseDTO.Builder()
                .with(true)
                .with(e.getMessage())
                .build();
    }

}
