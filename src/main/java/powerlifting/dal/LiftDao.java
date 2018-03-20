package powerlifting.dal;

import powerlifting.dal.mapper.BenchMapper;
import powerlifting.dal.mapper.DeadliftMapper;
import powerlifting.dal.mapper.SquatMapper;
import powerlifting.model.Bench;
import powerlifting.model.Deadlift;
import powerlifting.model.Lift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import powerlifting.model.Squat;

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
        String sql = "SELECT * " +
                "FROM bench_lifts b, squat_lifts s, deadlift_lifts d, users u " +
                "WHERE u.user_id = b.user_id " +
                "AND u.user_id = ? " +
                "ORDER BY date_lifted;";

        List lifts = getJdbcTemplate().query(sql, new SquatMapper(), new BenchMapper(), new DeadliftMapper(), new Object[]{userId});

        return lifts;
    }

    public List<Lift> getAllLiftsInDb() {
        String sql = "SELECT * FROM bench_lifts b, squat_lifts s, deadlift_lifts d";

        List lifts = getJdbcTemplate().query(sql, new SquatMapper(), new BenchMapper(), new DeadliftMapper());

        return lifts;
    }

    public void insertSquat(int reps, int sets, double weightLifted, Date dateLifted, long userId) {
        String sql = "INSERT INTO squat_lifts VALUES(0, ?, ?, ?, ?, ?, ?)";

        getJdbcTemplate().update(sql, new Object[]{reps, sets, weightLifted, dateLifted, true, userId});
    }

    public void insertBench(int reps, int sets, double weightLifted, Date dateLifted, long userId) {
        String sql = "INSERT INTO bench_lifts VALUES(0, ?, ?, ?, ?, ?, ?)";

        getJdbcTemplate().update(sql, new Object[]{reps, sets, weightLifted, dateLifted, true, userId});
    }

    public void insertDeadlift(int reps, int sets, double weightLifted, Date dateLifted, long userId) {
        String sql = "INSERT INTO deadlift_lifts VALUES(0, ?, ?, ?, ?, ?, ?)";

        getJdbcTemplate().update(sql, new Object[]{reps, sets, weightLifted, dateLifted, true, userId});
    }

    public void removeLift(long liftId) {
        String sql = "DELETE FROM bench_lifts WHERE bench_id = ?";

        getJdbcTemplate().update(sql, new Object[]{liftId});
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

}
