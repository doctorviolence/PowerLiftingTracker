import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import powerlifting.config.ApplicationConfig;
import powerlifting.dal.UserDao;
import powerlifting.model.User;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class UserDaoTests {


    @Autowired
    private UserDao userDao;

    private EmbeddedDatabase database;

    @Before
    public void setup() {
        database = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("powerlifting_tracker.sql")
                .addScripts("daotests.sql")
                .build();

        userDao.setDataSource(database);
        userDao.postConstruct();
    }

    @Test
    public void insertNewMaleUser() {
        User user = new User();
        user.setUserId(1234);
        user.setUserName("test");
        user.setPassword("password");
        user.setMale(true);

        userDao.addNewMaleUserToDb(user);

        User test = userDao.findUserById(1234);

        Assert.assertEquals("test", test.getUserName());
        Assert.assertEquals("password", test.getPassword());
        Assert.assertEquals(true, test.isMale());
    }

    @After
    public void tearDown() {
        userDao = null;
        database.shutdown();
    }

}
