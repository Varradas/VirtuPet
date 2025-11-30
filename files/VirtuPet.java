import java.util.ArrayList;
import java.util.Scanner;
import virtupetClasses.*;
import virtupetClasses.Activity.ActivityType;

public class VirtuPet {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        
        ArrayList<Pet> pet = new ArrayList<>();
        Action.loadPetData(pet);

        while (true) { 
            try {
                displayMainMenu(pet);
                Action.savePetData(pet);
                break;
            } finally{
                input.close();
            }
        }

    }

    public static void displayMainMenu(ArrayList<Pet> pet){
        while(true){
            System.out.println("\nWelcome to VirtuPet");
            System.out.println("\nAvailable Actions:");
            System.out.println("[1] View My Pets");
            System.out.println("[2] Get a New Pet");
            System.out.println("[3] Raise Your Pets");
            System.out.println("[0] Exit Program");

            try {
                int choice = input.nextInt();
                switch (choice){
                    case 1 ->  {
                        if (pet.isEmpty()){
                            System.out.println("You Have No Pets to View! Get Yourself Some New Pets!");
                        }else {
                            displayMyPets(pet); //Not Finished
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

    }

    public static void displayNewPetMenu(ArrayList<Pet> pet){
        while (true) {
            System.out.println("Choose What Kind of Pet You Want: \n");
            int x = 0;
            for (Species species : Species.values()){
                x++;
                System.out.println("[" + x + "] " + species);
            }
            try {
                System.out.print("Species of Pet [0 To Cancel]: ");
                int choice = input.nextInt();
                input.nextLine();
                System.out.print("Name of Your Pet [0 To Cancel]: ");
                String name = input.nextLine();

                switch (choice){
                    case 0 -> {return;}
                    case 1 -> {pet.add(new Pet(name, Species.DOG)); Action.savePetData(pet);}
                    case 2 -> {pet.add(new Pet(name, Species.CAT)); Action.savePetData(pet);}
                    case 3 -> {pet.add(new Pet(name, Species.BIRD)); Action.savePetData(pet);}
                    case 4 -> {pet.add(new Pet(name, Species.RABBIT)); Action.savePetData(pet);} 
                    case 5 -> {pet.add(new Pet(name, Species.HAMSTER)); Action.savePetData(pet);}
                    case 6 -> {pet.add(new Pet(name, Species.GUINEA_PIG)); Action.savePetData(pet);}
                    case 7 -> {pet.add(new Pet(name, Species.GECKO)); Action.savePetData(pet);}
                    default -> throw new IllegalArgumentException("Invalid choice");
                }

                Action.runIdleAnimation(pet.get(pet.size()-1));
                System.out.println("\t\tCongratualtions on Your New Pet!");
                System.out.println("\tWelcome " + pet.get(pet.size()-1).getPetName() + "!");
                return;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void displayPetCareMenu(ArrayList<Pet> pet){
        while (true) {
            System.out.println("Chosen");
        }
    }

    public void displayActionMenu(Pet pet){
        while (true) { 
            System.out.println("[1] Rest");
            System.out.println("[2] Feed");
            System.out.println("[3] Do Activity");
            System.out.println("[0] Back");
            System.out.print("\nInput: ");
            try {
                int choice = input.nextInt();
                switch (choice){
                    case 1 -> Action.Rest(pet);
                    case 2 -> displayFeedMenu(pet);
                    case 3 -> displayActivityMenu(pet);
                    case 0 -> {return;}
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public void displayFeedMenu(Pet pet){
        while (true) {
            System.out.println("Available Food: ");
            int x = 0;
            for (Food food : Food.values()){
                x++;
                System.out.println("["+ x + "] " + food + " \t " + "Base Value: "+ food.foodValue);
            }

            System.out.print("\nChoose Food to Feed " + pet.getPetName() + ": ");
            try {
                int choice = input.nextInt();
                switch (choice){
                    case 1 -> Action.Eat(pet, Food.CHICKEN);
                    case 2 -> Action.Eat(pet, Food.FISH);
                    case 3 -> Action.Eat(pet, Food.PEAS);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public void displayActivityMenu(Pet pet){
        while (true) {
            System.out.println("Available Activities for " + pet.getPetName() + ":");
            ActivitySelection.displayActivities(pet.getSpecies());

            System.out.print("\nChoose Activity to do with " + pet.getPetName() + ": ");
            try {
                int choice = input.nextInt();
            
                ActivityType activity = ActivitySelection.getActivityByNumber(pet.getSpecies(), choice);
                
                Action.Play(pet, activity);
                
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
