package powerlifting.dal;

import powerlifting.dal.exceptions.DbException;
import powerlifting.model.User;

import javax.sql.DataSource;

public interface IUserDao {

    void setDataSource(DataSource ds);

    User findUserById(long userId);

    void addNewFemaleUserToDb(User user);

    void addNewMaleUserToDb(User user);

    void changeUserPassword(long id, String pw) throws DbException;

    void deleteUserFromDb(long id) throws DbException;

    boolean findForeignKeyConstraints(long id);

}
