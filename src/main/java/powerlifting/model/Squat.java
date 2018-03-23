package powerlifting.model;

public class Squat extends Lift {

    private boolean squat;

    public Squat() {

    }

    public Squat(int reps, int sets, double weightLifted){
        super(reps, sets, weightLifted);
        this.squat = true;
    }

    public void setSquat(boolean squat) {
        this.squat = squat;
    }

    public boolean isSquat() {
        return squat;
    }
}
