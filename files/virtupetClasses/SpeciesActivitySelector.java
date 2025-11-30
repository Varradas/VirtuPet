package virtupetClasses;

public class SpeciesActivitySelector {
    
    public enum speciesSelector{

        DOG, 
        CAT, 
        BIRD, 
        RABBIT, 
        HAMSTER, 
        GUINEA_PIG,
        GECKO;

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
            case HAMSTER -> {
                return Activity.Hamster.values();
            }
            case GUINEA_PIG -> {
                return Activity.GuineaPig.values();
            }
            case GECKO -> {
                return Activity.Gecko.values();
            }
            default -> throw new IllegalArgumentException("Unknown type: " + species);
        }
    }

}
