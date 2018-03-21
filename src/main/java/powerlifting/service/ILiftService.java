package powerlifting.service;

import powerlifting.model.Bench;
import powerlifting.model.Deadlift;
import powerlifting.model.Lift;
import powerlifting.model.Squat;

import java.util.List;

public interface ILiftService {

    void insertLiftIntoDatabase(Lift lift, long userStatId);

    void deleteSquatFromDatabase(long id, long userId);

    void deleteBenchFromDatabase(long id, long userId);

    void deleteDeadliftFromDatabase(long id, long userId);

    List<Squat> getSquatByUserFromDao(long userId);

    List<Bench> getBenchByUserFromDao(long userId);

    List<Deadlift> getDeadliftByUserFromDao(long userId);

}
