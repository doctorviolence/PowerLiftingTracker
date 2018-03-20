package powerlifting.model;

public class Squat extends Lift {

    private boolean squat;

    public Squat() {

    }

    public Squat(int reps, int sets, double weightLifted, boolean squat){
        super(reps, sets, weightLifted);
        this.squat = squat;
    }

    public void setSquat(boolean squat) {
        this.squat = squat;
    }

    public boolean isSquat() {
        return squat;
    }
}
