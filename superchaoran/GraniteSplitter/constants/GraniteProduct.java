package superchaoran.GraniteSplitter.constants;

import org.powerbot.script.rt6.GeItem;

/**
 * Created by chaoran on 5/12/16.
 */
public enum GraniteProduct {
    Granite500g(6979);

    private final int id;
    public int price;


    GraniteProduct(int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }
    public void setPrice(int price){this.price = price;}


    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
