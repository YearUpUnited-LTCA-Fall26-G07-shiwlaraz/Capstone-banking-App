package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TransactionFileReader {

    public TransactionFileReader() {
    }

    public static HashMap<LocalDate, List<Transaction>> readTransactions() {

        HashMap<LocalDate, List<Transaction>> transactions = new HashMap<>();

        try {
            FileReader fr1 = new FileReader("Transactions.csv");
            BufferedReader br1 = new BufferedReader(fr1);
            br1.readLine();

            String Line;

            while ((Line = br1.readLine()) != null) {

                String[] split = Line.split("[|]");

                LocalDate date = LocalDate.parse(split[0]);
                LocalTime time = LocalTime.parse(split[1]);
                String description = split[2];
                String vendor = split[3];
                double amount = Double.parseDouble(split[4]);

                Transaction transaction =
                        new Transaction(date, time, description, vendor, amount);

                transactions.computeIfAbsent(date, (k) -> new ArrayList<>())
                        .add(transaction);
            }

        } catch (IOException e) {
            System.out.print("Something went Wrong");
        }

        return transactions;
    }
    public static void readAllTransactions() {
        HashMap<LocalDate, List<Transaction>> transactions = readTransactions();

        for (List<Transaction> transactionList : transactions.values()) {
            for (Transaction transaction : transactionList) {
                System.out.println(
                        transaction.getDate() + " | " +
                                transaction.getTime() + " | " +
                                transaction.getDescription() + " | " +
                                transaction.getVendor() + " | " +
                                transaction.getAmount()
                );
            }
        }

    }


    public static void depositTransactions() {

        HashMap<LocalDate, List<Transaction>> transactions = readTransactions();

        for (List<Transaction> transactionList : transactions.values()) {

            for (Transaction transaction : transactionList) {

                if (transaction.getAmount() > 0.0) {

                    System.out.println(
                            transaction.getDate() + " | " +
                                    transaction.getTime() + " | " +
                                    transaction.getDescription() + " | " +
                                    transaction.getVendor() + " | " +
                                    transaction.getAmount()
                    );
                }
            }
        }
    }

    public static void PaymentTransactions() {

        HashMap<LocalDate, List<Transaction>> transactions = readTransactions();

        for (List<Transaction> transactionList : transactions.values()) {

            for (Transaction transaction : transactionList) {

                if (transaction.getAmount() < 0.0) {

                    System.out.println(
                            transaction.getDate() + " | " +
                                    transaction.getTime() + " | " +
                                    transaction.getDescription() + " | " +
                                    transaction.getVendor() + " | " +
                                    transaction.getAmount()
                    );
                }
            }
        }
    }

    public static void searchByMonthToDate() {

        LocalDate startDate = LocalDate.now().withDayOfMonth(1);
        LocalDate endDate = LocalDate.now();

        HashMap<LocalDate, List<Transaction>> transactions = readTransactions();

        for (LocalDate date : transactions.keySet()) {

            if (!date.isBefore(startDate) && !date.isAfter(endDate)) {

                for (Transaction transaction : transactions.get(date)) {

                    System.out.printf(
                            "%s | %s | %s | $%.2f%n",
                            transaction.getDate(),
                            transaction.getDescription(),
                            transaction.getVendor(),
                            transaction.getAmount()
                    );
                }
            }
        }
    }
    public static void searchByPreviousMonth() {
        LocalDate startDate = LocalDate.now().minusMonths(1).withDayOfMonth(1);
        LocalDate endDate = LocalDate.now().withDayOfMonth(1).minusDays(1);

        HashMap<LocalDate, List<Transaction>> transactions = readTransactions();
        for (LocalDate date : transactions.keySet()) {
            if (!date.isBefore(startDate) && !date.isAfter(endDate)) {
                for (Transaction transaction : transactions.get(date)) {
                    System.out.printf(
                            "%s | %s | %s | $%.2f%n",
                            transaction.getDate(),
                            transaction.getDescription(),
                            transaction.getVendor(),
                            transaction.getAmount());
                }
            }
        }
    }
    public static void searchByYearToDate() {
        LocalDate startDate = LocalDate.now().withMonth(1).withDayOfMonth(1);
        LocalDate endDate = LocalDate.now();

        HashMap<LocalDate, List<Transaction>> transactions = readTransactions();
        for (LocalDate date : transactions.keySet()) {
            if (!date.isBefore(startDate) && !date.isAfter(endDate)){
                for (Transaction transaction : transactions.get(date)){
                    System.out.printf(
                            "%s | %s | %s | $%.2f%n",
                            transaction.getDate(),
                            transaction.getDescription(),
                            transaction.getVendor(),
                            transaction.getAmount());
                }
            }
        }

    }
    public static void searchByPreviousYear() {
        LocalDate startDate = LocalDate.now().minusYears(1).withMonth(1).withDayOfMonth(1);
        LocalDate endDate = LocalDate.now().minusYears(1).withMonth(12).withDayOfMonth(31);

        HashMap<LocalDate, List<Transaction>> transactions = readTransactions();
        for (LocalDate date : transactions.keySet()) {
            if (!date.isBefore(startDate) && !date.isAfter(endDate)) {
                for (Transaction transaction : transactions.get(date)) {
                    System.out.printf(
                            "%s | %s | %s | $%.2f%n",
                            transaction.getDate(),
                            transaction.getDescription(),
                            transaction.getVendor(),
                            transaction.getAmount());
                }
            }
        }
    }
    public static void searchByVendor(){
        String vendorSearch = Console.promptForString("Enter the name of the vendor");
        String vendor = vendorSearch;

        HashMap<LocalDate, List<Transaction>> transactions = readTransactions();

        for (List<Transaction> transactionList : transactions.values()) {
            for (Transaction transaction : transactionList) {
                if (transaction.getVendor().equalsIgnoreCase(vendor)) {
                    System.out.println(
                            transaction.getDate() + " | " +
                                    transaction.getTime() + " | " +
                                    transaction.getDescription() + " | " +
                                    transaction.getVendor() + " | " +
                                    transaction.getAmount());
                }
            }
        }
    }
}