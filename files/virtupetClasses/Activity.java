package virtupetClasses;

public enum Activity {
    ;
    public interface ActivityType{
        float getActivityValue();
        String name();
    }

    public enum Dog implements ActivityType{
        WALK(20),
        FETCH(20),
        PLAY(20);

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
        ACT1(2),
        ACT2(2);

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
        ACT1(3);

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
        ACT1(4),
        ACT2(4),
        ACT3(4);

        public final float activityValue;

        Rabbit (float activityValue){
            this.activityValue = activityValue;
        }

        @Override
        public float getActivityValue() {
            return activityValue;
        }
    }

    public enum Hamster implements ActivityType{
        ACT1(5),
        ACT2(5),
        ACT3(5);

        public final float activityValue;

        Hamster (float activityValue){
            this.activityValue = activityValue;
        }

        @Override
        public float getActivityValue() {
            return activityValue;
        }
    }

    public enum GuineaPig implements ActivityType{
        ACT1(6),
        ACT2(6),
        ACT3(6);

        public final float activityValue;

        GuineaPig (float activityValue){
            this.activityValue = activityValue;
        }

        @Override
        public float getActivityValue() {
            return activityValue;
        }
    }

    public enum Gecko implements ActivityType{
        ACT1(7),
        ACT2(7),
        ACT3(7);

        public final float activityValue;

        Gecko (float activityValue){
            this.activityValue = activityValue;
        }

        @Override
        public float getActivityValue() {
            return activityValue;
        }
    }

}