package files;
import java.util.Random;

public class Pet {
    String species;
    double emotional_value;
    float hunger_level;
    float mood_level;
    float energy;

    Pet() { //Test out the RNG for hunger, mood and level, 
        species = "default_stats";

        hunger_level = new Random().nextInt(70,101); //Upper limit is 100, lower limit is 1, random for each new instance from 70-100
        mood_level = new Random().nextInt(40,61); //Upper limit is 100, lower limit is 1, random for each new instance from 40-60
        energy = new Random().nextInt(70,101); //Upper limit is 100, lower limit is 1, random for each new instance from 70-100
        emotional_value = Math.round(((((energy/50)+(hunger_level/25))/3) + (mood_level/12.5)) * 10.0)/10.0; //needs testing, also needs enum Emotional Value
    }

    float action_Eat(){ //NEEDS AN INPUT, CHOSEN FOOD AND FAVORITE MULTIPLIER OF CURRENT PET
        //food_value will change depending on the food, the "1" is a multiplier that changes depending on the pet's preferences.
        int food_value = 20*1;
        if ((food_value + hunger_level) >= 100) {
            hunger_level = 100;
            return hunger_level;
        }
        else {
            return hunger_level + food_value;
        }
    }

    float action_Rest(){ //All done
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
            return energy;
        }
        else {
            return energy + rest_value;
        }
    }

    //void action_Play(){ //NEEDS AN INPUT, CHOSEN PET AND CHOSEN ACTION
        // Maybe an array input, we choose the action's value, and take the corresponding multiplier for that action for the chosen pet. Needs those parts to be done before this gets started.
    //}
}
