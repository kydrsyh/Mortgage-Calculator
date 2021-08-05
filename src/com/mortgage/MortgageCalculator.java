package com.mortgage;

public class MortgageCalculator {
    private final static int MONTHS_IN_YEAR = 12;
    private final static int PERCENT = 100;

    private int principal;
    private double interest;
    private int period;

    public MortgageCalculator(int principal, double interest, int period) {
        this.principal = principal;
        this.interest = interest;
        this.period = period;
    }

    public double calculateBalance(int numberOfPaymentsMade) {
        double numberOfPayment = getNumberOfPayment();
        double monthlyInterest = getMonthlyInterest();

        double balance = principal
                * (Math.pow(1 + monthlyInterest, numberOfPayment) -  Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterest, numberOfPayment) - 1);

        return balance;
    }

    public double calculateMortgage() {
        double numberOfPayment = getNumberOfPayment();
        double monthlyInterest = getMonthlyInterest();

        double mortgage = principal * monthlyInterest *
                Math.pow((1 + monthlyInterest), (numberOfPayment)) /
                (Math.pow((1 + monthlyInterest), (numberOfPayment)) - 1);

        return mortgage;
    }

    public double[] getRemainingBalances(){
        var balances = new double[getNumberOfPayment()];
        for (short month = 1; month <= balances.length; month++) {
            balances[month - 1] = calculateBalance(month);
        }
        return balances;
    }

    private double getMonthlyInterest() {
        return (interest / PERCENT) / MONTHS_IN_YEAR;
    }

    private int getNumberOfPayment() {
        return period * MONTHS_IN_YEAR;
    }
}
