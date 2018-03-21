package powerlifting.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public abstract class Lift {

    private long liftId;
    private int reps;
    private int sets;
    private double weightLifted;
    private int userId;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateLifted;

    public Lift() {

    }

    public Lift(int reps, int sets, double weightLifted) {
        this.reps = reps;
        this.sets = sets;
        this.weightLifted = weightLifted;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString(){
        return "ID:" + liftId + " " + getClass() + " User ID: " + userId;
    }

}