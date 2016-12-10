package superchaoran.HerbsUlitimate.constants;

/**
 * Created by chaoran on 5/13/16.
 */
import org.powerbot.script.rt6.GeItem;
import org.powerbot.script.rt6.Item;

public enum Herb implements Comparable<Herb>{
    Guam(199, 249, 1), Tarromin(203, 253, 5), Marrentill(201,251,9), Harralander(205,255,20), Ranarr(207,257,25),
    Toadflax(3049,2998,30),SpiritWeed(12174,12172,35),Irit(209,259,40), Wergali(14836,14854,41),Avantoe(211,261,48),
    Kwuarm(213,263,45),Snapdragon(3051,3000,59), Cadantine(215,265,65),Lantadyme(2485,2481,67),DwarfWeed(217,267,70),
    Torstol(219,269,75),Fellstalk(21626,21624,91);

    private final int grimyId;
    private final int cleanId;
    private final int levelRequired;
    private int grimyPrice;
    private int cleanPrice;
    private Item item;
    private int unitProfit;
    private int numberCleaned;

    Herb(int grimyId, int cleanId, int levelRequired) {
        this.grimyId = grimyId;
        this.cleanId = cleanId;
        this.levelRequired = levelRequired;
    }

    public int getGrimyId() {
        return grimyId;
    }

    public int getCleanId() {
        return cleanId;
    }

    public int getLevelRequired() { return levelRequired;}

    /*lazy update*/
    public int getGrimyPrice(Herb herb) {
//        if(herb.grimyPrice == 0){
//            int temp = new GeItem(herb.getGrimyId()).price;
//            setGrimyPrice(temp);
//            return grimyPrice;
//        }
        return grimyPrice;
    }
    public void setGrimyPrice(int price) {this.grimyPrice = price;}

    /*lazy update*/
    public int getCleanPrice() {
        return cleanPrice;
    }

    public void setCleanPrice(int price) {this.cleanPrice = price;}

    public Item getItem(Item item) {return item;}
    public void setItem(Item item) {this.item = item;}

    public int getUnitProfit(){
        return unitProfit;
    }
    public void setUnitProfit(){
        this.grimyPrice = new GeItem(grimyId).price;
        this.cleanPrice = new GeItem(cleanId).price;
        this.unitProfit = this.cleanPrice - this.grimyPrice;
    }
    public int getNumberCleaned(){
        return numberCleaned;
    }
    public void setNumberCleaned(int numberCleaned){
        this.numberCleaned = numberCleaned;
    }
    public String toString(){
        return name() + " lvl:" + levelRequired + " profit: "+ getUnitProfit();
    }
}
