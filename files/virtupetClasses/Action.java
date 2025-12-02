package virtupetClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.*;

public class Action{

    public static void Eat(Pet pet, Food food){ //Input is the chosen pet and food.
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
            food_value = Math.round(((100+food_value)-(pet.getHunger()+food_value))* 10) / 10.0f;
            pet.setHunger(100.0f);
            pet.updateEmotionalState();
        }
        else {
            pet.setHunger(pet.getHunger() + food_value);
            pet.updateEmotionalState();
        }

        String msg1 = "Feeding " + pet.getPetName() + " " + food.name() + "...";
        String msg2 = "Hunger " + "+" + Math.round(food_value*10) /10.0;

        for (char c: msg1.toCharArray()){
            System.out.print(c);
            Action.delay(100);
        }

        Action.delay(2000);

        Action.runIdleAnimation(pet);

        Action.playAudio("statUp");
        for (char c: msg2.toCharArray()){
            System.out.print(c);
            Action.delay(100);
        }

        Action.delay(2000);  

    }

    public static void Play(Pet pet, Activity.ActivityType activity){ //Input is the chosen pet and activity.
        Object activityList[][] = pet.getActivityList();
        float activityMultiplier = 0;
        String audioToPlay;

        for (int i = 0; i < activityList.length; i++){
            for (int j = 0; j < activityList[i].length; j++){ //Loops through the list of the Pet's Activities
                Object currentItem = activityList[i][j]; //Assigns current item to a temporary object
                if (currentItem instanceof Activity.ActivityType) { //Checks if the current item is an Activity.ActivityType
                    Activity.ActivityType currentActivity = (Activity.ActivityType) currentItem; //If so then we cast that object to an Activity.ActivityType
                    if (currentActivity.equals(activity)) { //Then check if that item is equal to the activitytype chosen, if so get the appropriate multiplier.
                        activityMultiplier = pet.getActivityMultiplier(i);
                    }
                }
            }   
        }

        float energy_consumption = ((1/(pet.getMood()+pet.getHunger()))*100)+(Math.abs(activity.getActivityValue())*(pet.getEnergy()/100)*0.5f);
        float play_value = activity.getActivityValue() * (activityMultiplier/100);

        if (play_value < 0){
            audioToPlay = "statDown";
        }else {
            audioToPlay = "statUp";
        }

        if ((play_value + pet.getMood()) >= 100) { //Takes into account if Mood goes beyond 100 or below 0
            pet.setMood(100.0f);
        }else if ((play_value + pet.getMood()) <= 0) {
            pet.setMood(0.0f);
        }else {
            pet.setMood(pet.getMood() + play_value);
        }

        if ((pet.getHunger() - (energy_consumption/2)) <= 0){ //Hunger gets reduced by half of energy_consumption
            pet.setHunger(0.0f);
        }else {
             pet.setHunger(pet.getHunger() - (energy_consumption/2));
        }

        if ((pet.getEnergy() - (energy_consumption)) <= 0){ //Energy gets reduced by energy_consumption
            pet.setEnergy(0.0f);
        }else {
             pet.setEnergy(pet.getEnergy() - (energy_consumption));
        }

        pet.updateEmotionalState();
        String sign = "";
        if (play_value > 0){
            sign = "+";
        }else if (play_value < 0){
            sign = "-";
        }else {
            sign = "";
        }

        String msg1 = pet.getPetName() + " Is Doing Activity: " + activity;
        String msg2 = "Mood " + sign + Math.abs(Math.round(play_value  * 10)) / 10.0f;
        String msg3 = "Hunger " + "-" + Math.round((energy_consumption/2)  * 10) / 10.0f;
        String msg4 = "Energy " + "-" + Math.round(energy_consumption  * 10) / 10.0f;

        for (char c: msg1.toCharArray()){
            System.out.print(c);
            Action.delay(100);
        }

        Action.delay(2000);

        Action.runIdleAnimation(pet);

        Action.playAudio(audioToPlay);

        for (char c: msg2.toCharArray()){
            System.out.print(c);
            Action.delay(100);
        }
        System.out.print("\n");
        for (char c: msg3.toCharArray()){
            System.out.print(c);
            Action.delay(100);
        }
        System.out.print("\n");
        for (char c: msg4.toCharArray()){
            System.out.print(c);
            Action.delay(100);
        }

        Action.delay(2000);

    }

    public static void Rest(Pet pet){ //Input is chosen Pet
        int restRng = new Random().nextInt(10);
        int rest_value;
        int mood_value;
        String audioToPlay;

        if (restRng < 7){ //70% chance, standard energy value
            rest_value = 30;
            mood_value = 0;
        }
        else if (restRng < 9){ // 20% chance, greater energy value
            rest_value = 50;
            mood_value = 5;
            pet.setMood(pet.getMood() + 5);
        }
        else { // 10% chance, either greatest energy value or lower energy value
            int chance = new Random().nextInt(10);
            if (chance < 5) { // 50% chance of greatest energy value
                rest_value = 80;
                mood_value = 10;
                pet.setMood(pet.getMood() + 10);
            }
            else { // 50% chance of lower energy value
                rest_value = 10;
                mood_value = -10;
                pet.setMood(pet.getMood() - 10);
            }
        }

        if (pet.getMood() >= 100) { // Takes into account if mood goes past 100
            pet.setMood(100);
        }

        if (pet.getMood() <= 0) { // Takes into account if mood goes bellow 0
            pet.setMood(0);
        }
        
        if ((rest_value + pet.getEnergy()) >= 100) {
            rest_value = (int) (Math.round(((100+rest_value)-(pet.getEnergy()+rest_value))* 10) / 10.0f);
            pet.setEnergy(100);
            pet.updateEmotionalState();
        }
        else {
            pet.setEnergy(pet.getEnergy() + rest_value);
            pet.updateEmotionalState();
        }

        String sign ;
        if (mood_value > 0){
            sign = "+";
            audioToPlay = "statUp";
        }else if (mood_value < 0){
            sign = "-";
            audioToPlay = "statDown";
        }else {
            sign = "+";
            audioToPlay = "statUp";
        }

        String msg1 = pet.getPetName() + " Is Resting...";
        String msg2 = "Energy +" + Math.round(rest_value  * 10) / 10.0f;
        String msg3 = "Mood " + sign + Math.abs(mood_value);

        for (char c: msg1.toCharArray()){
            System.out.print(c);
            Action.delay(100);
        }
        Action.delay(2000);
        Action.runIdleAnimation(pet);
        System.out.println("\n");
        for (char c: msg2.toCharArray()){
            System.out.print(c);
            Action.delay(100);
        }
        Action.playAudio(audioToPlay);
        System.out.println("\n");
        for (char c: msg3.toCharArray()){
            System.out.print(c);
            Action.delay(100);
        }
        
        Action.delay(2000);
    }

    public static void triggerDeath(ArrayList<Pet> pets, Pet petToDie) {
        Action.playAudio("error");
        String msg1 = "You've Neglected " + petToDie.getPetName() + "...";
        String msg2 = petToDie.getPetName() + " is in a better place now.";
        for (Pet mypets : pets){
            if (mypets == petToDie){
                
                for (char c: msg1.toCharArray()){
                    System.out.print(c);
                    Action.delay(100);
                }
                Action.delay(2000);

                Action.printSprite(petToDie.getSpecies(), -1);
                
                for (char c: msg2.toCharArray()){
                    System.out.print(c);
                    Action.delay(100);
                }
                pets.remove(petToDie);
                Action.delay(3000);
                break;
            }
        }
    }

    public static Clip playAudio(String filename, boolean loop) {
        try {
            File file = new File("audio/"+filename+".wav");
            AudioInputStream audioStream = null;
            try {
                audioStream = AudioSystem.getAudioInputStream(file);
            } catch (UnsupportedAudioFileException | IOException ex) {
            }
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            
            if(loop){
                if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    gainControl.setValue(-10.0f); // Adjust this number as needed
                }
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }else {
                clip.start();
            }
            return clip;
            // Action.delay(2000);
        } catch (LineUnavailableException | IOException ex) {
            return null;
        }        
    }

    public static void playAudio(String filename) {
        playAudio(filename, false);       
    }

    public static void printSprite(Species petPath, int frame){ //Work in Progress
        Path filePath = Paths.get("sprites", petPath.name(), frame + ".txt");
        try {
            String content = Files.readString(filePath, StandardCharsets.UTF_8);
            System.out.println(content);
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void runIdleAnimation(Pet pet){ //Work in Progress
        for (int i = 0; i < 2; i++){
                System.out.println("\u001b[2J");
            Action.printSprite(pet.getSpecies(), 1);
            Action.delay(190);
                System.out.println("\u001b[2J");
            Action.printSprite(pet.getSpecies(), 2);
            Action.delay(190);
                System.out.println("\u001b[2J");
            Action.printSprite(pet.getSpecies(), 3);
            Action.delay(190);
                System.out.println("\u001b[2J");
            Action.printSprite(pet.getSpecies(), 4);
            Action.delay(190);
            System.out.println("\u001b[2J");
            Action.printSprite(pet.getSpecies(), 1);
            Action.delay(190);
        }
    }

    public static void displayAllPetDetails(ArrayList<Pet> n){
        
        for (Pet myPets : n) { 
            System.out.println("\nName: " + myPets.getPetName());
            System.out.println("Species: " + myPets.getSpecies());
            System.out.println("Emotional State: " + myPets.getEmotionalState());
            System.out.println("Emotional Value: " + myPets.getEmotionalValue());
            System.out.println("Hunger: " + myPets.getHunger());
            System.out.println("Mood: " + myPets.getMood());
            System.out.println("Energy: " + myPets.getEnergy());

            System.out.println("Activity Multipliers: ");
            for (int i = 0; i < myPets.getActivityList().length; i++){
                int x = i+1;
                System.out.println("\n" + myPets.getActivityName(i) + " " + x + ": " + myPets.getActivityMultiplier(i));
            }
            System.out.println("Food Multipliers: ");
            for (int i = 0; i < myPets.getFoodInventory().length; i++){
                int x = i+1;
                System.out.println("\n" + myPets.getFoodName(i) + " " + x + ": " + myPets.getFoodMultiplier(i));
            }
        }
        
        System.out.println("""
                            \u001b[38;5;202m
            Finished Looping through available pets.\n
                            \u001b[0m""");

    }

    public static ArrayList<Pet> loadPetData(ArrayList<Pet> pets){     
        Path filePath = Paths.get("data", "petData.ser");
    
        if (!Files.exists(filePath)) {
            System.out.println("""  
                                                \u001b[38;5;196m
                                \nNo Pet Data to Load.\n
                                                \u001b[0m""");
            return pets;
        }

        try (FileInputStream fileIn = new FileInputStream(filePath.toFile());
            ObjectInputStream in = new ObjectInputStream(fileIn)) {
            
            @SuppressWarnings("unchecked")
            ArrayList<Pet> loadedPets = (ArrayList<Pet>) in.readObject();
            
            System.out.println("""  
                                                \u001b[38;5;46m
                                \nPet Data Loaded Succesfully!\n
                                                \u001b[0m""");
            return loadedPets;
            
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("""  
                                                \u001b[38;5;196m
                                \nError loading pet data: \u001b[0m""" + ex.getMessage());
            return pets;
        }
    }

    public static void savePetData(ArrayList<Pet> pets){   
        
        if (pets.isEmpty()) {
            System.out.println("""  
                                                \u001b[38;5;196m
                                \nNo Pet Data to Save.\n
                                                \u001b[0m""");
            return;
        }

        Path filePath = Paths.get("data", "petData.ser");
        
        // Create directory if it doesn't exist yet
        try {
            Files.createDirectories(filePath.getParent());
        } catch (IOException ex) {
            System.out.println("""  
                                                \u001b[38;5;196m
                                \nError creating data directory: \u001b[0m""" + ex.getMessage());
            return;
        }

        try (FileOutputStream fileOut = new FileOutputStream(filePath.toFile());
            ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            
            out.writeObject(pets);
            System.out.println("""  
                                                \u001b[38;5;46m
                                \nPet Data Saved.\n
                                                \u001b[0m""");
        } catch (IOException ex) {
            System.out.println("""  
                                                \u001b[38;5;196m
                                \nError saving pet data: \u001b[0m""" + ex.getMessage());
        }
    }

    public static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Delay interrupted");
        }
    }

}