package br.com.calc.pi.services;

import org.springframework.stereotype.Service;


@Service
public class CalculatorService {

    public double add(double a, double b) {
        if (Double.isNaN(a) || Double.isNaN(b)) {
            throw new IllegalArgumentException("Invalid values for addition.");
        }
        return a + b;
    }

    public double subtract(double a, double b) {
        if (Double.isInfinite(a) || Double.isInfinite(b)) {
            throw new IllegalArgumentException("Infinite values are not allowed.");
        }
        return a - b;
    }

    public double multiply(double a, double b) {
        double result = a * b;
        if (Double.isInfinite(result)) {
            throw new ArithmeticException("Multiplication overflow.");
        }
        return result;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed.");
        }
        return a / b;
    }
}
