package com.witsoftware.challenge.controller;

import com.witsoftware.challenge.dto.OperandosDTO;
import com.witsoftware.challenge.dto.ResponseDTO;
import com.witsoftware.challenge.service.IService;
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
        return service.sum(operandos);
    }

    @GetMapping(
            value = "/subtract",
            params = {"a", "b"}
    )
    public ResponseDTO subtract(OperandosDTO operandos) {
        return service.subtraction(operandos);
    }

    @GetMapping(
            value = "/multiply",
            params = {"a", "b"}
    )
    public ResponseDTO multiply(OperandosDTO operandos) {
        return service.multiplication(operandos);
    }

    @GetMapping(
            value = "/divide",
            params = {"a", "b"}
    )
    public ResponseDTO divide(OperandosDTO operandos) {
        return service.division(operandos);
    }

}
