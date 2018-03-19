package dal;

import model.Lift;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

public interface ILiftDao {

    void setDataSource(DataSource ds);

    List<Lift> getLiftsByUser(long userId);

    void insertSquat(Lift lift, long userStatId);

    void insertBench(Lift lift, long userStatId);

    void insertDeadlift(Lift lift, long userStatId);

    void removeLift(long liftId);

}
