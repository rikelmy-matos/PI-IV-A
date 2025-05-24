package br.com.calc.pi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class CalculatorServiceTests {

    @InjectMocks
    private CalculatorService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ✅ Soma (Add)

    @Test
    void addShouldReturnCorrectSumWhenBothPositive() {
        assertEquals(8.0, service.add(5, 3));
    }

    @Test
    void addShouldReturnCorrectSumWhenBothNegative() {
        assertEquals(-9.0, service.add(-4, -5));
    }

    @Test
    void addShouldReturnSameNumberWhenAddingZero() {
        assertEquals(7.0, service.add(7, 0));
    }

    @Test
    void addShouldThrowExceptionWhenOperandIsNaN() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            service.add(Double.NaN, 1.0);
        });
        assertEquals("Invalid values for addition.", ex.getMessage());
    }

    // ✅ Subtração (Subtract)

    @Test
    void subtractShouldReturnCorrectResultWhenBothPositive() {
        assertEquals(2.0, service.subtract(5, 3));
    }

    @Test
    void subtractShouldReturnCorrectResultWhenNegativeNumbers() {
        assertEquals(1.0, service.subtract(-4, -5));
    }

    @Test
    void subtractShouldReturnSameNumberWhenSubtractingZero() {
        assertEquals(7.0, service.subtract(7, 0));
    }

    @Test
    void subtractShouldThrowExceptionWhenOperandIsInfinite() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            service.subtract(Double.POSITIVE_INFINITY, 1.0);
        });
        assertEquals("Infinite values are not allowed.", ex.getMessage());
    }

    // ✅ Multiplicação (Multiply)

    @Test
    void multiplyShouldReturnCorrectResultWhenBothPositive() {
        assertEquals(15.0, service.multiply(3, 5));
    }

    @Test
    void multiplyShouldReturnCorrectResultWhenNegativeAndPositive() {
        assertEquals(-12.0, service.multiply(-3, 4));
    }

    @Test
    void multiplyShouldReturnZeroWhenMultiplyingByZero() {
        assertEquals(0.0, service.multiply(0, 1000));
    }

    @Test
    void multiplyShouldThrowExceptionWhenResultIsInfinite() {
        Exception ex = assertThrows(ArithmeticException.class, () -> {
            service.multiply(Double.MAX_VALUE, Double.MAX_VALUE);
        });
        assertEquals("Multiplication overflow.", ex.getMessage());
    }

    // ✅ Divisão (Divide)

    @Test
    void divideShouldReturnCorrectResultWhenBothPositive() {
        assertEquals(2.0, service.divide(6, 3));
    }

    @Test
    void divideShouldReturnCorrectResultWhenNegativeNumerator() {
        assertEquals(-2.0, service.divide(-6, 3));
    }

    @Test
    void divideShouldReturnZeroWhenNumeratorIsZero() {
        assertEquals(0.0, service.divide(0, 3));
    }

    @Test
    void divideShouldThrowExceptionWhenDividingByZero() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            service.divide(5, 0);
        });
        assertEquals("Division by zero is not allowed.", ex.getMessage());
    }
}