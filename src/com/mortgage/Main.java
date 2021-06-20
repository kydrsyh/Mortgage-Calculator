package com.mortgage;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    final static int MONTHS_IN_YEAR = 12;
    final static int PERCENT = 100;

    public static void main(String[] args) {
        int principal = (int) readNumber("Principal : ", 100, 1000000);
        double interest = readNumber("Annual Interest Rate : ", 1, 30);
        int period = (int) readNumber("Period (Years) : ", 1, 30);

        printMonthlyMortgage(principal, interest, period);
        printPaymentSchedule(principal, interest, period);
    }

    private static void printMonthlyMortgage(int principal, double interest, int period) {
        double mortgage = calculateMortgage(principal, interest, period);
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + mortgageFormatted);
    }

    private static void printPaymentSchedule(int principal, double interest, int period) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for (short month = 1; month <= period * MONTHS_IN_YEAR; month++){
            double balance = calculateBalance(principal, interest, period, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    public static double readNumber(String prompt, double min, double max){
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextInt();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter value between " + min + "and " + max);
        }
        return value;
    }

    public static double calculateBalance(
            int principal,
            double interest,
            int period,
            int numberOfPaymentsMade
    ){
        double numberOfPayment = period * MONTHS_IN_YEAR;
        double monthlyInterest = (interest / PERCENT) / MONTHS_IN_YEAR;

        double balance = principal
                * (Math.pow(1 + monthlyInterest, numberOfPayment) -  Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterest, numberOfPayment) - 1);

        return balance;
    }

    public static double calculateMortgage(
            int principal,
            double interest,
            int period
    ) {
        double numberOfPayment = period * MONTHS_IN_YEAR;
        double monthlyInterest = (interest / PERCENT) / MONTHS_IN_YEAR;

        double mortgage = principal * monthlyInterest *
                Math.pow((1 + monthlyInterest), (numberOfPayment)) /
                (Math.pow((1 + monthlyInterest), (numberOfPayment)) - 1);

        return mortgage;
    }
}
