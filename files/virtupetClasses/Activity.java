package virtupetClasses;

import java.io.Serializable;

public class Activity implements Serializable{
    protected String name;
    protected float value;

    public Activity(){
        name = "standard";
        value = 1;
    }

    public void setActivityName(String n){
        name = n;
    }
    public void setActivityValue(float n){
        value = n;
    }

    public String getActivityName(){
        return name;
    }
    public float getActivityValue(){
        return value;
    }

    public void displayActivityDetails(Activity[] n){
        
        for (Activity activityList : n) { //move to Pet.java
            if (activityList == null) {
                System.err.println("""
                                    \u001b[38;5;202m
                                    Finished Looping through available activities.\n\u001b[0m""");
                break;
            }
            System.err.println("\nName of food: " + activityList.getActivityName());
            System.err.println("Value: " + activityList.getActivityValue());
        }
        
    }
}
