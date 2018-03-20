package powerlifting.dal.mapper;

import powerlifting.model.Bench;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BenchMapper extends LiftMapper<Bench> {

    @Override
    public Bench mapRow(ResultSet rs, int rowNum) throws SQLException {
        Bench bench = new Bench();
        mapBase(rs, bench);
        bench.setBench(rs.getBoolean("is_bench"));

        return bench;
    }
}
