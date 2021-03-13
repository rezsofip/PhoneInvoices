package datamodel;

public class PhoneNumber {
    private int provider;
    private String phoneNumber;
    private int tariffId;

    public PhoneNumber(int provider, String phoneNumber) {
        this.provider = provider;
        this.phoneNumber = phoneNumber;
    }

    public PhoneNumber(int provider, String phoneNumber, int tariffId) {
        this.provider = provider;
        this.phoneNumber = phoneNumber;
        this.tariffId = tariffId;
    }

    public int getProvider() {
        return provider;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getTariffId() {
        return tariffId;
    }

    @Override
    public boolean equals(Object obj) {
       PhoneNumber phoneNumber = (PhoneNumber) obj;
        return phoneNumber.phoneNumber.equals(this.phoneNumber);
    }

    @Override
    public String toString() {
        return this.phoneNumber;
    }
}
