import java.io.*;
import virtupetClasses.*;

public class VirtuPet {

    public static void main(String[] args) throws IOException, ClassNotFoundException { //just testing the output of classes, not indicative of the final

        Pet[] pets = new Pet[100]; //array to store pet data

        //     pets[0] = new Pet();
        //     pets[0].setName("Bubbles"); //Name needs to be decided by user through input
        //     pets[0].setSpecies("Doggo"); //Species needs to be from a list

        //     pets[1] = new Pet();
        //     pets[1].setName("Drago");
        //     pets[1].setSpecies("Cat");

        //     pets[2] = new Pet();
        //     pets[2].setName("Lemon");
        //     pets[2].setSpecies("Parrot");

        // savePetData(pets);

        pets = loadPetData(pets);

        pets[0].displayAllPetDetails(pets);

        Food[] food = new Food[100]; //array to store food data, probably will be in another file.
    
            food[0] = new Food();
            food[0].setFoodName("Chicken");
            food[0].setFoodValue(10);

            food[1] = new Food();
            food[1].setFoodName("Peas");
            food[1].setFoodValue(3);

            food[2] = new Food();
            food[2].setFoodName("Fish");
            food[2].setFoodValue(12);

        food[0].displayFoodDetails(food);

    }

    static Pet[] loadPetData(Pet[] pets){        
        ObjectInputStream in = null;
        try {
            //Deserialize petData.ser to pet array
            String filepath = "D:\\OOP Final Project\\files\\data\\petData.ser";
            FileInputStream fileIn = new FileInputStream(filepath);
            in = new ObjectInputStream(fileIn);
            pets = (Pet[]) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Pet Data Loaded Succesfully!");
            return pets;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("No Pet Data to Load.");
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
            }
        }
        return pets;
    }

    static void savePetData(Pet[] pets){         
        ObjectOutputStream out = null;
        try {
            //Serialize pet array to petData.ser
            String filepath = "D:\\OOP Final Project\\files\\data\\petData.ser";
            FileOutputStream fileOut = new FileOutputStream(filepath);
            out = new ObjectOutputStream(fileOut);
            out.writeObject(pets);
            out.close();
            fileOut.close();
            System.out.println("Pet Data Saved.");
        } catch (IOException ex) {
            System.out.println("No Pet Data to Save.");
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
            }
        }
    }
}
