//Note before modifying: When this file is modified, the prior .ser file will no longer work.

package virtupetClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import virtupetClasses.Activity.ActivityType;

abstract public class Pet implements Serializable{
    private Species species;
    protected String name;
    protected double emotional_value;
    protected EmotionalState emotional_state;
    protected float hunger_level;
    protected float mood_level;
    protected float energy;
    protected final Object[][] activity_multiplier; //Determines if an activity will increase/decrease stats in each activity for each pet
    protected final Object[][] food_multiplier; //Determines the effectiveness of each food in each food for each pet

    public Pet(String name, Species species) {
        SpeciesActivities speciesActivity = new SpeciesActivities();
        Random random = new Random();
        Activity.ActivityType[] activities = (ActivityType[]) speciesActivity.getSpeciesActivities(species);
        Food[] food = Food.values();

        this.name = name;
        this.species = species;

        this.hunger_level = Math.round(random.nextFloat(70,101) * 10) / 10.0f; //Upper limit is 100, needs to be overridden for each species
        this.mood_level = Math.round(random.nextFloat(40,61)  * 10) / 10.0f; //Upper limit is 100, needs to be overridden for each species
        this.energy = Math.round(random.nextFloat(70,101)  * 10) / 10.0f; //Upper limit is 100, needs to be overridden for each species
        //updateEmotionalState(); Call this inside the subclass instead
        
        this.activity_multiplier = new Object[activities.length][2];
        this.food_multiplier = new Object[food.length][2];

        for (int i = 0; i < activities.length; i++) {
            activity_multiplier[i][0] = activities[i];  // Activity name
            activity_multiplier[i][1] = Math.round(random.nextFloat(-100.0f, 100.0f) * 10) / 10.0f;  // Random value, from -100% to 100% of the activity's value
        }

        for (int i = 0; i < food.length; i++) {
            food_multiplier[i][0] = food[i];  // Food name
            food_multiplier[i][1] = Math.round(random.nextFloat(1.0f, 100.0f) * 10) / 10.0f;  // Random value, from 1% to 100% of the food's value
        }
    }

    public void updateEmotionalState(){ //emotional value equation is changed per species
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
            emotional_state = EmotionalState.CONTENT;
        }else if (emotional_value <= 7.4){
            emotional_state = EmotionalState.INTERESTED;
        }else if (emotional_value <= 8.9){
            emotional_state = EmotionalState.HAPPY;
        }else if (emotional_value <= 9.7){
            emotional_state = EmotionalState.ECSTATIC;
        }
    }

    public void updateStatsWhenTimePass(ArrayList<Pet> mypets, Pet pet){ //Ending multiplier changes per species
        float energyDecayRate = 0.5f + ((100-hunger_level)/100) * 1f;
        energy = Math.round((energy-energyDecayRate) * 10.0f)/10.0f;

        float hungerDecayRate = 0.5f +((100-energy)/100) * 1f;
        hunger_level = Math.round((hunger_level-hungerDecayRate) * 10.0f)/10.0f;

        float moodDecayRate = 0.5f + ((100-energy)/100) * 1f;
        mood_level = Math.round((mood_level-moodDecayRate) * 10.0f)/10.0f;

        if (energy < 0){
            energy = 0.0f;
        }
        if (hunger_level < 0){
            hunger_level = 0.0f;
        }
        if (mood_level < 0){
            mood_level = 0.0f;
        }

        updateEmotionalState();

        if(energy <= 0 && hunger_level <= 0 && mood_level <= 0){ //Unlikely scenario but it's there     ╮(￣▽￣"")╭  
            Action.triggerDeath(mypets, pet);
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

    //Getters Activity and their Multipliers, 0 for name, 1 for value. Important to match appropriate activities per species.

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
