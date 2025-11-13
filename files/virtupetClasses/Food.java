package virtupetClasses;

import java.io.Serializable;

public class Food implements Serializable{
    protected String name;
    protected float value;

    public Food(){
        name = "standard";
        value = 1;
    }

    public void setFoodName(String n){
        name = n;
    }
    public void setFoodValue(float n){
        value = n;
    }

    public String getFoodName(){
        return name;
    }
    public float getFoodValue(){
        return value;
    }

    public void displayFoodDetails(Food[] n){
        
        for (Food foodInventory : n) { //move to Pet.java
            if (foodInventory == null) {
                System.err.println("""
                                    \u001b[1;32m
                                    Finished Looping through available food.\u001b[0m""");
                break;
            }
            System.err.println("\nName of food: " + foodInventory.getFoodName());
            System.err.println("Species: " + foodInventory.getFoodValue());
        }
        
    }

}
