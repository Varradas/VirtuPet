//Note before modifying: When this file is modified, the prior .ser file will no longer work.

package virtupetClasses;

import java.io.Serializable;
import java.util.Random;
import virtupetClasses.Activity.ActivityType;

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

    public Pet(String name, Species species) { //Test out the RNG for hunger, mood and level,
        SpeciesActivitySelector speciesActivity = new SpeciesActivitySelector();
        Random random = new Random();
        Activity.ActivityType[] activities = (ActivityType[]) speciesActivity.getSpeciesActivities(species);
        Food[] food = Food.values();

        this.name = name;
        this.species = species;

        hunger_level = Math.round(random.nextFloat(70,101) * 10) / 10.0f; //Upper limit is 100, lower limit is 1, random for each new instance from 70-100
        mood_level = Math.round(random.nextFloat(40,61)  * 10) / 10.0f; //Upper limit is 100, lower limit is 1, random for each new instance from 40-60
        energy = Math.round(random.nextFloat(70,101)  * 10) / 10.0f; //Upper limit is 100, lower limit is 1, random for each new instance from 70-100
        updateEmotionalState();
        
        activity_multiplier = new Object[activities.length][2];
        food_multiplier = new Object[food.length][2];

        for (int i = 0; i < activities.length; i++) {
            activity_multiplier[i][0] = activities[i];  // Activity name
            activity_multiplier[i][1] = Math.round(random.nextFloat(-100.0f, 100.0f) * 10) / 10.0f;  // Random value
        }

        for (int i = 0; i < food.length; i++) {
            food_multiplier[i][0] = food[i];  // Activity name
            food_multiplier[i][1] = Math.round(random.nextFloat(1.0f, 100.0f) * 10) / 10.0f;  // Random value
        }
    }

    public final void updateEmotionalState(){
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

    public double getEmotionalValue(){
        return emotional_value;
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

    public ActivityType getActivityName(int n){
        if (activity_multiplier[n][0] instanceof Activity.ActivityType activityName) {
            return activityName;
        }
        throw new IllegalStateException("Expected String at index " + n);
    }

    public Food getFoodName(int n){
        if (food_multiplier[n][0] instanceof Food foodName) {
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
