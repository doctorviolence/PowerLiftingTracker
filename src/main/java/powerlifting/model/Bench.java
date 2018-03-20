package powerlifting.model;

public class Bench extends Lift {

    private boolean bench;

    public Bench() {

    }

    public Bench(int reps, int sets, double weightLifted, boolean bench) {
        super(reps, sets, weightLifted);
        this.bench = bench;
    }

    public void setBench(boolean bench) {
        this.bench = bench;
    }

    public boolean isBench() {
        return bench;
    }

}
