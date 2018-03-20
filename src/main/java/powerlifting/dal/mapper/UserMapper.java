package powerlifting.dal.mapper;

import org.springframework.jdbc.core.RowMapper;
import powerlifting.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int row) throws SQLException {
        User user = new User();

        user.setUserId(rs.getInt("user_id"));
        user.setUserName(rs.getString("username"));
        user.setPassword(rs.getString("pw"));
        user.setFemale(rs.getBoolean("is_female"));
        user.setMale(rs.getBoolean("is_male"));

        return user;
    }
}
