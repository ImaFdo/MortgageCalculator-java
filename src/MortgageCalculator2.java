import java.util.Scanner;

public class MortgageCalculator2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int principal;
        while (true) {
            System.out.print("Principal (Rs10K - Rs1M): ");
            principal = scanner.nextInt();
            if (principal >= 10000 && principal <= 1000000) {
                break;
            } else {
                System.out.println("Enter a value between LKR 10,000 and 1,000,000.");
            }
        }

        double annualInterestRate;
        while (true) {
            System.out.print("Annual Interest Rate (1 - 30): ");
            annualInterestRate = scanner.nextDouble();
            if (annualInterestRate >= 1 && annualInterestRate <= 30) {
                break;
            } else {
                System.out.println("Enter a value between 1 and 30.");
            }
        }

        int years;
        while (true) {
            System.out.print("Period (Years, 1 - 30): ");
            years = scanner.nextInt();
            if (years >= 1 && years <= 30) {
                break;
            } else {
                System.out.println("Enter a value between 1 and 30.");
            }
        }

        double monthlyInterestRate = annualInterestRate / 100 / 12;
        int numberOfPayments = years * 12;

        double monthlyPayment = principal * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments)) /
                (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

        System.out.println("Monthly Payment: " + monthlyPayment);
    }
}