package virtupetClasses;

public enum Species {
   
    DOG(Activity.Dog.class), 
    CAT(Activity.Cat.class), 
    BIRD(Activity.Bird.class), 
    RABBIT(Activity.Rabbit.class); 

    private final Class<? extends Activity.ActivityType> activityClass;
    
    Species(Class<? extends Activity.ActivityType> activityClass) {
        this.activityClass = activityClass;
    }

    public Class<? extends Activity.ActivityType> getActivityClass() {
        return activityClass;
    }

    public Activity.ActivityType[] getActivities() {
        return (Activity.ActivityType[]) activityClass.getEnumConstants();
    }
}