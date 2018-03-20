package powerlifting.dal.mapper;

import powerlifting.model.Bench;
import powerlifting.model.Lift;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class LiftMapper<T extends Lift> implements RowMapper {

    @Override
    public abstract T mapRow(ResultSet rs, int row) throws SQLException;

    protected void mapBase(ResultSet rs, T lift) throws SQLException {
        lift.setLiftId(rs.getInt("lift_id"));
        lift.setReps(rs.getInt("reps"));
        lift.setSets(rs.getInt("sets"));
        lift.setWeightLifted(rs.getInt("weight_lifted"));
        lift.setDateLifted(rs.getDate("date_lifted"));
        lift.setUserId(rs.getInt("user_id"));
    }

}
