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
        String mainMenuText = "=== Manu Options ===\n" +
                "D) Add Deposit \n" +
                "P) Make Payment (Debit) \n" +
                "L) Ledger \n" +
                "X) Exit \n" +
                "Enter Your Command";
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
                                "=== Menu Options === \n" +
                                        "A) All \n" +
                                        "D) Deposit \n" +
                                        "P) Payments \n" +
                                        "R) Report \n" +
                                        "H) To go back \n" +
                                        "Enter Your Command\n"
                        ).toUpperCase();
                        switch (choice1) {
                            case "A":
                                TransactionFileReader.readAllTransactions();
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
                                            "=== Reports Menu === \n" +
                                                    "1) Month To Date \n" +
                                                    "2) Previous Month \n" +
                                                    "3) Year To Date \n" +
                                                    "4) Previous Year \n" +
                                                    "5) Search by Vendor \n" +
                                                    "0) Back \n" +
                                                    "Enter Your Command\n"
                                    );
                                    switch (choice2) {
                                        case 1:
                                            TransactionFileReader.searchByMonthToDate();
                                            break;
                                        case 2:
                                            TransactionFileReader.searchByPreviousMonth();
                                            break;
                                        case 3:
                                            TransactionFileReader.searchByYearToDate();
                                            break;
                                        case 4:
                                            TransactionFileReader.searchByPreviousYear();
                                            break;
                                        case 5:
                                            TransactionFileReader.searchByVendor();
                                            break;
                                    }
                                } while (choice2 != 0);
                                break;
                            case "H":
                                break;
                        }
                    } while (!choice1.equals("H"));
                    break;
            }
        } while (!choice.equals("X"));
    }
}
