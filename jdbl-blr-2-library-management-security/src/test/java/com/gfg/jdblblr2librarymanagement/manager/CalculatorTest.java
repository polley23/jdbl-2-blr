package com.gfg.jdblblr2librarymanagement.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    Calculator calculator;

    @Test
    void add() {
        int Expected = 13;
        int ActualResult = calculator.add(2,3);
        Assertions.assertEquals(Expected,ActualResult);
    }


    @Test
    void sub() {
        int Expected = -5;
        int ActualResult = calculator.sub(2,3);
        Assertions.assertEquals(Expected,ActualResult);
    }

    @Test
    void div() {
        Assertions.assertThrows(ArithmeticException.class,
                ()->{
                    calculator.divide(0,0);
                });
    }

    @BeforeEach
    void Setup(){
        calculator = new Calculator();
    }
}