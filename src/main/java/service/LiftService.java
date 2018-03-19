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
    public LiftService(LiftDao liftDao){
        this.liftDao = liftDao;
    }

    public void insertLiftIntoDatabase(Lift lift, long userStatId) {
    /*    if (lift.isSquat()) {
            int reps = lift.getReps();
            int sets = lift.getSets();
            double weightLifted = lift.getWeightLifted();
            Date dateLifted = lift.getDateLifted();

            liftDao.insertSquat(reps, sets, weightLifted, dateLifted, userStatId);
        } else if (lift.isBench()) {
            int reps = lift.getReps();
            int sets = lift.getSets();
            double weightLifted = lift.getWeightLifted();
            Date dateLifted = lift.getDateLifted();

            liftDao.insertBench(reps, sets, weightLifted, dateLifted, userStatId);
        } else if (lift.isDeadlift()) {
            int reps = lift.getReps();
            int sets = lift.getSets();
            double weightLifted = lift.getWeightLifted();
            Date dateLifted = lift.getDateLifted();

            liftDao.insertDeadlift(reps, sets, weightLifted, dateLifted, userStatId);
        }*/
    }

    public void deleteLiftFromDatabase(long liftId) {
        liftDao.removeLift(liftId);
    }

    public List<Lift> getLiftsByUser(long userId) {
        return liftDao.getLiftsByUser(userId);
    }

}
