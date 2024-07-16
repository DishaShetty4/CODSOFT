package project;


import java.util.Scanner;

//Class representing a user's bank account
class BankAccount {
 private double balance;

 public BankAccount(double initialBalance) {
     this.balance = initialBalance;
 }

 public double getBalance() {
     return balance;
 }

 public boolean deposit(double amount) {
     if (amount > 0) {
         balance += amount;
         return true;
     }
     return false;
 }

 public boolean withdraw(double amount) {
     if (amount > 0 && amount <= balance) {
         balance -= amount;
         return true;
     }
     return false;
 }
}

//Class representing the ATM machine
class ATM {
 private BankAccount account;
 private Scanner scanner;

 public ATM(BankAccount account) {
     this.account = account;
     this.scanner = new Scanner(System.in);
 }

 public void start() {
     int choice;
     do {
         System.out.println("ATM Menu:");
         System.out.println("1. Check Balance");
         System.out.println("2. Deposit");
         System.out.println("3. Withdraw");
         System.out.println("4. Exit");
         System.out.print("Enter your choice: ");
         choice = scanner.nextInt();

         switch (choice) {
             case 1:
                 checkBalance();
                 break;
             case 2:
                 deposit();
                 break;
             case 3:
                 withdraw();
                 break;
             case 4:
                 System.out.println("Exiting...");
                 break;
             default:
                 System.out.println("Invalid choice. Please try again.");
         }
     } while (choice != 4);
 }

 private void checkBalance() {
     System.out.printf("Your current balance is: $%.2f%n", account.getBalance());
 }

 private void deposit() {
     System.out.print("Enter the amount to deposit: ");
     double amount = scanner.nextDouble();
     if (account.deposit(amount)) {
         System.out.println("Deposit successful.");
     } else {
         System.out.println("Invalid deposit amount.");
     }
 }

 private void withdraw() {
     System.out.print("Enter the amount to withdraw: ");
     double amount = scanner.nextDouble();
     if (account.withdraw(amount)) {
         System.out.println("Withdrawal successful.");
     } else {
         System.out.println("Insufficient balance or invalid amount.");
     }
 }
}

//Main class to run the ATM system
public class Interface{
 public static void main(String[] args) {
     BankAccount account = new BankAccount(1000.00); // Initialize account with $1000
     ATM atm = new ATM(account);
     atm.start();
 }
}