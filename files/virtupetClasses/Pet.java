package virtupetClasses;

import java.io.Serializable;
import java.util.Random;

public class Pet implements Serializable{
    private Species species;
    private String name;
    private double emotional_value;
    private EmotionalState emotional_state;
    private float hunger_level;
    private float mood_level;
    private float energy;
    private final Object[][] activity_multiplier; //Bonuses/Penalties in each activity for each pet
    private final Object[][] food_multiplier; //Bonuses/Penalties in each food for each pet

    public Pet() { //Test out the RNG for hunger, mood and level, 
        Random random = new Random();
        Activity[] activities = Activity.values();
        Food[] food = Food.values();

        hunger_level = Math.round(random.nextFloat(70,101) * 10) / 10.0f; //Upper limit is 100, lower limit is 1, random for each new instance from 70-100
        mood_level = Math.round(random.nextFloat(40,61)  * 10) / 10.0f; //Upper limit is 100, lower limit is 1, random for each new instance from 40-60
        energy = Math.round(random.nextFloat(70,101)  * 10) / 10.0f; //Upper limit is 100, lower limit is 1, random for each new instance from 70-100
        // emotional_value = Math.round(((((energy/50)+(hunger_level/25))/3) + (mood_level/12.5)) * 10.0)/10.0; //needs testing, also needs enum Emotional Value

        activity_multiplier = new Object[Activity.values().length][2];
        food_multiplier = new Object[Food.values().length][2];

        for (int i = 0; i < activities.length; i++) {
            activity_multiplier[i][0] = activities[i];  // Activity name
            activity_multiplier[i][1] = Math.round(random.nextFloat(-100.0f, 100.0f) * 10) / 10.0f;  // Random value
        }

        for (int i = 0; i < food.length; i++) {
            food_multiplier[i][0] = food[i];  // Activity name
            food_multiplier[i][1] = Math.round(random.nextFloat(1.0f, 100.0f) * 10) / 10.0f;  // Random value
        }
    }

    public void updateEmotionalState(){
        emotional_value = Math.round(((((energy/50)+(hunger_level/25))/3) + (mood_level/12.5)) * 10.0)/10.0;
        if (emotional_value <= 0.0f){
            emotional_value = 0.0f;
        }else if (emotional_value >= 10.0f){
            emotional_value = 10.0f;
        }

        if (emotional_value <= 1.0f){
            emotional_state = EmotionalState.DESPAIR;
        }else if (emotional_value <= 2.5){
            emotional_state = EmotionalState.MISERABLE;
        }else if (emotional_value <= 4.7){
            emotional_state = EmotionalState.UNHAPPY;
        }else if (emotional_value <= 5.4){
            emotional_state = EmotionalState.INDIFFERENT;
        }else if (emotional_value <= 6.4){
            emotional_state = EmotionalState.INTERESTED;
        }else if (emotional_value <= 7.4){
            emotional_state = EmotionalState.CONTENT;
        }else if (emotional_value <= 8.9){
            emotional_state = EmotionalState.HAPPY;
        }else if (emotional_value <= 10.0){
            emotional_state = EmotionalState.ECSTATIC;
        }
    }

    public void displayAllPetDetails(Pet[] n){
        
        for (Pet myPets : n) { 
            if (myPets == null) {
                System.err.println("""
                                    \u001b[38;5;202m
                                    Finished Looping through available pets.\n\u001b[0m""");
                break;
            }
            System.err.println("\nName: " + myPets.name);
            System.err.println("Species: " + myPets.species);
            System.err.println("Emotional Value: " + myPets.emotional_value);
            System.err.println("Hunger: " + myPets.hunger_level);
            System.err.println("Mood: " + myPets.mood_level);
            System.err.println("Energy: " + myPets.energy);

            System.err.println("Activity Multipliers: ");
            for (int i = 0; i < myPets.activity_multiplier.length; i++){
                int x = i+1;
                System.err.println("\nActivity " + x + ": " + myPets.activity_multiplier[i][1]);
            }
            System.err.println("Food Multipliers: ");
            for (int i = 0; i < myPets.food_multiplier.length; i++){
                int x = i+1;
                System.err.println("\nFood " + x + ": " + myPets.food_multiplier[i][1]);
            }

            
        }
        
    }

    public void setName(String n) {
        name = n;
    }

    public void setSpecies(Species n) {
        species = n;
    }

    public void setHunger(float n) {
        hunger_level = Math.round(n*10) / 10.0f;
    }

    public void setMood(float n) {
        mood_level = Math.round(n*10) / 10.0f;
    }
    
    public void setEnergy(float n) {
        energy = Math.round(n*10) / 10.0f;
    }

    public String getPetName(){
        return name;
    }
    public Species getSpecies(){
        return species;
    }

    public EmotionalState getEmotionalState(){
        return emotional_state;
    }

    public float getHunger() {
        return hunger_level;
    }

    public float getMood() {
        return mood_level;
    }
    
    public float getEnergy() {
        return energy;
    }

    public Object[][] getFoodInventory(){
        return food_multiplier;
    }

    public Object[][] getActivityList(){
        return activity_multiplier;
    }

    //Getting Activity and their Multipliers, 0 for name, 1 for value

    public String getActivityName(int n){
        if (activity_multiplier[n][0] instanceof String activityName) {
            return activityName;
        }
        throw new IllegalStateException("Expected String at index " + n);
    }

    public String getFoodName(int n){
        if (food_multiplier[n][0] instanceof String foodName) {
            return foodName;
        }
        throw new IllegalStateException("Expected String at index " + n);
    }

    public float getActivityMultiplier(int n){
        if (activity_multiplier[n][1] instanceof Float multiplier) {
            return multiplier;
        }
        throw new IllegalStateException("Expected String at index " + n);
    }

    public float getFoodMultiplier(int n){
        if (food_multiplier[n][1] instanceof Float multiplier) {
            return multiplier;
        }
        throw new IllegalStateException("Expected String at index " + n);
    }

}
