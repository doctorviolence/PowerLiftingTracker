package powerlifting.dal;

import powerlifting.model.Bench;
import powerlifting.model.Deadlift;
import powerlifting.model.Lift;
import powerlifting.model.Squat;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

public interface ILiftDao {

    void setDataSource(DataSource ds);

    List<Lift> getLiftsByUser(long userId);

    List<Squat> getTopFiveSquatPRs(int reps);

    List<Bench> getTopFiveBenchPRs(int reps);

    List<Deadlift> getTopFiveDeadliftPRs(int reps);

    List<Squat> getSquatByUser(long userId);

    List<Bench> getBenchByUser(long userId);

    List<Deadlift> getDeadliftByUser(long userId);

    void insertSquat(int reps, int sets, double weightLifted, Date dateLifted, long userId);

    void insertBench(int reps, int sets, double weightLifted, Date dateLifted, long userId);

    void insertDeadlift(int reps, int sets, double weightLifted, Date dateLifted, long userId);

    void removeSquat(long liftId, long userId);

    void removeBench(long liftId, long userId);

    void removeDeadlift(long liftId, long userId);

}
