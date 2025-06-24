package com.example.skypay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountServiceImpl implements AccountService {
    private int balance;
    private List<Transaction> transactions;
    private SimpleDateFormat dateFormat;

    /**
     * Initializes a new account with zero balance and empty transaction history.
     */
    public AccountServiceImpl() {
        this.balance = 0;
        this.transactions = new ArrayList<>();
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }


    /**
     * Deposits the specified amount into the account.
     * @param amount The amount to deposit (must be positive)
     * @throws IllegalArgumentException if amount is not positive
     */
    @Override
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
        transactions.add(new Transaction(new Date(), amount, balance));
    }


    /**
     * Withdraws the specified amount from the account.
     * @param amount The amount to withdraw (must be ≥10 and ≤ current balance)
     * @throws IllegalArgumentException if amount is invalid or insufficient funds
     */
    @Override
    public void withdraw(int amount) {
        if (amount < 10) {
            throw new IllegalArgumentException("Withdrawal amount must be >= 10");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds for withdrawal");
        }
        balance -= amount;
        transactions.add(new Transaction(new Date(), -amount, balance));
    }

    /**
     * Prints the account statement showing all transactions in reverse chronological order,
     * with running balances.
     */
    @Override
    public void printStatement() {
        System.out.println("Date       || Amount  || Balance");

        transactions.stream()
                .sorted((t1, t2) -> t2.date().compareTo(t1.date()))
                .forEach(t -> System.out.printf("%s || %6d  || %7d%n",
                        dateFormat.format(t.date()),
                        t.amount(),
                        t.balance()));
    }
    private record Transaction(Date date, int amount, int balance) {}

}
