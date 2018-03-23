package powerlifting.service;

import powerlifting.dal.ILiftDao;
import powerlifting.dal.IUserDao;
import powerlifting.model.Bench;
import powerlifting.model.Deadlift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import powerlifting.model.Squat;
import powerlifting.model.User;

import java.util.Date;
import java.util.List;

@Service("liftService")
public class LiftService implements ILiftService {

    ILiftDao liftDao;
    IUserDao userDao;
    private Date dateLifted = new Date(System.currentTimeMillis());

    @Autowired
    public LiftService(ILiftDao liftDao, IUserDao userDao) {
        this.liftDao = liftDao;
        this.userDao = userDao;
    }

    public void insertSquatToDatabase(Squat s, long userId) {
        int reps = s.getReps();
        int sets = s.getSets();
        double weightLifted = s.getWeightLifted();

        liftDao.insertSquat(reps, sets, weightLifted, dateLifted, userId);
    }

    public void insertBenchToDatabase(Bench b, long userId) {
        int reps = b.getReps();
        int sets = b.getSets();
        double weightLifted = b.getWeightLifted();

        liftDao.insertBench(reps, sets, weightLifted, dateLifted, userId);
    }

    public void insertDeadliftToDatabase(Deadlift d, long userId) {
        int reps = d.getReps();
        int sets = d.getSets();
        double weightLifted = d.getWeightLifted();

        liftDao.insertDeadlift(reps, sets, weightLifted, dateLifted, userId);
    }

    public void deleteSquatFromDatabase(long liftId, long userId) {
        liftDao.removeSquat(liftId, userId);
    }

    public void deleteBenchFromDatabase(long liftId, long userId) {
        liftDao.removeBench(liftId, userId);
    }

    public void deleteDeadliftFromDatabase(long liftId, long userId) {
        liftDao.removeDeadlift(liftId, userId);
    }

    public List<Squat> getSquatByUserFromDao(long userId) {
        return liftDao.getSquatByUser(userId);
    }

    public List<Bench> getBenchByUserFromDao(long userId) {
        return liftDao.getBenchByUser(userId);
    }

    public List<Deadlift> getDeadliftByUserFromDao(long userId) {
        return liftDao.getDeadliftByUser(userId);
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
