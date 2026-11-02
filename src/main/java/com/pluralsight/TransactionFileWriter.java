
package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class TransactionFileWriter {
    public TransactionFileWriter() {
    }

    public static void addDeposit() {
        String description = Console.promptForString("Enter description");
        String vendor = Console.promptForString("Enter Vendor");
        String doubleAmount = Console.promptForString("Enter Vendor");
        Double amount = Double.parseDouble(doubleAmount);
        if (amount <= (double)0.0F) {
            System.out.println("Deposit amount must be greater than zero.");
        } else {
            Transaction transaction = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, amount);
            writeTransaction(transaction);
        }
    }

    public static void writeTransaction(Transaction transaction) {
        try (FileWriter fw1 = new FileWriter("Transactions.csv", true)) {
            String var10001 = String.valueOf(transaction.getDate());
            fw1.write(var10001 + "|" + String.valueOf(transaction.getTime()) + "|" + transaction.getDescription() + "|" + transaction.getVendor() + "|" + transaction.getAmount() + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

    }

    public static void makePayment() {
        String description = Console.promptForString("Enter description");
        String vendor = Console.promptForString("Enter Vendor");
        String doubleAmount = Console.promptForString("Enter Payment Amount");
        double amount = Double.parseDouble(doubleAmount);
        if (amount <= (double)0.0F) {
            System.out.println("Payment amount must be greater than zero.");
        } else {
            Transaction transaction = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, -amount);
            writeTransaction(transaction);
        }
    }
}
