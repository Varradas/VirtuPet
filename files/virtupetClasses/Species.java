package virtupetClasses;

public enum Species {
   
    DOG(Activity.Dog.class), 
    CAT(Activity.Cat.class), 
    BIRD(Activity.Bird.class), 
    RABBIT(Activity.Rabbit.class), 
    HAMSTER(Activity.Hamster.class), 
    GUINEA_PIG(Activity.GuineaPig.class),
    GECKO(Activity.Gecko.class);

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

//    public enum Dog{
//         WALK(1),
//         TALK(1),
//         RUN(1);

//         public final float activityValue;

//         Dog (float activityValue){
//             this.activityValue = activityValue;
//         }
//    }

//    public enum Cat{
//         ACT1(2),
//         ACT2(2),
//         ACT3(3);

//         public final float activityValue;

//         Cat (float activityValue){
//             this.activityValue = activityValue;
//         }
//    }

//    public enum Bird{
//         ACT1(4),
//         ACT2(4);

//         public final float activityValue;

//         Bird (float activityValue){
//             this.activityValue = activityValue;
//         }
//    }

//    public enum Rabbit{
//         ACT1(5),
//         ACT2(5),
//         ACT3(5);

//         public final float activityValue;

//         Rabbit (float activityValue){
//             this.activityValue = activityValue;
//         }
//    }

//    public enum Hamster{
//         ACT1(6),
//         ACT2(6),
//         ACT3(6);

//         public final float activityValue;

//         Hamster (float activityValue){
//             this.activityValue = activityValue;
//         }
//    }

//    public enum GuineaPig{
//         ACT1(7),
//         ACT2(7),
//         ACT3(7);

//         public final float activityValue;

//         GuineaPig (float activityValue){
//             this.activityValue = activityValue;
//         }
//    }

//    public enum Gecko{
//         ACT1(8),
//         ACT2(8),
//         ACT3(8);

//         public final float activityValue;

//         Gecko (float activityValue){
//             this.activityValue = activityValue;
//         }
//    }

}