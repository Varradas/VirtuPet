package virtupetClasses;

import java.io.Serializable;
import java.util.Random;

public class Pet implements Serializable{
    private String species;
    private String name;
    private double emotional_value;
    private float hunger_level;
    private float mood_level;
    private float energy;
    private final float[] activity_multiplier; //Bonuses/Penalties in each activity for each pet
    private final float[] food_multiplier; //Bonuses/Penalties in each food for each pet

    public Pet() { //Test out the RNG for hunger, mood and level, 
        Random random = new Random();

        hunger_level = Math.round(random.nextFloat(70,101) * 10) / 10.0f; //Upper limit is 100, lower limit is 1, random for each new instance from 70-100
        mood_level = Math.round(random.nextFloat(40,61)  * 10) / 10.0f; //Upper limit is 100, lower limit is 1, random for each new instance from 40-60
        energy = Math.round(random.nextFloat(70,101)  * 10) / 10.0f; //Upper limit is 100, lower limit is 1, random for each new instance from 70-100
        emotional_value = Math.round(((((energy/50)+(hunger_level/25))/3) + (mood_level/12.5)) * 10.0)/10.0; //needs testing, also needs enum Emotional Value

        activity_multiplier = new float[]{ // Multipliers go from -100 to 100, eg. (Value = 10, possible range becomes -10 to 10)
            Math.round(random.nextFloat(-100.0f,100.0f)  * 10) / 10.0f,
            Math.round(random.nextFloat(-100.0f,100.0f)  * 10) / 10.0f,
            Math.round(random.nextFloat(-100.0f,100.0f)  * 10) / 10.0f
        };

        food_multiplier = new float[]{
            Math.round(random.nextFloat(-100.0f,100.0f)  * 10) / 10.0f,
            Math.round(random.nextFloat(-100.0f,100.0f)  * 10) / 10.0f,
            Math.round(random.nextFloat(-100.0f,100.0f)  * 10) / 10.0f
        };
    }

    public void updateEmotionalValue(){
        emotional_value = Math.round(((((energy/50)+(hunger_level/25))/3) + (mood_level/12.5)) * 10.0)/10.0;
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
                System.err.println("\nActivity " + x + ": " + myPets.activity_multiplier[i]);
            }
            System.err.println("Food Multipliers: ");
            for (int i = 0; i < myPets.food_multiplier.length; i++){
                int x = i+1;
                System.err.println("\nFood " + x + ": " + myPets.food_multiplier[i]);
            }

            
        }
        
    }

    public void setName(String n) {
        name = n;
    }

    public void setSpecies(String n) {
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
    public String getSpecies(){
        return species;
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

    public float getActivityMultiplier(int n){
        return activity_multiplier[n];
    }

    public float getFoodMultiplier(int n){
        return food_multiplier[n];
    }





}
