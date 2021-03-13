package datamodel;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Utility {

    private static final String USERS_FILENAME = "src/main/resources/Users.csv";
    private static final String TARIFFS_FILENAME = "src/main/resources/Tariffs.csv";
    private static final String PHONE_CALLS_FILENAME = "src/main/resources/Phone_calls.csv";

    public static Map<Integer, User> loadUsers() {
        Map<Integer, User> usersMap = new HashMap<>();
        try{
            File users = new File(USERS_FILENAME);
            Scanner scanner = new Scanner(users);
            scanner.nextLine();
            while(scanner.hasNext()) {
                String input = scanner.nextLine();
                String line[] = input.split(",");
                int userId = Integer.parseInt(line[0]);
                String name = line[1];
                String phoneNumberString = line[2];
                int tariffId = Integer.parseInt(line[3]);
                usersMap.put(userId, new User(userId, name, getUserPhoneNumbers(userId)));
            }
            scanner.close();
            return usersMap;

        }catch(FileNotFoundException e) {
            System.out.println("The file is not found.");
            return null;
        }
    }

    private static List<PhoneNumber> getUserPhoneNumbers(int userId) {
        try{
            List<PhoneNumber> phoneNumbers = new ArrayList<>();
            File usersFile = new File(USERS_FILENAME);
            Scanner scanner = new Scanner(usersFile);
            scanner.nextLine();
            while(scanner.hasNext()) {
                String input = scanner.nextLine();
                String[] line = input.split(",");
                if(Integer.parseInt(line[0]) == userId) {
                    phoneNumbers.add(new PhoneNumber(Integer.parseInt(line[2].substring(0, 4)), line[2], Integer.parseInt(line[3])));
                }
            }
            scanner.close();
            return phoneNumbers;

        }catch(FileNotFoundException e) {
            System.out.println("The file is not found.");
            return null;
        }

    }

    public static Map<Integer, Tariff> loadTariffs() {
        Map<Integer, Tariff> tariffsMap = new HashMap<>();
        try{
            File tariffs = new File(TARIFFS_FILENAME);
            Scanner scanner = new Scanner(tariffs);
            scanner.nextLine();
            while(scanner.hasNext()) {
                String input = scanner.nextLine();
                String line[] = input.split(",");
                int tariffId = Integer.parseInt(line[0]);
                int networkInnerPrice = Integer.parseInt(line[1]);
                int networkOuterPrice = Integer.parseInt(line[2]);
                tariffsMap.put(tariffId, new Tariff(tariffId, networkInnerPrice, networkOuterPrice));
            }
            scanner.close();
            return tariffsMap;

        }catch(FileNotFoundException e) {
            System.out.println("The file is not found.");
            return null;
        }
    }

    public static List<PhoneCall> loadPhoneCalls() {
        List<PhoneCall> phoneCallsList = new ArrayList<>();
        try{
            File phoneCalls = new File(PHONE_CALLS_FILENAME);
            Scanner scanner = new Scanner(phoneCalls);
            scanner.nextLine();
            while(scanner.hasNext()) {
                String input = scanner.nextLine();
                String line[] = input.split(",");
                String sourcePhoneNumberString = line[0];
                PhoneNumber sourcePhoneNumber = new PhoneNumber(Integer.parseInt(sourcePhoneNumberString.substring(0, 4)), sourcePhoneNumberString);
                String targetPhoneNumberString = line[1];
                PhoneNumber targetPhoneNumber = new PhoneNumber(Integer.parseInt(targetPhoneNumberString.substring(0, 4)), targetPhoneNumberString);
                String date = line[2];
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd kk:mm:ss");
                LocalDateTime startedAt = LocalDateTime.parse(date, dateTimeFormatter);
                int minutes = Integer.parseInt(line[3]);
                phoneCallsList.add(new PhoneCall(sourcePhoneNumber, targetPhoneNumber, startedAt, minutes));
            }
            scanner.close();
            return phoneCallsList;

        }catch(FileNotFoundException e) {
            System.out.println("The file is not found.");
            return null;
        }
    }

}
