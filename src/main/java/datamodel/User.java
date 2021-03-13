package datamodel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {

    private int userId;
    private String name;
    private List<PhoneNumber> phoneNumberList;

    public User(int userId, String name, List<PhoneNumber> phoneNumberList) {
        this.userId = userId;
        this.name = name;
        this.phoneNumberList = phoneNumberList;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public List<PhoneNumber> getPhoneNumberList() {
        return phoneNumberList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(userId);
        stringBuilder.append(" " + name + " " + phoneNumberList + " ");
        return stringBuilder.toString();
    }

    public List<PhoneNumber> getCalledNumbers(LocalDate startingDate, LocalDate endingDate) {
        List<PhoneNumber> phoneNumberList = new ArrayList<>();
        for(PhoneCall phoneCall : LoadedData.getPhoneCalls()) {
            for(PhoneNumber phoneNumber : this.phoneNumberList) {
                if(phoneCall.getSourcePhoneNumber().equals(phoneNumber) && phoneCall.getStartedAt().toLocalDate().isAfter(startingDate)
                        && phoneCall.getStartedAt().toLocalDate().isBefore(endingDate)) {
                    phoneNumberList.add(phoneCall.getTargetPhoneNumber());
                }else if(phoneCall.getTargetPhoneNumber().equals(phoneNumber) && phoneCall.getStartedAt().toLocalDate().isAfter(startingDate)
                        && phoneCall.getStartedAt().toLocalDate().isBefore(endingDate)) {
                    phoneNumberList.add(phoneCall.getSourcePhoneNumber());
                }
            }
            }

        return phoneNumberList;
    }
}
