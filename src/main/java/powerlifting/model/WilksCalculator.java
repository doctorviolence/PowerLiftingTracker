package powerlifting.model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;

public class WilksCalculator {

    private UserStatDetail userStatDetail = new UserStatDetail();
    public List<Lift> lifts = new ArrayList<>();
    private int i = 0;

    public void addLift(Lift lift) {
        lifts.add(lift);
    }

    private double getTotal() {
        double total = 0;
        for (Lift l : lifts) {
            total += l.getWeightLifted();
        }
        return total;
    }

    public double calculateWilksScore(double bodyWeight) {
        double coefficient;
        double score = 0;
        boolean male = false;
        boolean female = false;

        if (male && !female) {
            double a = -216.0475144;
            double b = 16.2606339;
            double c = -0.002388645;
            double d = -0.00113732;
            double e = 7.01863E-06;
            double f = -1.291E-08;

            coefficient = 500 / (a + (b * bodyWeight)
                    + (c * pow(bodyWeight, 2))
                    + (d * pow(bodyWeight, 3))
                    + (e * pow(bodyWeight, 4))
                    + (f * pow(bodyWeight, 5)));

            score += getTotal() * coefficient;

        } else if (female && !male) {
            double a = 594.31747775582;
            double b = -27.23842536447;
            double c = 0.82112226871;
            double d = -0.00930733913;
            double e = 4.731582E-05;
            double f = -9.054E-08;

            coefficient = 500 / (a + (b * bodyWeight)
                    + (c * pow(bodyWeight, 2))
                    + (d * pow(bodyWeight, 3))
                    + (e * pow(bodyWeight, 4))
                    + (f * pow(bodyWeight, 5)));

            score += getTotal() * coefficient;
        }

        return score;
    }

}
