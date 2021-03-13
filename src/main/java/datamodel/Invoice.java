package datamodel;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Invoice {
    private User user;
    private List<PhoneNumber> calledNumbers;
    private LocalDate startingDate;
    private LocalDate endingDate;
    private Map<PhoneAndProvider, AggregatedFee> aggregatedFees;

    public Invoice(User user, LocalDate startingDate) {
        this.user = user;
        this.startingDate = startingDate;
        setEndingDate();
        this.calledNumbers = user.getCalledNumbers(startingDate, this.endingDate);
        this.aggregatedFees = new HashMap<>();
        for(PhoneNumber phoneNumber : user.getPhoneNumberList()) {
            calculateAggregatedFees(phoneNumber);
        }

    }

    public User getUser() {
        return user;
    }

    public List<PhoneNumber> getCalledNumbers() {
        return calledNumbers;
    }

    public Map<PhoneAndProvider, AggregatedFee> getAggregatedFees() {
        return aggregatedFees;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public void setEndingDate() {
        this.endingDate = this.startingDate.plusMonths(1).minusDays(1);
    }

    public int findInnerTariff(int tariffId) {
        Tariff foundTariff = LoadedData.getTariffs().get(tariffId);
        return foundTariff.getNetworkInnerPrice();
    }

    public int findOuterTariff(int tariffId) {
        Tariff foundTariff = LoadedData.getTariffs().get(tariffId);
        return foundTariff.getNetworkOuterPrice();
    }

    public void calculateAggregatedFees(PhoneNumber phoneNumber) {
        int minutesFor3620 = 0;
        int minutesFor3630 = 0;
        int minutesFor3670 = 0;
        double netFeeFor3620 = 0;
        double netFeeFor3630 = 0;
        double netFeeFor3670 = 0;
        double grossFeeFor3620, grossFeeFor3630, grossFeeFor3670;

        for(PhoneCall phoneCall : LoadedData.getPhoneCalls()) {
            if(phoneCall.getSourcePhoneNumber().equals(phoneNumber)) {
                switch(phoneCall.getTargetPhoneNumber().getProvider()) {
                    case 3620:

                        if(phoneNumber.getProvider() == 3620 && phoneCall.getStartedAt().toLocalDate().isAfter(this.startingDate) && phoneCall.getStartedAt().toLocalDate().isBefore(this.endingDate)) {
                            netFeeFor3620 += phoneCall.getMinutes() * findInnerTariff(phoneNumber.getTariffId());
                            minutesFor3620 += phoneCall.getMinutes();
                        }else if(phoneNumber.getProvider() != 3620 && phoneCall.getStartedAt().toLocalDate().isAfter(this.startingDate) && phoneCall.getStartedAt().toLocalDate().isBefore(this.endingDate)){
                            netFeeFor3620 += phoneCall.getMinutes() * findOuterTariff(phoneNumber.getTariffId());
                            minutesFor3620 += phoneCall.getMinutes();
                        }
                        break;
                    case 3630:

                        if(phoneNumber.getProvider() == 3630 && phoneCall.getStartedAt().toLocalDate().isAfter(this.startingDate) && phoneCall.getStartedAt().toLocalDate().isBefore(this.endingDate)) {
                            netFeeFor3630 += phoneCall.getMinutes() * findInnerTariff(phoneNumber.getTariffId());
                            minutesFor3630 += phoneCall.getMinutes();
                        }else if(phoneNumber.getProvider() != 3630 && phoneCall.getStartedAt().toLocalDate().isAfter(this.startingDate) && phoneCall.getStartedAt().toLocalDate().isBefore(this.endingDate)){
                            netFeeFor3630 += phoneCall.getMinutes() * findOuterTariff(phoneNumber.getTariffId());
                            minutesFor3630 += phoneCall.getMinutes();
                        }
                        break;
                    case 3670:

                        if(phoneNumber.getProvider() == 3670 && phoneCall.getStartedAt().toLocalDate().isAfter(this.startingDate) && phoneCall.getStartedAt().toLocalDate().isBefore(this.endingDate)) {
                            netFeeFor3670 += phoneCall.getMinutes() * findInnerTariff(phoneNumber.getTariffId());
                            minutesFor3670 += phoneCall.getMinutes();
                        }else if(phoneNumber.getProvider() != 3670 && phoneCall.getStartedAt().toLocalDate().isAfter(this.startingDate) && phoneCall.getStartedAt().toLocalDate().isBefore(this.endingDate)){
                            netFeeFor3670 += phoneCall.getMinutes() * findOuterTariff(phoneNumber.getTariffId());
                            minutesFor3670 += phoneCall.getMinutes();
                        }
                        break;
                }
            }else if(phoneCall.getTargetPhoneNumber().equals(phoneNumber)) {
                switch(phoneCall.getSourcePhoneNumber().getProvider()) {
                    case 3620:

                        if(phoneNumber.getProvider() == 3620) {
                            netFeeFor3620 += phoneCall.getMinutes() * findInnerTariff(phoneNumber.getTariffId());
                            minutesFor3620 += phoneCall.getMinutes();
                        }else if(phoneNumber.getProvider() != 3620 && phoneCall.getStartedAt().toLocalDate().isAfter(this.startingDate) && phoneCall.getStartedAt().toLocalDate().isBefore(this.endingDate)){
                            netFeeFor3620 += phoneCall.getMinutes() * findOuterTariff(phoneNumber.getTariffId());
                            minutesFor3620 += phoneCall.getMinutes();
                        }
                        break;
                    case 3630:

                        if(phoneNumber.getProvider() == 3630) {
                            netFeeFor3630 += phoneCall.getMinutes() * findInnerTariff(phoneNumber.getTariffId());
                            minutesFor3630 += phoneCall.getMinutes();
                        }else if(phoneNumber.getProvider() != 3630 && phoneCall.getStartedAt().toLocalDate().isAfter(this.startingDate) && phoneCall.getStartedAt().toLocalDate().isBefore(this.endingDate)) {
                            netFeeFor3630 += phoneCall.getMinutes() * findOuterTariff(phoneNumber.getTariffId());
                            minutesFor3630 += phoneCall.getMinutes();
                        }
                        break;
                    case 3670:

                        if(phoneNumber.getProvider() == 3670) {
                            netFeeFor3670 += phoneCall.getMinutes() * findInnerTariff(phoneNumber.getTariffId());
                            minutesFor3670 += phoneCall.getMinutes();
                        }else if(phoneNumber.getProvider() != 3670 && phoneCall.getStartedAt().toLocalDate().isAfter(this.startingDate) && phoneCall.getStartedAt().toLocalDate().isBefore(this.endingDate)) {
                            netFeeFor3670 += phoneCall.getMinutes() * findOuterTariff(phoneNumber.getTariffId());
                            minutesFor3670 += phoneCall.getMinutes();
                        }
                        break;
                }
            }
        }

        grossFeeFor3620 = netFeeFor3620 * 1.27;
        grossFeeFor3630 = netFeeFor3630 * 1.27;
        grossFeeFor3670 = netFeeFor3670 * 1.27;

        this.aggregatedFees.put(new PhoneAndProvider(phoneNumber.getPhoneNumber(), 3620), new AggregatedFee(3620, minutesFor3620, netFeeFor3620, grossFeeFor3620));
        this.aggregatedFees.put(new PhoneAndProvider(phoneNumber.getPhoneNumber(), 3630), new AggregatedFee(3630, minutesFor3630, netFeeFor3630, grossFeeFor3630));
        this.aggregatedFees.put(new PhoneAndProvider(phoneNumber.getPhoneNumber(), 3670), new AggregatedFee(3670, minutesFor3670, netFeeFor3670, grossFeeFor3670));



    }

    public void printInvoice() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("User: ");
        stringBuilder.append(this.user.getUserId() + " " + this.user.getName());
        stringBuilder.append("\n");
        stringBuilder.append(this.startingDate + " " + this.endingDate);
        for(PhoneNumber phoneNumber : this.user.getPhoneNumberList()) {
            stringBuilder.append("\n" + phoneNumber.getPhoneNumber());
            stringBuilder.append("\n");
            stringBuilder.append("3620 " + this.aggregatedFees.get(new PhoneAndProvider(phoneNumber.getPhoneNumber(), 3620)).getMinutes() + " minutes " + "net fee: " + this.aggregatedFees.get(new PhoneAndProvider(phoneNumber.getPhoneNumber(), 3620)).getNetFee() + " HUF");
            stringBuilder.append(" gross fee: " + this.aggregatedFees.get(new PhoneAndProvider(phoneNumber.getPhoneNumber(), 3620)).getGrossFee() + " HUF" + "\n");
            stringBuilder.append("3630 " + this.aggregatedFees.get(new PhoneAndProvider(phoneNumber.getPhoneNumber(), 3630)).getMinutes() + " minutes " + "net fee: " + this.aggregatedFees.get(new PhoneAndProvider(phoneNumber.getPhoneNumber(), 3630)).getNetFee() + " HUF");
            stringBuilder.append(" gross fee: " + this.aggregatedFees.get(new PhoneAndProvider(phoneNumber.getPhoneNumber(), 3630)).getGrossFee() + " HUF" + "\n");
            stringBuilder.append("3670 " + this.aggregatedFees.get(new PhoneAndProvider(phoneNumber.getPhoneNumber(), 3670)).getMinutes() + " minutes " + "net fee: " + this.aggregatedFees.get(new PhoneAndProvider(phoneNumber.getPhoneNumber(), 3670)).getNetFee() + " HUF");
            stringBuilder.append(" gross fee: " + this.aggregatedFees.get(new PhoneAndProvider(phoneNumber.getPhoneNumber(), 3670)).getGrossFee() + " HUF" + "\n");
            stringBuilder.append("Total amount: net amount: " + ((this.aggregatedFees.get(new PhoneAndProvider(phoneNumber.getPhoneNumber(), 3620)).getNetFee()) + (this.aggregatedFees.get(new PhoneAndProvider(phoneNumber.getPhoneNumber(), 3630)).getNetFee()) + (this.aggregatedFees.get(new PhoneAndProvider(phoneNumber.getPhoneNumber(), 3670)).getNetFee())));
            stringBuilder.append(" gross amount: " + ((this.aggregatedFees.get(new PhoneAndProvider(phoneNumber.getPhoneNumber(), 3620)).getNetFee()) + (this.aggregatedFees.get(new PhoneAndProvider(phoneNumber.getPhoneNumber(), 3630)).getNetFee()) + (this.aggregatedFees.get(new PhoneAndProvider(phoneNumber.getPhoneNumber(), 3670)).getNetFee())) * 1.27);

        }


        stringBuilder.append("\n");
        stringBuilder.append("Called numbers: " + this.calledNumbers);
        System.out.println(stringBuilder);
    }
}
