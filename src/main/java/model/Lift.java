package model;

import java.util.Date;

public class Lift {

    private long liftId;
    private int reps;
    private int sets;
    private double weightLifted;
    private Date dateLifted;
    private boolean squat;
    private boolean bench;
    private boolean deadlift;

    public Lift() {

    }

    public Lift(int reps, int sets, double weightLifted, Date dateLifted) {
        this.reps = reps;
        this.sets = sets;
        this.weightLifted = weightLifted;
        this.dateLifted = dateLifted;
    }

    public long getLiftId() {
        return liftId;
    }

    public void setLiftId(long liftId) {
        this.liftId = liftId;
    }

    public double getWeightLifted() {
        return weightLifted;
    }

    public void setWeightLifted(double weightLifted) {
        this.weightLifted = weightLifted;
    }

    public int getReps() {
        return reps;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getSets() {
        return sets;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public Date getDateLifted() {
        return dateLifted;
    }

    public void setDateLifted(Date dateLifted) {
        this.dateLifted = dateLifted;
    }

    public void setSquat(boolean squat) {
        this.squat = squat;
    }

    public void setBench(boolean bench) {
        this.bench = bench;
    }

    public void setDeadlift(boolean deadlift) {
        this.deadlift = deadlift;
    }

    public boolean isSquat() {
        return squat && !deadlift && !bench;
    }

    public boolean isBench() {
        return bench && !deadlift && !squat;
    }

    public boolean isDeadlift() {
        return deadlift && !bench && !squat;
    }

}