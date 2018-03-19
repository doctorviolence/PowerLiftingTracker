package dal;

import dal.mapper.LiftMapper;
import model.Lift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@Repository
public class LiftDao implements ILiftDao {

    private JdbcTemplate jdbcTemplate;
    private DataSource ds;

    @Autowired
    public void setDataSource(DataSource ds) {
        this.ds = ds;
    }

    @PostConstruct
    public void postConstruct() {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    @SuppressWarnings("unchecked")
    public List<Lift> getLiftsByUser(long userId) {
        return this.jdbcTemplate.query("SELECT l.lift_id, l.reps, l.sets, l.weight_lifted, l.is_bench, l.is_deadlift, l.is_squat, l.date_lifted, l.user_stat_id " +
                "FROM lifts l, users u, user_stats s " +
                "WHERE u.user_id = s.user_id " +
                "AND u.user_id = ? " +
                "AND s.user_stat_id = l.user_stat_id " +
                "ORDER BY date_lifted;", new LiftMapper(), new Object[]{userId});
    }

    public void insertSquat(Lift lift, long userStatId) {
        int reps = lift.getReps();
        int sets = lift.getSets();
        double weightLifted = lift.getWeightLifted();
        Date dateLifted = new Date(System.currentTimeMillis());

        this.jdbcTemplate.update("INSERT INTO lifts VALUES(0, ?, ?, ?, ?, ?, ?, ?, ?)",
                new Object[]{reps, sets, weightLifted, dateLifted, false, true, false, userStatId});
    }

    public void insertBench(Lift lift, long userStatId) {
        int reps = lift.getReps();
        int sets = lift.getSets();
        double weightLifted = lift.getWeightLifted();
        Date dateLifted = new Date(System.currentTimeMillis());

        this.jdbcTemplate.update("INSERT INTO lifts VALUES(0, ?, ?, ?, ?, ?, ?, ?, ?)",
                new Object[]{reps, sets, weightLifted, dateLifted, true, false, false, userStatId});
    }

    public void insertDeadlift(Lift lift, long userStatId) {
        int reps = lift.getReps();
        int sets = lift.getSets();
        double weightLifted = lift.getWeightLifted();
        Date dateLifted = new Date(System.currentTimeMillis());

        this.jdbcTemplate.update("INSERT INTO lifts VALUES(0, ?, ?, ?, ?, ?, ?, ?, ?)",
                new Object[]{reps, sets, weightLifted, dateLifted, false, false, true, userStatId});
    }

    public void removeLift(long liftId) {
        this.jdbcTemplate.update("DELETE FROM lifts WHERE lift_id = ?", new Object[]{liftId});
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

}
