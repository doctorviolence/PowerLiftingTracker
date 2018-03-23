package powerlifting.model;

public class Deadlift extends Lift {

    private boolean deadlift;

    public Deadlift() {

    }

    public Deadlift(int reps, int sets, double weightLifted){
        super(reps, sets, weightLifted);
        this.deadlift = true;
    }

    public void setDeadlift(boolean deadlift) {
        this.deadlift = deadlift;
    }

    public boolean isDeadlift() {
        return deadlift;
    }

}
