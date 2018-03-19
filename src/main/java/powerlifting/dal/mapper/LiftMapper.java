package powerlifting.dal.mapper;

import powerlifting.model.Lift;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LiftMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int row) throws SQLException {
        Lift l = new Lift();

        l.setLiftId(rs.getInt("lift_id"));
        l.setReps(rs.getInt("reps"));
        l.setSets(rs.getInt("sets"));
        l.setWeightLifted(rs.getInt("weight_lifted"));
        l.setDateLifted(rs.getDate("date_lifted"));
        l.setBench(rs.getBoolean("is_bench"));
        l.setSquat(rs.getBoolean("is_squat"));
        l.setDeadlift(rs.getBoolean("is_deadlift"));
        l.setUserStatId(rs.getInt("user_stat_id"));

        return l;
    }

}
