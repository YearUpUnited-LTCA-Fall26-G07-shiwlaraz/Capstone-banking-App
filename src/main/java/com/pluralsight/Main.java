package com.pluralsight;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class Main {
    public Main() {
    }
    public static void main(String[] args) {
        HashMap<LocalDate, List<Transaction>> readTransactions = loadTransactions();
        ShowScreenHome();
    }
    public static HashMap<LocalDate, List<Transaction>> loadTransactions() {
        return TransactionFileReader.readTransactions();
    }
    public static void ShowScreenHome() {
        String mainMenuText = "=== Manu Options ===\nD) Add Deposit\nP) Make Payment (Debit)\nL) Ledger\nX) Exit\nEnter Your Command";

        String choice;

        do {
            choice = Console.promptForString(mainMenuText).toUpperCase();

            switch (choice) {
                case "D":
                    TransactionFileWriter.addDeposit();
                    break;
                case "P":
                    TransactionFileWriter.makePayment();
                    break;
                case "L":
                    String choice1;
                    do {
                        int choice2;
                        choice1 = Console.promptForString(
                                "=== Menu Options ===" +
                                        "A) All" +
                                        "D) Deposit" +
                                        "P) Payments" +
                                        "R) Report" +
                                        "0) Back" +
                                        "Enter Your Command\n"
                        ).toUpperCase();
                        switch (choice1) {
                            case "A":
                                TransactionFileReader.readTransactions();
                                break;

                            case "D":
                                TransactionFileReader.depositTransactions();
                                break;
                            case "P":
                                TransactionFileReader.PaymentTransactions();
                                break;
                            case "R":
                                do {
                                    choice2 = Console.promptForInt(
                                            "=== Reports Menu ===" +
                                                    "1) Month To Date" +
                                                    "2) Previous Month" +
                                                    "3) Year To Date" +
                                                    "4) Previous Year" +
                                                    "5) Search by Vendor" +
                                                    "0) Back" +
                                                    "Enter Your Command\n"
                                    );
                                    switch (choice2) {
                                        case 1:
                                            TransactionFileReader.searchInventory();
                                            break;
                                    }
                                } while (choice2 != 0);
                                break;
                        }
                    } while (!choice1.equals("0"));
                    break;
            }
        } while (!choice.equals("X"));
    }
}
