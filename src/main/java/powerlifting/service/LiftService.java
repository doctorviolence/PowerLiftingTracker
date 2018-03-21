package powerlifting.service;

import powerlifting.dal.ILiftDao;
import powerlifting.model.Bench;
import powerlifting.model.Deadlift;
import powerlifting.model.Lift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import powerlifting.model.Squat;

import java.util.Date;
import java.util.List;

@Service("liftService")
public class LiftService implements ILiftService {

    ILiftDao liftDao;

    @Autowired
    public LiftService(ILiftDao liftDao) {
        this.liftDao = liftDao;
    }

    public void insertLiftIntoDatabase(Lift lift, long userId) {
        int reps = lift.getReps();
        int sets = lift.getSets();
        double weightLifted = lift.getWeightLifted();
        Date dateLifted = new Date(System.currentTimeMillis());

        if (lift.getClass() == Squat.class) {
            liftDao.insertSquat(reps, sets, weightLifted, dateLifted, userId);
        } else if (lift.getClass() == Bench.class) {
            liftDao.insertBench(reps, sets, weightLifted, dateLifted, userId);
        } else if (lift.getClass() == Deadlift.class) {
            liftDao.insertDeadlift(reps, sets, weightLifted, dateLifted, userId);
        }
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

}
