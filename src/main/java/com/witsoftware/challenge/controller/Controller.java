package com.witsoftware.challenge.controller;

import com.witsoftware.challenge.ChallengeApplication;
import com.witsoftware.challenge.dto.ResponseDTO;
import com.witsoftware.challenge.service.IService;
import dtos.OperandosDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.String.format;

@RestController
public class Controller {

    private static final String SUM_PATH = "/sum";
    private static final String SUBTRACT_PATH = "/subtract";
    private static final String MULTIPLY_PATH = "/multiply";
    private static final String DIVIDE_PATH = "/divide";

    @Autowired
    private IService service;
    private static final Logger LOGGER = LoggerFactory.getLogger(ChallengeApplication.class);

    @GetMapping(
            value = SUM_PATH,
            params = {"a", "b"}
    )
    public ResponseDTO sum(OperandosDTO operandos) {
        makeInfoLog(SUM_PATH, operandos);
        try {
            return responseWithResult(service.sum(operandos));
        } catch (RuntimeException e) {
            makeErrorLog(e);
            return responseWithError(e);
        }
    }

    @GetMapping(
            value = SUBTRACT_PATH,
            params = {"a", "b"}
    )
    public ResponseDTO subtract(OperandosDTO operandos) {
        makeInfoLog(SUBTRACT_PATH, operandos);
        try {
            return responseWithResult(service.subtraction(operandos));
        } catch (RuntimeException e) {
            makeErrorLog(e);
            return responseWithError(e);
        }
    }

    @GetMapping(
            value = MULTIPLY_PATH,
            params = {"a", "b"}
    )
    public ResponseDTO multiply(OperandosDTO operandos) {
        makeInfoLog(MULTIPLY_PATH, operandos);
        try {
            return responseWithResult(service.multiplication(operandos));
        } catch (RuntimeException e) {
            makeErrorLog(e);
            return responseWithError(e);
        }
    }

    @GetMapping(
            value = DIVIDE_PATH,
            params = {"a", "b"}
    )
    public ResponseDTO divide(OperandosDTO operandos) {
        makeInfoLog(DIVIDE_PATH, operandos);
        try {
            return responseWithResult(service.division(operandos));
        } catch (RuntimeException e) {
            makeErrorLog(e);
            return responseWithError(e);
        }
    }

    private ResponseDTO responseWithError(RuntimeException e) {
        return new ResponseDTO.Builder()
                .with(true)
                .with(e.getMessage())
                .build();
    }

    private ResponseDTO responseWithResult(Double result) {
        return new ResponseDTO.Builder()
                .with(result.doubleValue())
                .build();
    }

    private void makeInfoLog(String path, OperandosDTO operandos) {
        LOGGER.info(format("Endpoint: \'%s\' Operandos: a=%s b=%s", path, operandos.getA(), operandos.getB()));
    }

    private void makeErrorLog(RuntimeException e) {
        LOGGER.error("Não foi possível realizar a operação solicitada!", e);
    }
}
