package datamodel;

public class PhoneAndProvider {
    private String phoneNumber;
    private int provider;

    public PhoneAndProvider(String phoneNumber, int provider) {
        this.phoneNumber = phoneNumber;
        this.provider = provider;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getProvider() {
        return provider;
    }

    @Override
    public boolean equals(Object obj) {
        PhoneAndProvider phoneAndProvider = (PhoneAndProvider) obj;
        return ((this.phoneNumber.equals(phoneAndProvider.phoneNumber)) && (this.provider == phoneAndProvider.provider));
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(this.phoneNumber.substring(4, 11)) + this.provider;
    }
}
