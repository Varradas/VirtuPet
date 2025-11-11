package files;

public class Food {
    protected String name;
    protected float value;

    Food(){
        name = "standard";
        value = 1;
    }

    void setFoodName(String n){
        name = n;
    }
    void setFoodValue(float n){
        value = n;
    }

    String getFoodName(){
        return name;
    }
    float getFoodValue(){
        return value;
    }

    void displayFoodDetails(Food[] n){
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
