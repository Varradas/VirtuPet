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
    private float[] activity_multiplier; //Bonuses/Penalties in each activity for each pet
    private float[] food_multiplier; //Bonuses/Penalties in each food for each pet

    public Pet() { //Test out the RNG for hunger, mood and level, 
        species = "default_stats";

        hunger_level = new Random().nextInt(70,101); //Upper limit is 100, lower limit is 1, random for each new instance from 70-100
        mood_level = new Random().nextInt(40,61); //Upper limit is 100, lower limit is 1, random for each new instance from 40-60
        energy = new Random().nextInt(70,101); //Upper limit is 100, lower limit is 1, random for each new instance from 70-100
        emotional_value = Math.round(((((energy/50)+(hunger_level/25))/3) + (mood_level/12.5)) * 10.0)/10.0; //needs testing, also needs enum Emotional Value


    }

    public void updateEmotionalValue(){
        emotional_value = Math.round(((((energy/50)+(hunger_level/25))/3) + (mood_level/12.5)) * 10.0)/10.0;
    }

    public void displayAllPetDetails(Pet[] n){
        
        for (Pet myPets : n) { 
            if (myPets == null) {
                System.err.println("""
                                    \u001b[1;31m
                                    Finished Looping through available pets.\u001b[0m""");
                break;
            }
            System.err.println("\nName: " + myPets.name);
            System.err.println("Species: " + myPets.species);
            System.err.println("Emotional Value: " + myPets.emotional_value);
            System.err.println("Hunger: " + myPets.hunger_level);
            System.err.println("Mood: " + myPets.mood_level);
            System.err.println("Energy: " + myPets.energy);
        }
        
    }

    public void setName(String n) {
        name = n;
    }

    public void setSpecies(String n) {
        species = n;
    }
    
    public String getPetName(){
        return name;
    }
    public String getSpecies(){
        return species;
    }









    //actions will probably be moved to their own class.

    public float actionEat(){ //NEEDS AN INPUT, CHOSEN FOOD AND FOOD MULTIPLIER OF CURRENT PET
        //food_value will change depending on the food, the "1" is a multiplier that changes depending on the pet's preferences.
        float food_value = 20*1;
        if ((food_value + hunger_level) >= 100) {
            hunger_level = 100;
            updateEmotionalValue();
            return hunger_level;
        }
        else {
            hunger_level = hunger_level + food_value;
            updateEmotionalValue();
            return hunger_level;
        }
    }

    public float actionRest(){ //All done
        int restRng = new Random().nextInt(10);
        int rest_value;

        if (restRng < 7){ //70% chance, standard energy value
            rest_value = 50;
        }
        else if (restRng < 9){ // 20% chance, greater energy value
            rest_value = 70;
            mood_level = mood_level + 5;
        }
        else { // 10% chance, either greatest energy value or lower energy value
            int chance = new Random().nextInt(10);
            if (chance < 5) { // 50% chance of greatest energy value
                rest_value = 100;
                mood_level = mood_level + 10;
            }
            else { // 50% chance of lower energy value
                rest_value = 20;
                mood_level = mood_level - 10;
            }
        }

        if (mood_level >= 100) { // Takes into account if mood goes past 100
            mood_level = 100;
        }
        
        if ((rest_value + energy) >= 100) {
            energy = 100;
            updateEmotionalValue();
            return energy;
        }
        else {
            energy = energy + rest_value;
            updateEmotionalValue();
            return energy;
        }
    }

    //void actionPlay(){ //NEEDS AN INPUT, CHOSEN PET AND CHOSEN ACTION
        // Maybe an array input, we choose the action's value, and take the corresponding multiplier for that action for the chosen pet. Needs those parts to be done before this gets started.
    //}
}
