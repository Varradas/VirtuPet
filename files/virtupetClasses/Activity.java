package virtupetClasses;

public enum Activity{
    WALK(50f),
    TALK(26f),
    PLAY(33f);

    public final float activityValue;

    Activity (float activityValue){
        this.activityValue = activityValue;
    }

}