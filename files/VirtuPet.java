package files;

public class VirtuPet {

    public static void main(String[] args) { //just testing the output of Pet class, not indicative of the final
        Pet test = new Pet();

    System.out.println("Species: " + test.species);
    System.out.println("Emotional Value: " + test.emotional_value);
    System.out.println("Hunger: " + test.hunger_level);
    System.out.println("Mood: " + test.mood_level);
    System.out.println("Energy: " + test.energy);

    System.out.println("\nCurrent Hunger Level: " + test.action_Eat());

    System.out.println("Current Hunger Level: " + test.hunger_level);
    System.out.println("Hunger After Eating: " + test.action_Eat());

    System.out.println("\n\nCurrent Energy: " + test.energy);
    System.out.println("Energy after Resting: " + test.action_Rest());
    System.out.println("Mood: " + test.mood_level);
    





    }
}
