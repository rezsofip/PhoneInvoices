package datamodel;

import java.util.List;
import java.util.Map;

public class LoadedData {

    private static Map<Integer, User> users = Utility.loadUsers();
    private static Map<Integer, Tariff> tariffs = Utility.loadTariffs();
    private static List<PhoneCall> phoneCalls = Utility.loadPhoneCalls();

    public static Map<Integer, User> getUsers() {
        return users;
    }

    public static Map<Integer, Tariff> getTariffs() {
        return tariffs;
    }

    public static List<PhoneCall> getPhoneCalls() {
        return phoneCalls;
    }

    public User getUserById(int userId) {
        return users.get(userId);
    }
}
