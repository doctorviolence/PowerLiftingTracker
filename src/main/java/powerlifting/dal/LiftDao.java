package powerlifting.dal;

import powerlifting.dal.mapper.LiftMapper;
import powerlifting.model.Lift;
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
        String sql = "SELECT l.lift_id, l.reps, l.sets, l.weight_lifted, l.is_bench, l.is_deadlift, l.is_squat, l.date_lifted, l.user_id " +
                "FROM lifts l, users u " +
                "WHERE u.user_id = l.user_id " +
                "AND u.user_id = ? " +
                "ORDER BY date_lifted;";

        List lifts = getJdbcTemplate().query(sql, new LiftMapper(), new Object[]{userId});

        return lifts;
    }

    public List<Lift> getAllLiftsInDb() {
        String sql = "SELECT * FROM lifts";

        List lifts = getJdbcTemplate().query(sql, new LiftMapper());

        return lifts;
    }

    public void insertSquat(Lift lift, long userId) {
        String sql = "INSERT INTO lifts VALUES(0, ?, ?, ?, ?, ?, ?, ?, ?)";

        int reps = lift.getReps();
        int sets = lift.getSets();
        double weightLifted = lift.getWeightLifted();
        Date dateLifted = new Date(System.currentTimeMillis());

        getJdbcTemplate().update(sql, new Object[]{reps, sets, weightLifted, dateLifted, false, true, false, userId});
    }

    public void insertBench(Lift lift, long userId) {
        String sql = "INSERT INTO lifts VALUES(0, ?, ?, ?, ?, ?, ?, ?, ?)";

        int reps = lift.getReps();
        int sets = lift.getSets();
        double weightLifted = lift.getWeightLifted();
        Date dateLifted = new Date(System.currentTimeMillis());

        getJdbcTemplate().update(sql, new Object[]{reps, sets, weightLifted, dateLifted, true, false, false, userId});
    }

    public void insertDeadlift(Lift lift, long userId) {
        String sql = "INSERT INTO lifts VALUES(0, ?, ?, ?, ?, ?, ?, ?, ?)";

        int reps = lift.getReps();
        int sets = lift.getSets();
        double weightLifted = lift.getWeightLifted();
        Date dateLifted = new Date(System.currentTimeMillis());

        getJdbcTemplate().update(sql, new Object[]{reps, sets, weightLifted, dateLifted, false, false, true, userId});
    }

    public void removeLift(long liftId) {
        String sql = "DELETE FROM lifts WHERE lift_id = ?";

        getJdbcTemplate().update(sql, new Object[]{liftId});
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

}
