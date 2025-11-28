package virtupetClasses;

public enum Food{
    CHICKEN(30f),
    PEAS(20f),
    FISH(14f);

    public final float foodValue;

    Food (float foodValue){
        this.foodValue = foodValue;
    }

}