package powerlifting.dal.mapper;

import powerlifting.model.Squat;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SquatMapper extends LiftMapper<Squat> {

    @Override
    public Squat mapRow(ResultSet rs, int rowNum) throws SQLException {
        Squat squat = new Squat();
        mapBase(rs, squat);
        squat.setSquat(rs.getBoolean("is_squat"));

        return squat;
    }

}
