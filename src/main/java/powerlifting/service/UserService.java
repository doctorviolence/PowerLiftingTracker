package powerlifting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import powerlifting.dal.IUserDao;
import powerlifting.model.User;

@Service("userService")
public class UserService implements IUserService {

    private IUserDao userDao;

    @Autowired
    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    public void insertMaleUserToDatabase(long id, String userName, String pass) {
        User user = new User();
        user.setUserId(id);
        user.setUserName(userName);
        user.setPassword(pass);
        user.setMale(true);

        userDao.addNewMaleUserToDb(user);
    }

    public void insertFemaleUserToDatabase(long id, String userName, String pass) {
        User user = new User();
        user.setUserId(id);
        user.setUserName(userName);
        user.setPassword(pass);
        user.setFemale(true);

        userDao.addNewFemaleUserToDb(user);
    }

    public boolean findUserInDb(long id) {
        boolean userExists = false;
        User user = userDao.findUserById(id);

        if (user != null) {
            userExists = true;
        }

        return userExists;
    }

}
