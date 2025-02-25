package org.calculator;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    Calculator calc = new Calculator();

    @Test
    void testSquareRoot() {
        assertEquals(4, calc.squareRoot(16), 0.001);
    }

    @Test
    void testFactorial() {
        assertEquals(120, calc.factorial(5));
    }

    @Test
    void testLog() {
        assertEquals(0, calc.log(1), 0.001);
    }

    @Test
    void testPower() {
        assertEquals(8, calc.power(2, 3), 0.001);
    }
}
