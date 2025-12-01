import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import virtupetClasses.*;
import virtupetClasses.Activity.ActivityType;

public class VirtuPet {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        
        ArrayList<Pet> pet = new ArrayList<>();
        System.out.println("""  
                                                \u001b[38;5;46m
                                \nStarting Program...\n
                                                \u001b[0m""");
        Action.delay(2000);
        Action.loadPetData(pet);
        Action.delay(2000);
        pet = Action.loadPetData(pet);

        while (true) { 
            try (input) {
                displayMainMenu(pet);
                Action.savePetData(pet);
                break;
            }
        }

    }

    public static void displayMainMenu(ArrayList<Pet> pet) throws IOException{
        while(true){
            System.out.println("\u001b[1J");
            System.out.println("\nWelcome to VirtuPet");
            System.out.println("\nAvailable Actions:");
            System.out.println("[1] View My Pets");
            System.out.println("[2] Adopt a New Pet");
            System.out.println("[3] Raise Your Pets");
            System.out.println("[0] Exit Program");
            System.out.print("\nAction: ");

            try {
                int choice = input.nextInt();
                switch (choice){
                    case 1 ->  {
                        if (pet.isEmpty()){
                            System.out.println("You Have No Pets to View! Get Yourself Some New Pets!");
                            Action.delay(2000);
                        }else {
                            displayMyPets(pet); //done
                        }
                    }
                    case 2 -> displayNewPetMenu(pet); //Needs Testing
                    case 3 -> displayPetCareMenu(pet); //Not Finished
                    case 0 -> {return;}
                    default -> throw new IllegalArgumentException("Invalid choice");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void displayMyPets(ArrayList<Pet> pet){
        while (true) {
            System.out.println("\u001b[1J");
            int x = 0;
            System.out.println("\nMy Pets: \n");
            for (Pet pets: pet){
                x++;
                System.out.println("\n");
                Action.printSprite(pets.getSpecies(), 0);
                System.out.println("\t\t\t["+ x + "]");
                System.out.println("Name: " + pets.getPetName() + "\t\t\t Hunger Level: " + pets.getHunger());
                System.out.println("Species: " + pets.getSpecies() + "\t\t\t Energy Level: " + pets.getEnergy());
                System.out.println("Emotional State: " + pets.getEmotionalState() + "\t Mood Level: " + pets.getMood());
            }

            try {
                System.out.println("\n\t\t\tActions:");
                System.out.println("[1] Unadopt Pet (why)");
                System.out.println("[0] Go Back");
                System.out.print("\nAction: ");
                int choice = input.nextInt();
                switch (choice){
                    case 1 -> {
                        System.out.print("Choose Pet to Unadopt [Select Their Number]: ");
                        int petToUnadopt = input.nextInt();
                        String msg1 = "Goodbye, " + pet.get(petToUnadopt-1).getPetName() + "...";
                        System.out.println("\n");

                        for (char c: msg1.toCharArray()){
                            System.out.print(c);
                            Action.delay(100);
                        }

                        Action.delay(3000);
                        pet.remove(petToUnadopt-1); 
                    }
                    case 0 -> {return;}
                    default -> throw new IllegalArgumentException("Invalid choice");

                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void displayNewPetMenu(ArrayList<Pet> pet){
        while (true) {
            System.out.println("\u001b[1J");
            System.out.println("Choose What Kind of Pet You Want: \n");
            int x = 0;
            for (Species species : Species.values()){
                x++;
                System.out.println("[" + x + "] " + species);
            }
            try {
                System.out.print("Species of Pet [0 To Cancel]: ");
                int choice = input.nextInt();
                if (choice == 0){
                    return;
                }else if (choice > x || choice < 0){
                    System.out.print("""  
                                                \u001b[38;5;196m
                                \nInvalid Choice. \u001b[0m""" );
                    Action.delay(2000);
                    return;
                }
                input.nextLine();
                System.out.print("Name of Your Pet [0 To Cancel]: ");
                String name = input.nextLine();

                switch (choice){
                    case 0 -> {return;}
                    case 1 -> {pet.add(new Dog(name, Species.DOG)); }
                    case 2 -> {pet.add(new Cat(name, Species.CAT)); }
                    case 3 -> {pet.add(new Pet(name, Species.BIRD)); }
                    case 4 -> {pet.add(new Pet(name, Species.RABBIT)); } 
                    case 5 -> {pet.add(new Pet(name, Species.HAMSTER)); }
                    case 6 -> {pet.add(new Pet(name, Species.GUINEA_PIG)); }
                    case 7 -> {pet.add(new Pet(name, Species.GECKO)); }
                    default -> {System.out.print("""  
                                                \u001b[38;5;196m
                                \nInvalid Choice. \u001b[0m""" );
                                Action.delay(2000); throw new IllegalArgumentException();}
                }

                Action.runIdleAnimation(pet.get(pet.size()-1));
                String msg1 = "Congratualtions on Your New Pet!";
                String msg2 = "Welcome " + pet.get(pet.size()-1).getPetName() + "!";

                for (char c: msg1.toCharArray()){
                    System.out.print(c);
                    Action.delay(100);
                }
                System.out.println("\n");
                for (char c: msg2.toCharArray()){
                    System.out.print(c);
                    Action.delay(100);
                }

                Action.delay(3000);
                
                return;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void displayPetCareMenu(ArrayList<Pet> pet){
        while (true) {
            System.out.println("\u001b[1J");
            int x = 0;
            System.out.println("Choose One of Your Pets to Care For: \n");
            for (Pet pets: pet){
                x++;
                System.out.println("\n");
                Action.printSprite(pets.getSpecies(), 0);
                System.out.println("\t\t\t["+ x + "]");
                System.out.printf("%-18s %-20s %-20s%n", "Name:", pets.getPetName(), "Hunger Level: " + pets.getHunger());
                System.out.printf("%-18s %-20s %-20s%n", "Species:", pets.getSpecies(), "Energy Level: " + pets.getEnergy());
                System.out.printf("%-18s %-20s %-20s%n", "Emotional State:", pets.getEmotionalState(), "Mood Level: " + pets.getMood());
            }

            try {
                System.out.print("\nChosen Pet for Care [Select Their Number] [0 To Go Back]: ");
                int choice = input.nextInt();
                
                if (choice <= 0){
                    return;
                }else if (choice-1 >= pet.size()){
                    System.out.print("""  
                                                \u001b[38;5;196m
                                \nInvalid Choice, Please Try Again.  \u001b[0m""");
                    Action.delay(3000); 
                    continue;
                }

                Pet chosenPet = pet.get(choice-1);
                displayActionMenu(chosenPet, pet);

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

        }
    }

    public static void displayActionMenu(Pet pet, ArrayList<Pet> myPets){
        while (true) { 
            System.out.println("\u001b[1J");
            System.out.println("\n");
            Action.runIdleAnimation(pet);
            System.out.printf("%-18s %-20s %-20s%n", "Name:", pet.getPetName(), "Hunger Level: " + pet.getHunger());
            System.out.printf("%-18s %-20s %-20s%n", "Species:", pet.getSpecies(), "Energy Level: " + pet.getEnergy());
            System.out.printf("%-18s %-20s %-20s%n", "Emotional State:", pet.getEmotionalState(), "Mood Level: " + pet.getMood());

            System.out.println("[1] Rest");
            System.out.println("[2] Feed");
            System.out.println("[3] Do Activity");
            System.out.println("[0] Back");
            System.out.print("\nAction: ");
            try {
                int choice = input.nextInt();
                switch (choice){
                    case 1 -> {Action.Rest(pet);
                        for (Pet pets: myPets){
                            if (pets != pet){
                                pets.updateStatsWhenTimePass(myPets, pets);
                            }
                        }
                    }
                    case 2 -> displayFeedMenu(myPets, pet);
                    case 3 -> displayActivityMenu(myPets, pet);
                    case 0 -> {return;}
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void displayFeedMenu(ArrayList<Pet> myPets, Pet pet){
        while (true) {
            System.out.println("\u001b[1J");
            System.out.println("Available Food: ");
            int x = 0;
            for (Food food : Food.values()){
                x++;
                System.out.println("["+ x + "] " + food + " \t " + "Base Value: "+ food.foodValue);
            }
            System.out.print("\nChoose Food to Feed " + pet.getPetName() + "[0 To Go Back]: ");
            try {
                int choice = input.nextInt();
                switch (choice){
                    case 1 -> Action.Eat(pet, Food.CHICKEN);
                    case 2 -> Action.Eat(pet, Food.PEAS);
                    case 3 -> Action.Eat(pet, Food.FISH);
                    case 0 -> {return;}
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
            for (Pet pets: myPets){
                if (pets != pet){
                    pets.updateStatsWhenTimePass(myPets, pets);
                }
            }
        }
    }

    public static void displayActivityMenu(ArrayList<Pet> myPets, Pet pet){
        while (true) {
            System.out.println("\u001b[1J");
            System.out.println("Available Activities for " + pet.getPetName() + ":");
            ActivitySelection.displayActivities(pet.getSpecies().name());

            System.out.print("\nChoose Activity to do with " + pet.getPetName() + " [0 To Go Back]: ");
            try {
                int choice = input.nextInt();

                if (choice == 0){
                    return;
                }
            
                ActivityType activity = ActivitySelection.getActivityByNumber(pet.getSpecies().name(), choice);
                
                Action.Play(pet, activity);

                for (Pet pets: myPets){
                    if (pets != pet){
                        pets.updateStatsWhenTimePass(myPets, pets);
                    }
                }
                
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
