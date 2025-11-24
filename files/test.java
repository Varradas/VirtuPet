import java.io.*;
import java.util.*;
import virtupetClasses.*;

//This file is to purely test how to handle the modification of data in the program. Will be removed in the final version.

public class test {

    public static void main(String[] args) throws IOException, ClassNotFoundException { //just testing the output of classes, not indicative of the final

        Pet[] pets = new Pet[100]; //array to store pet data
        Food[] food = new Food[100]; //array to store food data, probably will be in another file.
        Activity[] activity = new Activity[100];
        Scanner input = new Scanner(System.in);

            // pets[0] = new Pet();
            // pets[0].setName("Bubbles"); 
            // pets[0].setSpecies("Doggo"); 

            // pets[1] = new Pet();
            // pets[1].setName("Drago");
            // pets[1].setSpecies("Cat");

            // pets[2] = new Pet();
            // pets[2].setName("Lemon");
            // pets[2].setSpecies("Parrot");

            // food[0] = new Food();
            // food[0].setFoodName("Chicken");
            // food[0].setFoodValue(5);

            // food[1] = new Food();
            // food[1].setFoodName("Peas");
            // food[1].setFoodValue(3);

            // food[2] = new Food();
            // food[2].setFoodName("Fish");
            // food[2].setFoodValue(8);

            // activity[0] = new Activity();
            // activity[0].setActivityName("Play");
            // activity[0].setActivityValue(7);
            // activity[1] = new Activity();
            // activity[1].setActivityName("Walk");
            // activity[1].setActivityValue(10);

            // activity[2] = new Activity();
            // activity[2].setActivityName("Talk");
            // activity[2].setActivityValue(4);

        OUTER:
        while (true) {
            
            System.out.println("\nChoose Method to Test: ");
            System.out.println("[1] Load Pet Data");
            System.out.println("[2] Load Food and Activity Data");
            System.out.println("[3] Save Pet Data");
            System.out.println("[4] Save Food, Activity Data");
            System.out.println("\n[5] Modify Pet Data");
            System.out.println("[6] Modify Food Data");
            System.out.println("\n[7] Print Pet Data");
            System.out.println("[8] Print Food Data");
            System.out.println("\n[0] Exit Program");
            int method = input.nextInt();

            switch (method) {
                case 0 -> {
                    System.out.print("Exiting Program... ");
                    input.close();
                    break OUTER;
                }
                case 1 -> pets = Action.loadPetData(pets);
                case 2 -> {
                    food = Action.loadFoodData(food);
                    activity = Action.loadActivityData(activity);
                }
                case 3 -> Action.savePetData(pets);
                case 4 -> {
                    Action.saveFoodData(food);
                    Action.saveActivityData(activity);
                }
                case 5 -> {
                    int y= 2;
                    int x = 0;
                    int z = 1;
                    System.out.println("Feeding " + pets[y].getPetName() + "...");
                    System.out.println("Current Hunger Level: " + pets[0].getHunger());
                    System.out.println("Hunger Multiplier: " + pets[y].getFoodMultiplier(0));
                    System.out.println("Chosen Food: " + food[0].getFoodName() + ": "+ food[0].getFoodValue());
                    Action.Eat(pets[y], food[0], 0);
                    System.out.println("After Eating Hunger Level: " + pets[y].getHunger());

                    System.out.println("\nCurrently Resting: " + pets[x].getPetName() + "...");
                    System.out.println("Current Energy Level: " + pets[x].getEnergy());
                    System.out.println("Current Mood Level: " + pets[x].getMood());
                    Action.Rest(pets[x]);
                    System.out.println("After Resting Energy Level: " + pets[x].getEnergy());
                    System.out.println("After Resting Mood Level: " + pets[x].getMood());

                    System.out.println("\nPlaying with " + pets[z].getPetName() + "...");
                    System.out.println("Chosen Activity: " + activity[z].getActivityName());
                    System.out.println("Current Energy Level: " + pets[z].getEnergy());
                    System.out.println("Current Mood Level: " + pets[z].getMood());
                    Action.Play(pets[z], activity[z], z);
                    System.out.println("After Playing Energy Level: " + pets[z].getEnergy());
                    System.out.println("After Playing Mood Level: " + pets[z].getMood());
                }
                case 6 -> {
                    break OUTER; //Need to work on deleting and adding new pets/food
                }
                case 7 -> {
                    if (pets[0] != null){
                        pets[0].displayAllPetDetails(pets);
                    } else {
                        System.err.println("""  
                                                \u001b[38;5;196m
                                \nError: No pet data loaded, Please load pet data first.\n
                                                \u001b[0m""");
                    }
                }
                case 8 -> {
                    if (food[0] != null && activity[0] != null){
                        food[0].displayFoodDetails(food);
                        activity[0].displayActivityDetails(activity);
                    } else {
                        System.out.println("""  
                                                \u001b[38;5;196m
                                \nError: No food nor Activity data loaded, Please load food data first.\n
                                                \u001b[0m""");
                    }
                }
                default -> {
                    System.out.println("\nInvalid input. Please try again.\n");
                }
            }
            
            
        }
    }

    // static Pet[] loadPetData(Pet[] pets){        
    //     ObjectInputStream in = null;
    //     try {
    //         //Deserialize petData.ser to pet array
    //         String filepath = "D:\\OOP Final Project\\files\\data\\petData.ser";
    //         try (FileInputStream fileIn = new FileInputStream(filepath)) {
    //             in = new ObjectInputStream(fileIn);
    //             pets = (Pet[]) in.readObject();
    //             in.close();
    //         }
    //         System.out.println("""  
    //                                             \u001b[38;5;46m
    //                             \nPet Data Loaded Succesfully!\n
    //                                             \u001b[0m""");
    //         return pets;
    //     } catch (IOException | ClassNotFoundException ex) {
    //         System.out.println("""  
    //                                             \u001b[38;5;196m
    //                             \nNo Pet Data to Load.\n
    //                                             \u001b[0m""");
    //         return pets;
    //     } finally {
    //         if (in != null){
    //             try {
    //                 in.close();
    //             } catch (IOException ex) {
    //             }
    //         }
    //     }
    // }

    // static Food[] loadFoodData(Food[] food){        
    //     ObjectInputStream in = null;
    //     try {
    //         //Deserialize foodData.ser to pet array
    //         String filepath = "D:\\OOP Final Project\\files\\data\\foodData.ser";
    //         try (FileInputStream fileIn = new FileInputStream(filepath)) {
    //             in = new ObjectInputStream(fileIn);
    //             food = (Food[]) in.readObject();
    //             in.close();
    //         }
    //         System.out.println("""  
    //                                             \u001b[38;5;46m
    //                             \nFood Data Loaded Succesfully!\n
    //                                             \u001b[0m""");
    //         return food;
    //     } catch (IOException | ClassNotFoundException ex) {
    //         System.out.println("""  
    //                                             \u001b[38;5;196m
    //                             \nNo Food Data to Load.\n
    //                                             \u001b[0m""");
    //         return food;
    //     } finally {
    //         if (in != null){
    //             try {
    //                 in.close();
    //             } catch (IOException ex) {
    //             }
    //         }
    //     }
    // }

    // static Activity[] loadActivityData(Activity[] activity){        
    //     ObjectInputStream in = null;
    //     try {
    //         //Deserialize foodData.ser to pet array
    //         String filepath = "D:\\OOP Final Project\\files\\data\\activityData.ser";
    //         try (FileInputStream fileIn = new FileInputStream(filepath)) {
    //             in = new ObjectInputStream(fileIn);
    //             activity = (Activity[]) in.readObject();
    //             in.close();
    //         }
    //         System.out.println("""  
    //                                             \u001b[38;5;46m
    //                             \nActivity Data Loaded Succesfully!\n
    //                                             \u001b[0m""");
    //         return activity;
    //     } catch (IOException | ClassNotFoundException ex) {
    //         System.out.println("""  
    //                                             \u001b[38;5;196m
    //                             \nNo Activity Data to Load.\n
    //                                             \u001b[0m""");
    //         return activity;
    //     } finally {
    //         if (in != null){
    //             try {
    //                 in.close();
    //             } catch (IOException ex) {
    //             }
    //         }
    //     }
    // }

    // static void savePetData(Pet[] pets){         
    //     ObjectOutputStream out = null;
        
    //     if (pets[0] == null){
    //         System.out.println("""  
    //                                             \u001b[38;5;196m
    //                             \nNo Pet Data to Save.\n
    //                                             \u001b[0m""");
    //         return;
    //     }

    //     try {
    //         //Serialize pet array to petData.ser
    //         String filepath = "D:\\OOP Final Project\\files\\data\\petData.ser";
    //         try (FileOutputStream fileOut = new FileOutputStream(filepath)) {
    //             out = new ObjectOutputStream(fileOut);
    //             out.writeObject(pets);
    //             out.close();
    //         }
    //         System.out.println("""  
    //                                             \u001b[38;5;46m
    //                             \nPet Data Saved.\n
    //                                             \u001b[0m""");
    //     } catch (IOException ex) {
    //     } finally {
    //         try {
    //             out.close();
    //         } catch (IOException ex) {
    //         }
    //     }
    // }

    // static void saveFoodData(Food[] food){         
    //     ObjectOutputStream out = null;

    //     if (food[0] == null){
    //         System.out.println("""  
    //                                             \u001b[38;5;196m
    //                             \nNo Food Data to Save.\n
    //                                             \u001b[0m""");
    //         return;
    //     }

    //     try {
    //         //Serialize pet array to foodData.ser
    //         String filepath = "D:\\OOP Final Project\\files\\data\\foodData.ser";
    //         try (FileOutputStream fileOut = new FileOutputStream(filepath)) {
    //             out = new ObjectOutputStream(fileOut);
    //             out.writeObject(food);
    //             out.close();
    //         }
    //         System.out.println("""  
    //                                             \u001b[38;5;46m
    //                             \nFood Data Saved.\n
    //                                             \u001b[0m""");
    //     } catch (IOException ex) {
    //     } finally {
    //         try {
    //             out.close();
    //         } catch (IOException ex) {
    //         }
    //     }
    // }

    // static void saveActivityData(Activity[] activity){         
    //     ObjectOutputStream out = null;

    //     if (activity[0] == null){
    //         System.out.println("""  
    //                                             \u001b[38;5;196m
    //                             \nNo Activity Data to Save.\n
    //                                             \u001b[0m""");
    //         return;
    //     }

    //     try {
    //         //Serialize pet array to foodData.ser
    //         String filepath = "D:\\OOP Final Project\\files\\data\\activityData.ser";
    //         try (FileOutputStream fileOut = new FileOutputStream(filepath)) {
    //             out = new ObjectOutputStream(fileOut);
    //             out.writeObject(activity);
    //             out.close();
    //         }
    //         System.out.println("""  
    //                                             \u001b[38;5;46m
    //                             \nActivity Data Saved.\n
    //                                             \u001b[0m""");
    //     } catch (IOException ex) {
    //     } finally {
    //         try {
    //             out.close();
    //         } catch (IOException ex) {
    //         }
    //     }
    // }
}

