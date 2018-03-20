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

    List<Lift> getAllLiftsInDb();

    void insertSquat(int reps, int sets, double weightLifted, Date dateLifted, long userId);

    void insertBench(int reps, int sets, double weightLifted, Date dateLifted, long userId);

    void insertDeadlift(int reps, int sets, double weightLifted, Date dateLifted, long userId);

    void removeLift(long liftId);

}
