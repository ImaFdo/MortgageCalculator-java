import java.text.NumberFormat;
import java.util.Scanner;

public class MortgageCalculatorEnhanced {
    // Define constants for validation limits
    private static final int MIN_PRINCIPAL = 10_000;
    private static final int MAX_PRINCIPAL = 1_000_000;
    private static final double MIN_INTEREST_RATE = 1.0;
    private static final double MAX_INTEREST_RATE = 30.0;
    private static final int MIN_YEARS = 1;
    private static final int MAX_YEARS = 30;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Encapsulate input and validation into methods
        int principal = (int) readNumber(
                scanner,
                "Principal (Rs" + MIN_PRINCIPAL + " - Rs" + MAX_PRINCIPAL + "): ",
                MIN_PRINCIPAL,
                MAX_PRINCIPAL,
                "Enter a value between LKR " + MIN_PRINCIPAL + " and " + MAX_PRINCIPAL + "."
        );

        double annualInterestRate = readNumber(
                scanner,
                "Annual Interest Rate (" + MIN_INTEREST_RATE + " - " + MAX_INTEREST_RATE + "): ",
                MIN_INTEREST_RATE,
                MAX_INTEREST_RATE,
                "Enter a value between " + MIN_INTEREST_RATE + " and " + MAX_INTEREST_RATE + "."
        );

        int years = (int) readNumber(
                scanner,
                "Period (Years, " + MIN_YEARS + " - " + MAX_YEARS + "): ",
                MIN_YEARS,
                MAX_YEARS,
                "Enter a value between " + MIN_YEARS + " and " + MAX_YEARS + "."
        );

        // 2. Calculation
        double monthlyPayment = calculateMortgage(principal, annualInterestRate, years);

        // 3. Formatted Output
        // Use NumberFormat to format the currency output
        String formattedPayment = NumberFormat.getCurrencyInstance().format(monthlyPayment);

        System.out.println("--------------------------------------");
        System.out.println("MORTGAGE CALCULATION SUMMARY 🏠");
        System.out.println("-------------------------------------");
        System.out.println("Principal: " + NumberFormat.getCurrencyInstance().format(principal));
        System.out.println("Annual Interest Rate: " + annualInterestRate + "%");
        System.out.println("Period (Years): " + years);
        System.out.println("-------------------------------------");
        System.out.println("Monthly Payment: " + formattedPayment);
        System.out.println("-------------------------------------");
        
        scanner.close(); // Close the scanner to free resources
    }
    
    // Helper method to calculate the mortgage payment
    public static double calculateMortgage(int principal, double annualInterestRate, int years) {
        double monthlyInterestRate = annualInterestRate / 100 / 12;
        int numberOfPayments = years * 12;

        // Check for 0 interest rate to avoid division by zero or large number issues
        if (monthlyInterestRate == 0) {
            // Simple division for 0% interest
            return (double) principal / numberOfPayments;
        }

        // Mortgage Formula
        // M = P [ i(1 + i)^n ] / [ (1 + i)^n – 1]
        double power = Math.pow(1 + monthlyInterestRate, numberOfPayments);
        double monthlyPayment = principal * (monthlyInterestRate * power) / (power - 1);
        
        return monthlyPayment;
    }

    // Generic method to handle input and validation for numbers (int or double)
    public static double readNumber(Scanner scanner, String prompt, double min, double max, String errorMessage) {
        double value;
        while (true) {
            System.out.print(prompt);
            // Check if the next token is a valid number before reading
            if (scanner.hasNextDouble()) {
                value = scanner.nextDouble();
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println(errorMessage);
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Consume the invalid token to avoid an infinite loop
            }
        }
    }
}
