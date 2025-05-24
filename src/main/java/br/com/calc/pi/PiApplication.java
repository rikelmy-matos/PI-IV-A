package br.com.calc.pi;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.calc.pi.services.CalculatorService;

@SpringBootApplication
public class PiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiApplication.class, args);
    }

    @Bean
    public ApplicationRunner run(@Autowired CalculatorService calculatorService) {
        return args -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\n=== Calculator Menu ===");
                System.out.println("1. Add");
                System.out.println("2. Subtract");
                System.out.println("3. Multiply");
                System.out.println("4. Divide");
                System.out.println("0. Exit");
                System.out.print("Select an option: ");
                int option = scanner.nextInt();

                if (option == 0) {
                    System.out.println("Exiting...");
                    break;
                }

                System.out.print("Enter the first number: ");
                double a = scanner.nextDouble();
                System.out.print("Enter the second number: ");
                double b = scanner.nextDouble();

                try {
                    double result = switch (option) {
                        case 1 -> calculatorService.add(a, b);
                        case 2 -> calculatorService.subtract(a, b);
                        case 3 -> calculatorService.multiply(a, b);
                        case 4 -> calculatorService.divide(a, b);
                        default -> {
                            System.out.println("Invalid option.");
                            yield Double.NaN;
                        }
                    };
                    if (!Double.isNaN(result)) {
                        System.out.println("Result: " + result);
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        };
    }
}