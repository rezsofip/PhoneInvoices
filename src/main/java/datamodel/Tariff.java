package datamodel;

public class Tariff {

    private int tariffId;
    private int networkInnerPrice;
    private int networkOuterPrice;

    public Tariff(int tariffId, int networkInnerPrice, int networkOuterPrice) {
        this.tariffId = tariffId;
        this.networkInnerPrice = networkInnerPrice;
        this.networkOuterPrice = networkOuterPrice;
    }

    public int getTariffId() {
        return tariffId;
    }

    public int getNetworkInnerPrice() {
        return networkInnerPrice;
    }

    public int getNetworkOuterPrice() {
        return networkOuterPrice;
    }
}
