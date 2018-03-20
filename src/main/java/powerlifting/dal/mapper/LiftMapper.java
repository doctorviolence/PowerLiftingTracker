package powerlifting.dal.mapper;

import powerlifting.model.Lift;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LiftMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int row) throws SQLException {
        Lift lift = new Lift();

        lift.setLiftId(rs.getInt("lift_id"));
        lift.setReps(rs.getInt("reps"));
        lift.setSets(rs.getInt("sets"));
        lift.setWeightLifted(rs.getInt("weight_lifted"));
        lift.setDateLifted(rs.getDate("date_lifted"));
        lift.setBench(rs.getBoolean("is_bench"));
        lift.setSquat(rs.getBoolean("is_squat"));
        lift.setDeadlift(rs.getBoolean("is_deadlift"));
        lift.setUserId(rs.getInt("user_id"));

        return lift;
    }

}
