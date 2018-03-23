package powerlifting.service;

public interface IUserService {

    void insertMaleUserToDatabase(long id, String userName, String pass);

    void insertFemaleUserToDatabase(long id, String userName, String pass);
}
