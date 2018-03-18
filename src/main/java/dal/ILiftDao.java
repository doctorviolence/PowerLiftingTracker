package dal;

import model.Lift;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

public interface ILiftDao {

    void setDataSource(DataSource ds);

    List<Lift> getLiftsByUser(long userId);

    void insertSquat(int reps, int sets, double weightLifted, Date dateLifted, long userStatId);

    void insertBench(int reps, int sets, double weightLifted, Date dateLifted, long userStatId);

    void insertDeadlift(int reps, int sets, double weightLifted, Date dateLifted, long userStatId);

    void removeLift(long liftId);

}
