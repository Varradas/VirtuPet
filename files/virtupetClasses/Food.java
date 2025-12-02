package virtupetClasses;

public enum Food{
    PET_KIBBLE(30),
    VEGETABLE(30),
    FRUIT(30),
    TREAT(30),
    FISH(30),
    MEAT(30),
    CHICKEN(30);

    public final float foodValue;

    Food (float foodValue){
        this.foodValue = foodValue;
    }

}