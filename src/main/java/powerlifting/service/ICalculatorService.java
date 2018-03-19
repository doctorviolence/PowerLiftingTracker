package powerlifting.service;

import powerlifting.model.Lift;

import java.util.List;

public interface ICalculatorService {

    double calculateWilksScore(List<Lift> lifts);

}



