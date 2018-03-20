import powerlifting.config.ApplicationConfig;
import powerlifting.dal.LiftDao;
import powerlifting.model.Lift;
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
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class LiftDaoTests {

    @Autowired
    private LiftDao liftDao;

    private EmbeddedDatabase database;

    @Before
    public void setup() {
        database = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("powerlifting_tracker.sql")
                .addScripts("daotests.sql")
                .build();

        liftDao.setDataSource(database);
        liftDao.postConstruct();
    }

    @Test
    public void daoInitializedCorrectly() {
        Assert.assertNotNull(liftDao);
    }

    @Test
    public void testInsertLiftIntoDb() {
        Lift bench = new Lift(5, 5, 100);

        liftDao.insertBench(bench, 1);

        List<Lift> getLifts = liftDao.getLiftsByUser(1);

        Assert.assertNotNull(getLifts);
        Assert.assertTrue(getLifts.size() == 2);

        int c = JdbcTestUtils.countRowsInTable(liftDao.getJdbcTemplate(), "lifts");
        Assert.assertTrue(c == 3);

    }

    @Test
    public void testDeleteLiftFromDb() {
        liftDao.removeLift(1);

        int c = JdbcTestUtils.countRowsInTable(liftDao.getJdbcTemplate(), "lifts");
        Assert.assertTrue(c == 1);
    }

    @Test
    public void testLiftType() {
        Lift bench = new Lift(1, 1, 100);

        liftDao.insertBench(bench, 1);

        List<Lift> getLifts = liftDao.getLiftsByUser(1);
        Assert.assertNotNull(getLifts);
        Assert.assertTrue(getLifts.size() == 2);

        for (Lift l : getLifts) {
            if (l.isBench()) {
                String test = l.toString();
                Assert.assertEquals(test, "Bench true");
            } else if (l.isSquat()) {
                String test = l.toString();
                Assert.assertEquals(test, "Squat true");
            } else if (l.isDeadlift()) {
                String test = l.toString();
                Assert.assertEquals(test, "Deadlift true");
            }
        }

    }

    @Test
    public void testLiftByDifferentUsers() {

        List<Lift> getLiftsByUser1 = liftDao.getLiftsByUser(1);
        List<Lift> getLiftsByUser2 = liftDao.getLiftsByUser(2);

        Assert.assertTrue(getLiftsByUser1.size() == 1);
        Assert.assertTrue(getLiftsByUser2.size() == 1);

        Lift bench = new Lift(1, 1, 100);

        liftDao.insertBench(bench, 1);
        liftDao.removeLift(2);

        List<Lift> getUpdatedLiftsByUser1 = liftDao.getLiftsByUser(1);
        List<Lift> getUpdatedLiftsByUser2 = liftDao.getLiftsByUser(2);

        Assert.assertTrue(getUpdatedLiftsByUser1.size() == 2);
        Assert.assertTrue(getUpdatedLiftsByUser2.size() == 0);
    }

    @After
    public void tearDown() {
        liftDao = null;
        database.shutdown();
    }

}
