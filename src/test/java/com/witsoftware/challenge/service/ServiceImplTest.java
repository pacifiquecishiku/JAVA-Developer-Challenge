package com.witsoftware.challenge.service;

import dtos.OperandosDTO;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import static consts.RabbitMQ.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ServiceImplTest {

    private ServiceImpl service;
    private IRabbitService rabbitService;
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        rabbitService = Mockito.mock(IRabbitService.class);
        service = new ServiceImpl(rabbitService);
    }

    @Test
    public void deveLancarExcecaoQuandoOPrimeiroOperandoEstiverErradoDivisao(){
        exception.expect(NumberFormatException.class);
        exception.expectMessage("Não é possível operar com o valor : abc");

        OperandosDTO operandos = monteOperandos("abc", "89");

        service.division(operandos);
    }

    @Test
    public void deveLancarExcecaoQuandoOSegundoOperandoEstiverErradoDivisao(){
        exception.expect(NumberFormatException.class);
        exception.expectMessage("Não é possível operar com o valor : 89uh");

        OperandosDTO operandos = monteOperandos("15", "89uh");

        service.division(operandos);
    }

    @Test
    public void deveLancarExcecaoQuandoOPrimeiroOperandoEstiverErradoMultiplicacao(){
        exception.expect(NumberFormatException.class);
        exception.expectMessage("Não é possível operar com o valor : abc");

        OperandosDTO operandos = monteOperandos("abc", "89");

        service.multiplication(operandos);
    }

    @Test
    public void deveLancarExcecaoQuandoOSegundoOperandoEstiverErradoMultiplicacao(){
        exception.expect(NumberFormatException.class);
        exception.expectMessage("Não é possível operar com o valor : 89uh");

        OperandosDTO operandos = monteOperandos("15", "89uh");

        service.multiplication(operandos);
    }

    @Test
    public void deveLancarExcecaoQuandoOPrimeiroOperandoEstiverErradoSubtracao(){
        exception.expect(NumberFormatException.class);
        exception.expectMessage("Não é possível operar com o valor : abc");

        OperandosDTO operandos = monteOperandos("abc", "89");

        service.subtraction(operandos);
    }

    @Test
    public void deveLancarExcecaoQuandoOSegundoOperandoEstiverErradoSubtracao(){
        exception.expect(NumberFormatException.class);
        exception.expectMessage("Não é possível operar com o valor : 89uh");

        OperandosDTO operandos = monteOperandos("15", "89uh");

        service.subtraction(operandos);
    }

    @Test
    public void deveLancarExcecaoQuandoOPrimeiroOperandoEstiverErradoSoma(){
        exception.expect(NumberFormatException.class);
        exception.expectMessage("Não é possível operar com o valor : abc");

        OperandosDTO operandos = monteOperandos("abc", "89");

        service.sum(operandos);
    }

    @Test
    public void deveLancarExcecaoQuandoOSegundoOperandoEstiverErradoSoma(){
        exception.expect(NumberFormatException.class);
        exception.expectMessage("Não é possível operar com o valor : 89uh");

        OperandosDTO operandos = monteOperandos("15", "89uh");

        service.sum(operandos);
    }

    @Test
    public void deveLancarExcecaoQuandoAlgumOperandoEstiverNulo(){
        exception.expect(NumberFormatException.class);
        exception.expectMessage("Não é possível operar com o valor : null");

        OperandosDTO operandos = monteOperandos(null, null);

        service.sum(operandos);
    }

    @Test
    public void deveRealizarOperacoesQuandoOsValoresEstaoCorretos() {
        OperandosDTO operandos = monteOperandos("12", "34");

        service.sum(operandos);
        service.subtraction(operandos);
        service.multiplication(operandos);
        service.division(operandos);

        verify(rabbitService, times(1)).send(SUM_QUEUE, operandos);
        verify(rabbitService, times(1)).send(SUBTRACTION_QUEUE, operandos);
        verify(rabbitService, times(1)).send(MULTIPLICATION_QUEUE, operandos);
        verify(rabbitService, times(1)).send(DIVISION_QUEUE, operandos);
    }

    private OperandosDTO monteOperandos(String a, String b) {
        return new OperandosDTO(a, b);
    }
}