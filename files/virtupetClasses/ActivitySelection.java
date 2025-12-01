package virtupetClasses;

import java.util.*;
import virtupetClasses.Activity.ActivityType;

public class ActivitySelection {
    private static final Map<String, List<? extends ActivityType>> ACTIVITY_MAP = new HashMap<>();
    
    static {
        ACTIVITY_MAP.put(Species.DOG.name(), Arrays.asList(Activity.Dog.values()));
        ACTIVITY_MAP.put(Species.CAT.name(), Arrays.asList(Activity.Cat.values()));
        ACTIVITY_MAP.put(Species.BIRD.name(), Arrays.asList(Activity.Bird.values()));
        ACTIVITY_MAP.put(Species.RABBIT.name(), Arrays.asList(Activity.Rabbit.values()));
        ACTIVITY_MAP.put(Species.HAMSTER.name(), Arrays.asList(Activity.Hamster.values()));
        ACTIVITY_MAP.put(Species.GUINEA_PIG.name(), Arrays.asList(Activity.GuineaPig.values()));
        ACTIVITY_MAP.put(Species.GECKO.name(), Arrays.asList(Activity.Gecko.values()));
    }
    
    public static ActivityType getActivityByNumber(String species, int activityNumber) {
        if (!ACTIVITY_MAP.containsKey(species)) {
            throw new IllegalArgumentException("Unknown species: " + species);
        }
        
        List<? extends ActivityType> activities = ACTIVITY_MAP.get(species);
        if (activityNumber < 1 || activityNumber > activities.size()) {
            throw new IllegalArgumentException("Invalid activity number for " + species + ": " + activityNumber);
        }
        
        return activities.get(activityNumber - 1);
    }
    
    public static void displayActivities(String species) {
        if (!ACTIVITY_MAP.containsKey(species)) {
            System.out.println("Unknown species: " + species);
            return;
        }
        
        List<? extends ActivityType> activities = ACTIVITY_MAP.get(species);
        // System.out.println("Available activities for " + species + ":");
        for (int i = 0; i < activities.size(); i++) {
            System.out.println((i + 1) + ". " + activities.get(i));
        }
    }
}
