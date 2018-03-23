package powerlifting.model;

public class Bench extends Lift {

    private boolean bench;

    public Bench() {

    }

    public Bench(int reps, int sets, double weightLifted) {
        super(reps, sets, weightLifted);
        this.bench = true;
    }

    public void setBench(boolean bench) {
        this.bench = bench;
    }

    public boolean isBench() {
        return bench;
    }

}
