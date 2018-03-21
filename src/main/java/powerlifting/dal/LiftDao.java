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
        String sql = "SELECT 'Bench' AS type, b.reps, b.sets, b.weight_lifted, b.date_lifted " +
                "FROM bench_lifts b, users u " +
                "WHERE u.user_id = b.user_id " +
                "AND u.user_id = ? " +
                "UNION " +
                "SELECT 'Squat' AS type, s.reps, s.sets, s.weight_lifted, s.date_lifted " +
                "FROM squat_lifts s, users u " +
                "WHERE u.user_id = s.user_id " +
                "AND u.user_id = ? " +
                "UNION " +
                "SELECT 'Deadlift' AS type, d.reps, d.sets, d.weight_lifted, d.date_lifted " +
                "FROM deadlift_lifts d, users u " +
                "WHERE u.user_id = d.user_id " +
                "AND u.user_id = ?;";

        List lifts = getJdbcTemplate().query(sql, new BenchMapper(), new Object[]{userId});

        return lifts;
    }

    public List<Squat> getSquatByUser(long userId) {
        String sql = "SELECT * " +
                "FROM squat_lifts s, users u " +
                "WHERE u.user_id = s.user_id " +
                "AND u.user_id = ? " +
                "ORDER BY date_lifted;";

        List<Squat> lifts = getJdbcTemplate().query(sql, new SquatMapper(), new Object[]{userId});

        return lifts;
    }

    public List<Bench> getBenchByUser(long userId) {
        String sql = "SELECT * " +
                "FROM bench_lifts b, users u " +
                "WHERE u.user_id = b.user_id " +
                "AND u.user_id = ? " +
                "ORDER BY date_lifted;";

        List<Bench> lifts = getJdbcTemplate().query(sql, new BenchMapper(), new Object[]{userId});

        return lifts;
    }

    public List<Deadlift> getDeadliftByUser(long userId) {
        String sql = "SELECT * " +
                "FROM deadlift_lifts d, users u " +
                "WHERE u.user_id = d.user_id " +
                "AND u.user_id = ? " +
                "ORDER BY date_lifted;";

        List<Deadlift> lifts = getJdbcTemplate().query(sql, new DeadliftMapper(), new Object[]{userId});

        return lifts;
    }

    public void insertSquat(int reps, int sets, double weightLifted, Date dateLifted, long userId) {
        String sql = "INSERT INTO squat_lifts(reps, sets, weight_lifted, date_lifted, is_squat, user_id) VALUES(?, ?, ?, ?, ?, ?)";

        getJdbcTemplate().update(sql, new Object[]{reps, sets, weightLifted, dateLifted, true, userId});
    }

    public void insertBench(int reps, int sets, double weightLifted, Date dateLifted, long userId) {
        String sql = "INSERT INTO bench_lifts(reps, sets, weight_lifted, date_lifted, is_bench, user_id) VALUES(?, ?, ?, ?, ?, ?)";

        getJdbcTemplate().update(sql, new Object[]{reps, sets, weightLifted, dateLifted, true, userId});
    }

    public void insertDeadlift(int reps, int sets, double weightLifted, Date dateLifted, long userId) {
        String sql = "INSERT INTO deadlift_lifts(reps, sets, weight_lifted, date_lifted, is_deadlift, user_id) VALUES(?, ?, ?, ?, ?, ?)";

        getJdbcTemplate().update(sql, new Object[]{reps, sets, weightLifted, dateLifted, true, userId});
    }

    public void removeSquat(long liftId, long userId){
        String sql = "DELETE FROM squat_lifts WHERE bench_id = ? AND user_id = ?";

        getJdbcTemplate().update(sql, new Object[]{liftId, userId});
    }

    public void removeBench(long liftId, long userId){
        String sql = "DELETE FROM bench_lifts WHERE bench_id = ? AND user_id = ?";

        getJdbcTemplate().update(sql, new Object[]{liftId, userId});
    }

    public void removeDeadlift(long liftId, long userId){
        String sql = "DELETE FROM deadlift_lifts WHERE bench_id = ? AND user_id = ?";

        getJdbcTemplate().update(sql, new Object[]{liftId, userId});
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

}
