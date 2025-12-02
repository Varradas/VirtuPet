package virtupetClasses;

public enum Activity {
    ;
    public interface ActivityType{
        float getActivityValue();
        String name();
    }

    public enum Dog implements ActivityType{
        TALK(20),
        PET(20),
        PLAY(20),
        FETCH(20),
        WALK(20),
        LEARN_TRICK(20),
        SOLVE_PUZZLE(20),
        AGILITY_COURSE(20);

        public final float activityValue;

        Dog (float activityValue){
            this.activityValue = activityValue;
        }

        @Override
        public float getActivityValue() {
            return activityValue;
        }
    }

    public enum Cat implements ActivityType{
        TALK(20),
        PET(20),
        PLAY(20),
        CLIMB_CAT_TREE(20),
        SCRATCH_POST(20),
        CHASE_LASER(20);

        public final float activityValue;

        Cat (float activityValue){
            this.activityValue = activityValue;
        }

        @Override
        public float getActivityValue() {
            return activityValue;
        }
    }

    public enum Bird implements ActivityType{
        TALK(20),
        PET(20),
        PLAY(20),
        FORAGE(20),
        SHALLOW_BATHE(20),
        BUILD_NEST(20),
        LOFT_FLIGHT(20);

        public final float activityValue;

        Bird (float activityValue){
            this.activityValue = activityValue;
        }

        @Override
        public float getActivityValue() {
            return activityValue;
        }
    }

    public enum Rabbit implements ActivityType{
        TALK(20),
        PET(20),
        PLAY(20),
        RUN_AROUND(20),
        DIGGING_BOX(20),
        CHEW_STICK(20),
        TUNNEL(20);

        public final float activityValue;

        Rabbit (float activityValue){
            this.activityValue = activityValue;
        }

        @Override
        public float getActivityValue() {
            return activityValue;
        }
    }
}