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

    @Test
    void addShouldReturnSumWhenOperandsAreValid() {
        assertEquals(5.0, service.add(2, 3));
    }

    @Test
    void subtractShouldReturnDifferenceWhenOperandsAreValid() {
        assertEquals(1.0, service.subtract(3, 2));
    }

    @Test
    void multiplyShouldReturnProductWhenOperandsAreValid() {
        assertEquals(6.0, service.multiply(2, 3));
    }

    @Test
    void divideShouldReturnQuotientWhenOperandsAreValid() {
        assertEquals(2.0, service.divide(6, 3));
    }

    @Test
    void divideShouldReturnNegativeQuotientWhenNumeratorIsNegative() {
        assertEquals(-2.0, service.divide(-6, 3));
    }

    @Test
    void divideShouldThrowExceptionWhenDivisorIsZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.divide(5, 0);
        });
        assertEquals("Division by zero is not allowed.", exception.getMessage());
    }

    @Test
    void addShouldThrowExceptionWhenOperandIsNaN() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.add(Double.NaN, 1.0);
        });
        assertEquals("Invalid values for addition.", exception.getMessage());
    }

    @Test
    void subtractShouldThrowExceptionWhenOperandIsInfinite() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.subtract(Double.POSITIVE_INFINITY, 1.0);
        });
        assertEquals("Infinite values are not allowed.", exception.getMessage());
    }

    @Test
    void multiplyShouldThrowExceptionWhenResultOverflows() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            service.multiply(Double.MAX_VALUE, Double.MAX_VALUE);
        });
        assertEquals("Multiplication overflow.", exception.getMessage());
    }
}