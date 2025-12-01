package virtupetClasses;

import java.util.Random;

public final class Dog extends Pet{

    public Dog(String name, Species species) {
        super(name, species);
        Random random = new Random();
        this.hunger_level = Math.round(random.nextFloat(70,101) * 10) / 10.0f; //70-100
        this.mood_level = Math.round(random.nextFloat(60,71)  * 10) / 10.0f; //60-71
        this.energy = Math.round(random.nextFloat(80,101)  * 10) / 10.0f; //80-101
        updateEmotionalState();
    }

    @Override
    public void updateEmotionalState(){ //Not final, needs to be overridden for each species
        emotional_value = Math.round(((((energy/35)+(hunger_level/10.8))/3) + (mood_level/16.7)) * 10.0)/10.0;
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
    
}
