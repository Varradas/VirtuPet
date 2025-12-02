package virtupetClasses;

import java.util.ArrayList;
import java.util.Random;

public final class Cat extends Pet{

    public Cat(String name, Species species) {
        super(name, species);
        Random random = new Random();
        this.hunger_level = Math.round(random.nextFloat(90,101) * 10) / 10.0f; //90-100
        this.mood_level = Math.round(random.nextFloat(50,61)  * 10) / 10.0f; //50-61
        this.energy = Math.round(random.nextFloat(50,71)  * 10) / 10.0f; //50-71
        updateEmotionalState();
    }

    @Override
    public void updateEmotionalState(){ //Not final, needs to be overridden for each species
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

    @Override
    public void updateStatsWhenTimePass(ArrayList<Pet> mypets, Pet pet){
        float energyDecayRate = 0.5f + ((100-hunger_level)/100) * 10.0f;
        energy = Math.round((energy-energyDecayRate) * 10.0f)/10.0f;

        float hungerDecayRate = 0.5f +((100-energy)/100) * 0.5f;
        hunger_level = Math.round((hunger_level-hungerDecayRate) * 10.0f)/10.0f;

        float moodDecayRate = 0.5f + ((100-energy)/100) * 1.0f;
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

        if(energy <= 0 && hunger_level <= 0 && mood_level <= 0){
            Action.triggerDeath(mypets, pet);
        }

    }
    
}
