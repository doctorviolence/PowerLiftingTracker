package powerlifting.dal.mapper;

import powerlifting.model.Deadlift;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeadliftMapper extends LiftMapper<Deadlift> {

    @Override
    public Deadlift mapRow(ResultSet rs, int rowNum) throws SQLException {
        Deadlift deadlift = new Deadlift();
        mapBase(rs, deadlift);
        deadlift.setLiftId(rs.getInt("deadlift_id"));
        deadlift.setDeadlift(rs.getBoolean("is_deadlift"));

        return deadlift;
    }
}
