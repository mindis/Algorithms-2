package superchaoran.HerbsUlitimate.constants;

import java.util.ArrayList;

import static superchaoran.HerbsUlitimate.constants.Ingredient.vialOfWater;

/**
 * Created by chaoran on 5/14/16.
 */
public class MethodChosen {

    private Method method;

    private Herb herb;
    private UnfPotion unfPotion;

    public MethodChosen(){}
    public MethodChosen(Method method, Herb herb, UnfPotion unfPotion){
        this.herb = herb;this.unfPotion = unfPotion;this.method = method;
    }
    public Method getMethod() {
        return method;
    }
    public void setMethod(Method method) {
        this.method = method;
    }

    public Herb getHerb() {
        return herb;
    }
    public void setHerb(Herb herb) {
        this.herb = herb;
    }

    public UnfPotion getUnfPotion() {
        return unfPotion;
    }
    public void setUnfPotion(UnfPotion unfPotion) {
        this.unfPotion = unfPotion;
    }

    /*id, number paris*/
    public ArrayList<int[]> getWithdrawSize(){
        ArrayList<int[]> list = new ArrayList<int[]>();
        switch (method){
            case CleanHerb:
                list.add(new int[]{herb.getGrimyId(),28});
                break;
            case MakeUnfPotion:
                list.add(new int[]{unfPotion.getHerb().getCleanId(),14});
                list.add(new int[]{unfPotion.getIngredient().getId(),14});
                break;
        }
        return list;
    }

}
