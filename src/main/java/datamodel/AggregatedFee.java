package datamodel;

public class AggregatedFee {

    private int provider;
    private int minutes;
    private double netFee;
    private double grossFee;

    public AggregatedFee(int provider, int minutes, double netFee, double grossFee) {
        this.provider = provider;
        this.minutes = minutes;
        this.netFee = netFee;
        this.grossFee = grossFee;
    }

    public int getProvider() {
        return provider;
    }

    public int getMinutes() {
        return minutes;
    }

    public double getNetFee() {
        return netFee;
    }

    public double getGrossFee() {
        return grossFee;
    }
}
