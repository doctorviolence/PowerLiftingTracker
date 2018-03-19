package powerlifting.service;

import powerlifting.model.Lift;

import java.util.List;

public interface ILiftService {

    void insertLiftIntoDatabase(Lift lift, long userStatId);

    void deleteLiftFromDatabase(long id);

    List<Lift> getLiftsByUser(long userId);

    List<Lift> getAllLiftsInDb();

}
