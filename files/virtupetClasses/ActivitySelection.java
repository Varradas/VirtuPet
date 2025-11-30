package virtupetClasses;

import java.util.*;
import virtupetClasses.Activity.ActivityType;

public class ActivitySelection {
    private static final Map<String, List<? extends ActivityType>> ACTIVITY_MAP = new HashMap<>();
    
    static {
        ACTIVITY_MAP.put("DOG", Arrays.asList(Activity.Dog.values()));
        ACTIVITY_MAP.put("CAT", Arrays.asList(Activity.Cat.values()));
        ACTIVITY_MAP.put("BIRD", Arrays.asList(Activity.Bird.values()));
        ACTIVITY_MAP.put("RABBIT", Arrays.asList(Activity.Rabbit.values()));
        ACTIVITY_MAP.put("HAMSTER", Arrays.asList(Activity.Hamster.values()));
        ACTIVITY_MAP.put("GUINEA_PIG", Arrays.asList(Activity.GuineaPig.values()));
        ACTIVITY_MAP.put("GECKO", Arrays.asList(Activity.Gecko.values()));
    }
    
    @SuppressWarnings("unlikely-arg-type")
    public static ActivityType getActivityByNumber(Species species, int activityNumber) {
        if (!ACTIVITY_MAP.containsKey(species)) {
            throw new IllegalArgumentException("Unknown species: " + species);
        }
        
        List<? extends ActivityType> activities = ACTIVITY_MAP.get(species);
        if (activityNumber < 1 || activityNumber > activities.size()) {
            throw new IllegalArgumentException("Invalid activity number for " + species + ": " + activityNumber);
        }
        
        return activities.get(activityNumber - 1);
    }
    
    @SuppressWarnings("unlikely-arg-type")
    public static void displayActivities(Species species) {
        if (!ACTIVITY_MAP.containsKey(species)) {
            System.out.println("Unknown species: " + species);
            return;
        }
        
        List<? extends ActivityType> activities = ACTIVITY_MAP.get(species);
        System.out.println("Available activities for " + species + ":");
        for (int i = 0; i < activities.size(); i++) {
            System.out.println((i + 1) + ". " + activities.get(i));
        }
    }
}
