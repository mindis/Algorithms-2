package superchaoran.GraniteSplitter.constants;

/**
 * Created by chaoran on 5/12/16.
 */
public enum GraniteRaw {
    Granite5kg(6983, 3), Granite2kg(6981, 7);

    private final int id;
    private final int withdrawSize;
    public int price;
    public int unitProfit;

    GraniteRaw(int id, int withdrawSize) {
        this.id = id;
        this.withdrawSize = withdrawSize;
    }

    public int id() {
        return id;
    }
    public int getWithdrawSize() {
        return withdrawSize;
    }
    public void setPrice(int price){this.price = price;}
    public void setUnitProfit(int unitProfit){this.unitProfit = unitProfit;}

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
