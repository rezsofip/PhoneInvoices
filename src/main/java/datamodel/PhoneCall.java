package datamodel;

import java.time.LocalDateTime;

public class PhoneCall {

    private PhoneNumber sourcePhoneNumber;
    private PhoneNumber targetPhoneNumber;
    private LocalDateTime startedAt;
    private int minutes;

    public PhoneCall(PhoneNumber sourcePhoneNumber, PhoneNumber targetPhoneNumber, LocalDateTime startedAt, int minutes) {
        this.sourcePhoneNumber = sourcePhoneNumber;
        this.targetPhoneNumber = targetPhoneNumber;
        this.startedAt = startedAt;
        this.minutes = minutes;
    }

    public PhoneNumber getSourcePhoneNumber() {
        return sourcePhoneNumber;
    }

    public PhoneNumber getTargetPhoneNumber() {
        return targetPhoneNumber;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public int getMinutes() {
        return minutes;
    }
}
