package com.mortgage;

public class Main {

    public static void main(String[] args) {
        int principal = (int) Console.readNumber("Principal : ", 100, 1000000);
        double interest = Console.readNumber("Annual Interest Rate : ", 1, 30);
        int period = (int) Console.readNumber("Period (Years) : ", 1, 30);

        var calculator = new MortgageCalculator(principal, interest, period);
        var report = new MortgageReport(calculator);
        report.printMonthlyMortgage();
        report.printpaymentschedule();
    }
}
