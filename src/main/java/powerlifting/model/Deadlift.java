package powerlifting.model;

public class Deadlift extends Lift {

    private boolean deadlift;

    public Deadlift() {

    }

    public Deadlift(int reps, int sets, double weightLifted, boolean deadlift){
        super(reps, sets, weightLifted);
        this.deadlift = deadlift;
    }

    public void setDeadlift(boolean deadlift) {
        this.deadlift = deadlift;
    }

    public boolean isDeadlift() {
        return deadlift;
    }

}
