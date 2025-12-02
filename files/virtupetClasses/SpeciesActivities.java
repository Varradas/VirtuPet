package virtupetClasses;

public class SpeciesActivities {
    
    public enum speciesSelector{

        DOG, 
        CAT, 
        BIRD, 
        RABBIT,

    }

    public Enum<?>[] getSpeciesActivities(Species species){
        switch(species){
            case DOG -> {
                return Activity.Dog.values();
            }
            case CAT -> {
                return Activity.Cat.values();
            }
            case BIRD -> {
                return Activity.Bird.values();
            }
            case RABBIT -> {
                return Activity.Rabbit.values();
            }
            default -> throw new IllegalArgumentException("Unknown type: " + species);
        }
    }

}
