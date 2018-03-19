package powerlifting.service;

import powerlifting.model.Lift;
import powerlifting.model.WilksCalculator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("calculatorService")
public class CalculatorService implements ICalculatorService {

    private WilksCalculator wilksCalculator;

    public CalculatorService(WilksCalculator wilksCalculator){
        this.wilksCalculator = wilksCalculator;
    }

    public double calculateWilksScore(List<Lift> lifts){
        double score = 0;
        for (Lift l : lifts){
            wilksCalculator.addLift(l);
            //score += wilksCalculator.calculateWilksScore();
        }
        return score;
    }

}
