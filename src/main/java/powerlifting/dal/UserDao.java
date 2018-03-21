package powerlifting.dal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import powerlifting.dal.mapper.UserMapper;
import powerlifting.model.User;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

public class UserDao implements IUserDao {

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

    public User findUserById(long id) {
        String sql = "SELECT * FROM users u WHERE u.user_id = ?";

        User user = (User) getJdbcTemplate().queryForObject(sql, new Object[]{id}, new UserMapper());

        return user;
    }

    public void addNewFemaleUserToDb(User user) {
        String sql = "INSERT INTO users VALUES(?, ?, ?, ?, ?)";

        long id = user.getUserId();
        String username = user.getUserName();
        String pw = user.getPassword();

        getJdbcTemplate().update(sql, new Object[]{id, username, false, true, pw});
    }

    public void addNewMaleUserToDb(User user) {
        String sql = "INSERT INTO users VALUES(?, ?, ?, ?, ?)";

        long id = user.getUserId();
        String username = user.getUserName();
        String pw = user.getPassword();

        getJdbcTemplate().update(sql, new Object[]{id, username, true, false, pw});
    }

    public void changeUserPassword(long id, String pw) {
        String sql = "UPDATE users u SET u.pw = ? WHERE u.user_id = ?";

        User user = findUserById(id);

        if (user != null) {
            int userId = user.getUserId();

            getJdbcTemplate().update(sql, new Object[]{pw, userId});
        }

    }

    public void deleteUserFromDb(long id) {
        String sql = "DELETE FROM users u WHERE u.user_id = ? AND u.username = ?";

        User user = findUserById(id);

        if (user != null) {
            int userId = user.getUserId();
            String username = user.getUserName();

            getJdbcTemplate().update(sql, new Object[]{userId, username});
        }

    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

}
