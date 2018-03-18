package service;

import model.Lift;

import java.util.List;

public interface ILiftService {

    void insertLiftIntoDatabase(Lift lift, long userStatId);

    void deleteLiftFromDatabase(long id);

    List<Lift> getLiftsByUser(long userId);

}
