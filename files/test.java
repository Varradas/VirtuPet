import java.io.*;
import java.util.*;
import virtupetClasses.*;

//This file is to purely test how to handle the modification of data in the program. Will be removed in the final version.

public class test {

    public static void main(String[] args) throws IOException, ClassNotFoundException { //just testing the output of classes, not indicative of the final

        ArrayList<Pet> pets = new ArrayList<>();

        Scanner input = new Scanner(System.in);

        pets.add(new Dog("Bubbles", Species.DOG));
        pets.add(new Cat("Mia", Species.CAT));

        OUTER:
        while (true) {
            
            System.out.println("\nChoose Method to Test: ");
            System.out.println("[1] Load Pet Data");
            System.out.println("[2] Save Pet Data");
            System.out.println("\n[3] Modify Pet Data");
            System.out.println("[4] Print Sprite");
            System.out.println("\n[5] Print Pet Data");
            System.out.println("[6] Print Food Data");
            System.out.println("\n[0] Exit Program");
            int method = input.nextInt();

            switch (method) {
                case 0 -> {
                    System.out.print("Exiting Program... ");
                    input.close();
                    break OUTER;
                }
                case 1 -> pets = Action.loadPetData(pets);
                case 2 -> Action.savePetData(pets);
                case 3 -> {
                    int y= 2;
                    int x = 0;
                    int z = 1;
                    //Testing Eat Function
                    System.out.println("Feeding " + pets.get(y).getPetName() + "...");
                    System.out.println("Current Hunger Level: " + pets.get(y).getHunger());
                    System.out.println("Hunger Multiplier: " + pets.get(y).getFoodMultiplier(0));
                    System.out.println("Chosen Food: " + Food.CHICKEN + ": "+ Food.CHICKEN.foodValue);
                    Action.Eat(pets.get(y), Food.CHICKEN);
                    System.out.println("After Eating Hunger Level: " + pets.get(y).getHunger());

                    //Testing Rest Function
                    System.out.println("\nCurrently Resting: " + pets.get(x).getPetName() + "...");
                    System.out.println("Current Energy Level: " + pets.get(x).getEnergy());
                    System.out.println("Current Mood Level: " + pets.get(x).getMood());
                    Action.Rest(pets.get(x));
                    System.out.println("After Resting Energy Level: " + pets.get(x).getEnergy());
                    System.out.println("After Resting Mood Level: " + pets.get(x).getMood());

                    //Testing Activity Function
                    System.out.println("\nPlaying with " + pets.get(z).getPetName() + "...");
                    System.out.println("Chosen Activity: " + Activity.Dog.FETCH);
                    System.out.println("Current Energy Level: " + pets.get(z).getEnergy());
                    System.out.println("Current Mood Level: " + pets.get(z).getMood());
                    Action.Play(pets.get(z), Activity.Dog.FETCH);
                    System.out.println("After Playing Energy Level: " + pets.get(z).getEnergy());
                    System.out.println("After Playing Mood Level: " + pets.get(z).getMood());
                }
                case 4 -> { //Testing ANSI
                    Action.runIdleAnimation(pets.get(1));
                }
                case 5 -> {
                    if (!pets.isEmpty()){
                        Action.playAudio("statDown");
                        Action.displayAllPetDetails(pets);
                        // for (Pet pet: pets){
                        //     System.out.println(pet.getPetName());
                        // }
                    } else {
                        System.err.println("""  
                                                \u001b[38;5;196m
                                \nError: No pet data loaded, Please load pet data first.\n
                                                \u001b[0m""");
                    }
                }
                case 6 -> {
                    for (Food food : Food.values()){
                        System.out.println(food + " = " + food.foodValue);
                    }

                    for (Species species: Species.values()){
                        System.out.println("Species: " + species);

                        for (Activity.ActivityType activity : species.getActivities()){
                            System.out.println(activity + " = " + activity.getActivityValue());
                        }
                    }
                }
                case 7 -> {
                    Action.playAudio("select");
                }
                case 8 -> {
                    Action.playAudio("back");
                }
                case 9 -> {
                    Action.playAudio("statUp");
                }
                default -> {
                    System.out.println("\nInvalid input. Please try again.\n");
                }
            }
            
            
        }
    }
}

