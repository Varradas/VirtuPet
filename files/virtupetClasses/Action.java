package virtupetClasses;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

public class Action{

    public static Pet Eat(Pet pet, Food food, int n){ //NEEDS AN INPUT, CHOSEN FOOD AND FOOD MULTIPLIER OF CURRENT PET
        //food_value will change depending on the food, food[n] pertains to the chosen food, pet
        float food_value = food.getFoodValue() * (pet.getFoodMultiplier(n)/100);
        if ((food_value + pet.getHunger()) >= 100) {
            pet.setHunger(100.0f);
            pet.updateEmotionalValue();
            return pet;
        }
        else {
            pet.setHunger(pet.getHunger() + food_value);
            pet.updateEmotionalValue();
            return pet;
        }
    }

    public static Pet Play(Pet pet, Activity activity, int n){
        float energy_consumption = ((1/(pet.getMood()+pet.getHunger()))*100)+(Math.abs(activity.getActivityValue())*(pet.getEnergy()/100)*0.5f);
        float play_value = activity.getActivityValue() * (pet.getActivityMultiplier(n)/100);
        if ((play_value + pet.getMood()) >= 100) {
            pet.setMood(100.0f);
            pet.setEnergy(pet.getEnergy() - energy_consumption);
            pet.updateEmotionalValue();
            return pet;
        }
        else {
            pet.setMood(pet.getMood() + play_value);
            pet.setEnergy(pet.getEnergy() - energy_consumption);
            pet.updateEmotionalValue();
            return pet;
        }
    }

    public static Pet Rest(Pet pet){ //All done
        int restRng = new Random().nextInt(10);
        int rest_value;

        if (restRng < 7){ //70% chance, standard energy value
            rest_value = 50;
        }
        else if (restRng < 9){ // 20% chance, greater energy value
            rest_value = 70;
            pet.setMood(pet.getMood() + 5);
        }
        else { // 10% chance, either greatest energy value or lower energy value
            int chance = new Random().nextInt(10);
            if (chance < 5) { // 50% chance of greatest energy value
                rest_value = 100;
                pet.setMood(pet.getMood() + 10);
            }
            else { // 50% chance of lower energy value
                rest_value = 20;
                pet.setMood(pet.getMood() - 10);
            }
        }

        if (pet.getMood() >= 100) { // Takes into account if mood goes past 100
            pet.setMood(100);
        }
        
        if ((rest_value + pet.getEnergy()) >= 100) {
            pet.setEnergy(100);
            pet.updateEmotionalValue();
            return pet;
        }
        else {
            pet.setEnergy(pet.getEnergy() + rest_value);
            pet.updateEmotionalValue();
            return pet;
        }
    }

    public static Pet[] loadPetData(Pet[] pets){        
        ObjectInputStream in = null;
        try {
            //Deserialize petData.ser to pet array
            String filepath = "D:\\OOP Final Project\\files\\data\\petData.ser";
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

    public static Food[] loadFoodData(Food[] food){        
        ObjectInputStream in = null;
        try {
            //Deserialize foodData.ser to pet array
            String filepath = "D:\\OOP Final Project\\files\\data\\foodData.ser";
            try (FileInputStream fileIn = new FileInputStream(filepath)) {
                in = new ObjectInputStream(fileIn);
                food = (Food[]) in.readObject();
                in.close();
            }
            System.out.println("""  
                                                \u001b[38;5;46m
                                \nFood Data Loaded Succesfully!\n
                                                \u001b[0m""");
            return food;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("""  
                                                \u001b[38;5;196m
                                \nNo Food Data to Load.\n
                                                \u001b[0m""");
            return food;
        } finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException ex) {
                }
            }
        }
    }

    public static Activity[] loadActivityData(Activity[] activity){        
        ObjectInputStream in = null;
        try {
            //Deserialize foodData.ser to pet array
            String filepath = "D:\\OOP Final Project\\files\\data\\activityData.ser";
            try (FileInputStream fileIn = new FileInputStream(filepath)) {
                in = new ObjectInputStream(fileIn);
                activity = (Activity[]) in.readObject();
                in.close();
            }
            System.out.println("""  
                                                \u001b[38;5;46m
                                \nActivity Data Loaded Succesfully!\n
                                                \u001b[0m""");
            return activity;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("""  
                                                \u001b[38;5;196m
                                \nNo Activity Data to Load.\n
                                                \u001b[0m""");
            return activity;
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
            String filepath = "D:\\OOP Final Project\\files\\data\\petData.ser";
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

    public static void saveFoodData(Food[] food){         
        ObjectOutputStream out = null;

        if (food[0] == null){
            System.out.println("""  
                                                \u001b[38;5;196m
                                \nNo Food Data to Save.\n
                                                \u001b[0m""");
            return;
        }

        try {
            //Serialize pet array to foodData.ser
            String filepath = "D:\\OOP Final Project\\files\\data\\foodData.ser";
            try (FileOutputStream fileOut = new FileOutputStream(filepath)) {
                out = new ObjectOutputStream(fileOut);
                out.writeObject(food);
                out.close();
            }
            System.out.println("""  
                                                \u001b[38;5;46m
                                \nFood Data Saved.\n
                                                \u001b[0m""");
        } catch (IOException ex) {
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
            }
        }
    }

    public static void saveActivityData(Activity[] activity){         
        ObjectOutputStream out = null;

        if (activity[0] == null){
            System.out.println("""  
                                                \u001b[38;5;196m
                                \nNo Activity Data to Save.\n
                                                \u001b[0m""");
            return;
        }

        try {
            //Serialize pet array to foodData.ser
            String filepath = "D:\\OOP Final Project\\files\\data\\activityData.ser";
            try (FileOutputStream fileOut = new FileOutputStream(filepath)) {
                out = new ObjectOutputStream(fileOut);
                out.writeObject(activity);
                out.close();
            }
            System.out.println("""  
                                                \u001b[38;5;46m
                                \nActivity Data Saved.\n
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