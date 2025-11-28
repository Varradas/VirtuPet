import java.io.*;
import java.util.*;
import virtupetClasses.*;

//This file is to purely test how to handle the modification of data in the program. Will be removed in the final version.

public class test {

    public static void main(String[] args) throws IOException, ClassNotFoundException { //just testing the output of classes, not indicative of the final

        Pet[] pets = new Pet[100]; //array to store pet data
        Scanner input = new Scanner(System.in);

        pets[0] = new Pet();
        pets[0].setName("Bubbles"); 
        pets[0].setSpecies(Species.DOG); 

        pets[1] = new Pet();
        pets[1].setName("Drago");
        pets[1].setSpecies(Species.CAT);

        pets[2] = new Pet();
        pets[2].setName("Lemon");
        pets[2].setSpecies(Species.BIRD);

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
                    System.out.println("Feeding " + pets[y].getPetName() + "...");
                    System.out.println("Current Hunger Level: " + pets[y].getHunger());
                    System.out.println("Hunger Multiplier: " + pets[y].getFoodMultiplier(0));
                    System.out.println("Chosen Food: " + Food.CHICKEN + ": "+ Food.CHICKEN.foodValue);
                    Action.Eat(pets[y], Food.CHICKEN);
                    System.out.println("After Eating Hunger Level: " + pets[y].getHunger());

                    //Testing Rest Function
                    System.out.println("\nCurrently Resting: " + pets[x].getPetName() + "...");
                    System.out.println("Current Energy Level: " + pets[x].getEnergy());
                    System.out.println("Current Mood Level: " + pets[x].getMood());
                    Action.Rest(pets[x]);
                    System.out.println("After Resting Energy Level: " + pets[x].getEnergy());
                    System.out.println("After Resting Mood Level: " + pets[x].getMood());

                    //Testing Activity Function
                    System.out.println("\nPlaying with " + pets[z].getPetName() + "...");
                    System.out.println("Chosen Activity: " + Activity.WALK);
                    System.out.println("Current Energy Level: " + pets[z].getEnergy());
                    System.out.println("Current Mood Level: " + pets[z].getMood());
                    Action.Play(pets[z], Activity.WALK);
                    System.out.println("After Playing Energy Level: " + pets[z].getEnergy());
                    System.out.println("After Playing Mood Level: " + pets[z].getMood());
                }
                case 4 -> { //Testing ANSI
                    Action.runIdleAnimation(pets[1]);
                }
                case 5 -> {
                    if (pets[0] != null){
                        pets[0].displayAllPetDetails(pets);
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
                    for (Activity activity : Activity.values()){
                        System.out.println(activity + " = " + activity.activityValue);
                    }
                }
                default -> {
                    System.out.println("\nInvalid input. Please try again.\n");
                }
            }
            
            
        }
    }
}

