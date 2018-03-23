package powerlifting.service;

import powerlifting.dal.exceptions.DbException;
import powerlifting.model.Bench;
import powerlifting.model.Deadlift;
import powerlifting.model.Squat;

import java.util.List;

public interface ILiftService {

    void insertSquatToDatabase(Squat squat, long userStatId);

    void insertBenchToDatabase(Bench bench, long userStatId);

    void insertDeadliftToDatabase(Deadlift deadlift, long userId);

    void deleteSquatFromDatabase(long id, long userId);

    void deleteBenchFromDatabase(long id, long userId);

    void deleteDeadliftFromDatabase(long id, long userId);

    List<Squat> getSquatByUserFromDao(long userId);

    List<Bench> getBenchByUserFromDao(long userId);

    List<Deadlift> getDeadliftByUserFromDao(long userId);

}
