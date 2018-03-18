package dal;

import dal.mapper.LiftMapper;
import model.Lift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@Repository
public class LiftDao implements ILiftDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource ds){
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    @SuppressWarnings("unchecked")
    public List<Lift> getLiftsByUser(long userId) {
        return this.jdbcTemplate.query("SELECT l.lift_id, l.reps, l.sets, l.weight_lifted, l.date_lifted " +
                "FROM lifts l, users u, user_stats s " +
                "WHERE u.user_id = s.user_id " +
                "AND u.user_id = ? " +
                "AND s.user_stats_id = l.user_stats_id " +
                "ORDER BY date_lifted;", new LiftMapper(), new Object[]{userId});
    }

    public void insertSquat(int reps, int sets, double weightLifted, Date dateLifted, long userStatId) {
        this.jdbcTemplate.update("INSERT INTO lifts VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                new Object[]{reps, sets, weightLifted, dateLifted, false, true, false, userStatId});
    }

    public void insertBench(int reps, int sets, double weightLifted, Date dateLifted, long userStatId) {
        this.jdbcTemplate.update("INSERT INTO lifts VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                new Object[]{reps, sets, weightLifted, dateLifted, true, false, false, userStatId});
    }

    public void insertDeadlift(int reps, int sets, double weightLifted, Date dateLifted, long userStatId) {
        this.jdbcTemplate.update("INSERT INTO lifts VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                new Object[]{reps, sets, weightLifted, dateLifted, false, false, true, userStatId});
    }

    public void removeLift(long liftId) {
        this.jdbcTemplate.update("DELETE FROM lifts WHERE lift_id = ?", new Object[]{liftId});
    }

}
