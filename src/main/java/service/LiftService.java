package service;

import dal.LiftDao;
import model.Lift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("liftService")
public class LiftService implements ILiftService {

    LiftDao liftDao;

    @Autowired
    public LiftService(LiftDao liftDao) {
        this.liftDao = liftDao;
    }

    public void insertLiftIntoDatabase(Lift lift, long userStatId) {
        if (lift.isSquat()) {
            liftDao.insertSquat(lift, userStatId);
        } else if (lift.isBench()) {
            liftDao.insertBench(lift, userStatId);
        } else if (lift.isDeadlift()) {
            liftDao.insertDeadlift(lift, userStatId);
        }
    }

    public void deleteLiftFromDatabase(long liftId) {
        liftDao.removeLift(liftId);
    }

    public List<Lift> getLiftsByUser(long userId) {
        return liftDao.getLiftsByUser(userId);
    }

}
