package superchaoran.HerbsUlitimate.constants;

import org.powerbot.script.rt6.GeItem;

/**
 * Created by chaoran on 5/13/16.
 */
public enum UnfPotion {
    GuamPotion(91, Herb.Guam), TarrominPotion(95, Herb.Tarromin), MarrentillPotion(93, Herb.Marrentill), HarralanderPotion(97, Herb.Harralander),
    RanarrPotion(99, Herb.Ranarr), ToadflaxPotion(3002, Herb.Toadflax),SpiritWeedPotion(12181, Herb.SpiritWeed),IritPotion(101, Herb.Irit),
    WergaliPotion(14856, Herb.Wergali),AvantoePotion(103, Herb.Avantoe), KwuarmPotion(105, Herb.Kwuarm),SnapdragonPotion(3004, Herb.Snapdragon),
    CadantinePotion(107, Herb.Cadantine),LantadymePotion(2483, Herb.Lantadyme),DwarfWeedPotion(109, Herb.DwarfWeed),
    TorstolPotion(111, Herb.Torstol),FellstalkPotion(21628, Herb.Fellstalk);

    private final int id;
    private final Herb herb;
    Ingredient ingredient = Ingredient.vialOfWater;
    int unfPotionPrice;
    private int unitProfit;
    private int numberMade;

    UnfPotion(int id, Herb herb){
        this.id = id;
        this.herb = herb;
    }

    public Herb getHerb(){
        return herb;
    }

    public int getUnitProfit(){
        return unitProfit;
    }
    public void setUnitProfit(){
        unfPotionPrice = new GeItem(id).price;
        int profit = unfPotionPrice - herb.getCleanPrice() - ingredient.getPrice();
        this.unitProfit = profit;
    }

    public void setUnfPotionPrice(int price) {
        unfPotionPrice = price;
    }
    public int getNumberMade(){return this.numberMade;}
    public void setNumberMade(int numberMade){this.numberMade = numberMade;}

    public Ingredient getIngredient(){
        return ingredient;
    }

    public int getId(){
        return id;
    }
    public String toString(){
        return name() + " lvl:" + herb.getLevelRequired() + " profit: "+ getUnitProfit();
    }
}
