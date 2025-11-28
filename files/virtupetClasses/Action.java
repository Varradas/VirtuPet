package virtupetClasses;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Action{

    public static Pet Eat(Pet pet, Food food){ //Input is the chosen pet and food.
        Object foodInventory[][] = pet.getFoodInventory();
        float foodMultiplier = 0;

        for (int i = 0; i < foodInventory.length; i++){
            for (int j = 0; j < foodInventory[i].length; j++){
                if (foodInventory[i][j] instanceof Food currentItem && currentItem == food) {
                    foodMultiplier = pet.getFoodMultiplier(i);
                }
            }   
        }

        float food_value = food.foodValue * (foodMultiplier/100);
        if ((food_value + pet.getHunger()) >= 100) {
            pet.setHunger(100.0f);
            pet.updateEmotionalState();
            return pet;
        }
        else {
            pet.setHunger(pet.getHunger() + food_value);
            pet.updateEmotionalState();
            return pet;
        }
    }

    public static Pet Play(Pet pet, Activity activity){ //Input is the chosen pet and activity.
        Object activityList[][] = pet.getActivityList();
        float activityMultiplier = 0;

        for (int i = 0; i < activityList.length; i++){
            for (int j = 0; j < activityList[i].length; j++){
                if (activityList[i][j] instanceof Activity currentItem && currentItem == activity) {
                    activityMultiplier = pet.getActivityMultiplier(i);
                }
            }   
        }

        float energy_consumption = ((1/(pet.getMood()+pet.getHunger()))*100)+(Math.abs(activity.activityValue)*(pet.getEnergy()/100)*0.5f);
        float play_value = activity.activityValue * (activityMultiplier/100);
        if ((play_value + pet.getMood()) >= 100) {
            pet.setMood(100.0f);
            pet.setEnergy(pet.getEnergy() - energy_consumption);
            pet.updateEmotionalState();
            return pet;
        }
        else {
            pet.setMood(pet.getMood() + play_value);
            pet.setEnergy(pet.getEnergy() - energy_consumption);
            pet.updateEmotionalState();
            return pet;
        }
    }

    public static Pet Rest(Pet pet){ //Input is chosen Pet
        int restRng = new Random().nextInt(10);
        int rest_value;

        if (restRng < 7){ //70% chance, standard energy value
            rest_value = 30;
        }
        else if (restRng < 9){ // 20% chance, greater energy value
            rest_value = 50;
            pet.setMood(pet.getMood() + 5);
        }
        else { // 10% chance, either greatest energy value or lower energy value
            int chance = new Random().nextInt(10);
            if (chance < 5) { // 50% chance of greatest energy value
                rest_value = 80;
                pet.setMood(pet.getMood() + 10);
            }
            else { // 50% chance of lower energy value
                rest_value = 10;
                pet.setMood(pet.getMood() - 10);
            }
        }

        if (pet.getMood() >= 100) { // Takes into account if mood goes past 100
            pet.setMood(100);
        }
        
        if ((rest_value + pet.getEnergy()) >= 100) {
            pet.setEnergy(100);
            pet.updateEmotionalState();
            return pet;
        }
        else {
            pet.setEnergy(pet.getEnergy() + rest_value);
            pet.updateEmotionalState();
            return pet;
        }
    }

    public static void printSprite(){ //Work in Progress
        String filePath = "sprites\\dog1.txt"; // Replace with the actual path        
        try {
            String content = Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
            System.out.println(content);
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static Pet[] loadPetData(Pet[] pets){        
        ObjectInputStream in = null;
        try {
            //Deserialize petData.ser to pet array
            String filepath = "data\\petData.ser";
            try (FileInputStream fileIn = new FileInputStream(filepath)) {
                in = new ObjectInputStream(fileIn);
                pets = (Pet[]) in.readObject();
                in.close();
            }
            System.out.println("""  
                                                \u001b[38;5;46m
                                \nPet Data Loaded Succesfully!\n
                                                \u001b[0m""");
            return pets;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("""  
                                                \u001b[38;5;196m
                                \nNo Pet Data to Load.\n
                                                \u001b[0m""");
            return pets;
        } finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException ex) {
                }
            }
        }
    }

    public static void savePetData(Pet[] pets){         
        ObjectOutputStream out = null;
        
        if (pets[0] == null){
            System.out.println("""  
                                                \u001b[38;5;196m
                                \nNo Pet Data to Save.\n
                                                \u001b[0m""");
            return;
        }

        try {
            //Serialize pet array to petData.ser
            String filepath = "data\\petData.ser";
            try (FileOutputStream fileOut = new FileOutputStream(filepath)) {
                out = new ObjectOutputStream(fileOut);
                out.writeObject(pets);
                out.close();
            }
            System.out.println("""  
                                                \u001b[38;5;46m
                                \nPet Data Saved.\n
                                                \u001b[0m""");
        } catch (IOException ex) {
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
            }
        }
    }

}