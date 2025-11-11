package files;

public class VirtuPet {

    public static void main(String[] args) { //just testing the output of classes, not indicative of the final

        Pet[] pets = new Pet[100]; //array to store pet data

            pets[0] = new Pet();
            pets[0].name = "Bubbles"; //Name needs to be decided by user through input
            pets[0].species = "Doggo"; //Species needs to be from a list

            pets[1] = new Pet();
            pets[1].name = "Drago";
            pets[1].species = "Cat";

            pets[2] = new Pet();
            pets[2].name = "Lemon";
            pets[2].species = "Parrot";

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
}
