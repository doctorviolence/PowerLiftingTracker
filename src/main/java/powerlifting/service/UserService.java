package powerlifting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import powerlifting.dal.IUserDao;

@Service("userService")
public class UserService implements IUserService {

    private IUserDao userDao;

    @Autowired
    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

}
