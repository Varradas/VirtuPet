import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import virtupetClasses.*;
import virtupetClasses.Activity.ActivityType;
import virtupetClasses.PetSubclasses.*;

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
        Action.playAudio("bgMusic", true);
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
            System.out.println("\n\t *══ Welcome to VirtuPet ══*");
            System.out.println("\n\t     Available Actions:");
            System.out.println("\t     [1] View My Pets");
            System.out.println("\t     [2] Adopt a New Pet");
            System.out.println("\t     [3] Raise Your Pets");
            System.out.println("\t     [4] Guide");
            System.out.println("\t     [0] Exit Program");
            System.out.print("\nAction: ");

            try {
                int choice = input.nextInt();
                switch (choice){
                    case 1 ->  {
                        if (pet.isEmpty()){
                            Action.playAudio("error");
                            System.out.println("You Have No Pets to View! Get Yourself Some New Pets!");
                            Action.delay(2000);
                        }else {
                            Action.playAudio("select");
                            displayMyPets(pet); //done
                        }
                    }
                    case 2 -> {Action.playAudio("select"); displayNewPetMenu(pet);} //Needs Testing
                    case 3 -> {Action.playAudio("select"); displayPetCareMenu(pet);} //Not Finished
                    case 4 -> {Action.playAudio("select"); displayHelpMenu();} //todo
                    case 0 -> {Action.playAudio("back"); Action.delay(500);return;}
                    default -> throw new IllegalArgumentException("Invalid choice");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void displayHelpMenu(){ //Unfinished
        while (true) {

            System.out.println("\n\t\t\t\t\t\t════════ Guide Menu ════════");

            System.err.println("""

                Hello!
                
                Welcome to VirtuPet, a simple game simulating caretaking of a variety of different pets, each with their own temperaments.

                That last part is the important part, in this game, EVERY pet will behave differently from other pets, even from their own species!
                Some pets will love every kind of food you give them, some will just hate one specific activity, or love only one activity!
                If your unlucky, you might get a pet that hates everything! You might consider unadopting them at that point...

                --Basic Controls--

                Input instructions are found inside brackets [], type the corresponding character inside the bracket and press enter to navigate.
                When selecting a pet to start care for, type their corresponding number found under their icon, it's hard to miss!

                --Tips--

                Resting for every pet is RNG, and depending on how good your luck is, your pet might get a big energy boost with mood on top! Or maybe get barely any
                energy and lose some mood...

                Feeding a pet will always improve their hunger, so it's the safest action to give them! 

                Doing any Activity decreases both hunger and energy, meanwhile their mood can either go up or down if they like an activity or not, take note of this,
                as their preferences won't change!

                You might want to be careful having many pets, as when you take care of one pet, the others' stats will go down exponentially, meaning
                the lower it gets, the faster the rate it goes down when you dont give them any love!

                If you do neglect a pet to the point that all their stats fall to zero, they'll die. So be careful!



                That's it! Enjoy Playing!

            """);

            System.out.print("\n[0] Go Back: ");
            try {
                int choice = input.nextInt();
                switch (choice){
                    case 0 -> {Action.playAudio("back"); return;}
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
                        Action.playAudio("select");
                        System.out.print("Choose Pet to Unadopt [Select Their Number]: ");
                        int petToUnadopt = input.nextInt();
                        Action.playAudio("select");
                        String msg1 = "Goodbye, " + pet.get(petToUnadopt-1).getPetName() + "...";
                        System.out.println("\n");

                        for (char c: msg1.toCharArray()){
                            System.out.print(c);
                            Action.delay(100);
                        }

                        Action.delay(3000);
                        pet.remove(petToUnadopt-1);
                        return;
                    }
                    case 0 -> {Action.playAudio("back"); return;}
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
                    Action.playAudio("back");
                    return;
                }else if (choice > x || choice < 0){
                    Action.playAudio("error");
                    System.out.print("""  
                                                \u001b[38;5;196m
                                \nInvalid Choice. \u001b[0m""" );
                    Action.delay(2000);
                    return;
                }
                Action.playAudio("select");
                input.nextLine();
                System.out.print("Name of Your Pet [0 To Cancel]: ");
                String name = input.nextLine();
                if ("0".equals(name)){
                    Action.playAudio("back"); return;
                }
                Action.playAudio("select");

                switch (choice){
                    case 1 -> {pet.add(new Dog(name, Species.DOG)); }
                    case 2 -> {pet.add(new Cat(name, Species.CAT)); }
                    case 3 -> {pet.add(new Bird(name, Species.BIRD)); }
                    case 4 -> {pet.add(new Rabbit(name, Species.RABBIT)); }
                    case 0 -> {Action.playAudio("back"); return;}
                    default -> {Action.playAudio("error");
                                System.out.print("""  
                                                \u001b[38;5;196m
                                \nInvalid Choice. \u001b[0m""" );
                                Action.delay(2000); throw new IllegalArgumentException();}
                }

                Action.runIdleAnimation(pet.get(pet.size()-1));
                String msg1 = "Congratualtions on Your New Pet!";
                String msg2 = "\tWelcome " + pet.get(pet.size()-1).getPetName() + "!";

                for (char c: msg1.toCharArray()){
                    System.out.print(c);
                    Action.delay(50);
                }
                System.out.println("\n");
                for (char c: msg2.toCharArray()){
                    System.out.print(c);
                    Action.delay(50);
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
            if (pet.isEmpty()){
                Action.playAudio("error");
                System.out.println("You Have No Pets to View! Get Yourself Some New Pets!");
                Action.delay(2000);
                return;
            }
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
                    Action.playAudio("back");
                    return;
                }else if (choice-1 >= pet.size()){
                    Action.playAudio("error");
                    System.out.print("""  
                                                \u001b[38;5;196m
                                \nInvalid Choice, Please Try Again.  \u001b[0m""");
                    Action.delay(3000); 
                    continue;
                }
                Action.playAudio("select");
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
                    case 1 -> {
                        Action.playAudio("select");
                        Action.Rest(pet);
                        for (Pet pets: myPets){
                            if (pets != pet){
                                pets.updateStatsWhenTimePass(myPets, pets);
                            }
                        }
                    }
                    case 2 -> {Action.playAudio("select"); displayFeedMenu(myPets, pet);}
                    case 3 -> {Action.playAudio("select"); displayActivityMenu(myPets, pet);}
                   case 0 -> {Action.playAudio("back"); return;}
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
                System.out.println("["+ x + "] " + food);
            }
            System.out.print("\nChoose Food to Feed " + pet.getPetName() + "[0 To Go Back]: ");
            try {
                int choice = input.nextInt();
                switch (choice){
                    case 1 -> {Action.playAudio("select"); Action.Eat(pet, Food.PET_KIBBLE);}
                    case 2 -> {Action.playAudio("select"); Action.Eat(pet, Food.VEGETABLE);}
                    case 3 -> {Action.playAudio("select"); Action.Eat(pet, Food.FRUIT);}
                    case 4 -> {Action.playAudio("select"); Action.Eat(pet, Food.TREAT);}
                    case 5 -> {Action.playAudio("select"); Action.Eat(pet, Food.FISH);}
                    case 6 -> {Action.playAudio("select"); Action.Eat(pet, Food.MEAT);}
                    case 7 -> {Action.playAudio("select"); Action.Eat(pet, Food.CHICKEN);}
                    case 0 -> {Action.playAudio("back"); return;}
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
                    Action.playAudio("back");
                    return;
                }
            
                ActivityType activity = ActivitySelection.getActivityByNumber(pet.getSpecies().name(), choice);
                Action.playAudio("select");
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
