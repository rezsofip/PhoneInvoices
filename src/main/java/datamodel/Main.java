package datamodel;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Client id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Month: ");
        int month = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Day: ");
        int day = scanner.nextInt();
        scanner.nextLine();
        LocalDate localDate = LocalDate.of(year, month, day);
        LoadedData loadedData = new LoadedData();
        User user = loadedData.getUserById(id);
        Invoice invoice = new Invoice(user, localDate);
        invoice.printInvoice();
    }
}
