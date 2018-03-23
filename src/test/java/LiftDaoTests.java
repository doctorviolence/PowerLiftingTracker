import powerlifting.config.ApplicationConfig;
import powerlifting.dal.LiftDao;
import powerlifting.model.Bench;
import powerlifting.model.Deadlift;
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
import powerlifting.model.Squat;

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
        liftDao.insertBench(5, 5, 100, null, 1);

        List<Bench> getLifts = liftDao.getBenchByUser(1);

        Assert.assertNotNull(getLifts);
        Assert.assertTrue(getLifts.size() == 2);

        int c = JdbcTestUtils.countRowsInTable(liftDao.getJdbcTemplate(), "bench_lifts");
        Assert.assertTrue(c == 3);

    }

    @Test
    public void testDeleteLiftFromDb() {
        liftDao.removeBench(1, 1);

        int c = JdbcTestUtils.countRowsInTable(liftDao.getJdbcTemplate(), "bench_lifts");
        Assert.assertTrue(c == 1);
    }

    @Test
    public void testLiftType() {
        liftDao.insertBench(1, 1, 100, null, 1);

        List<Bench> getLifts = liftDao.getBenchByUser(1);
        List<Squat> getLifts2 = liftDao.getSquatByUser(2);

        Assert.assertNotNull(getLifts);
        Assert.assertNotNull(getLifts2);

        Assert.assertTrue(getLifts.size() == 2);
        Assert.assertTrue(getLifts2.size() == 1);

        for (Lift l : getLifts) {
            if (l.getClass() == Bench.class && l.getClass() != Deadlift.class && l.getClass() != Squat.class)
                Assert.assertTrue(l.getClass() == Bench.class);
        }

        for (Lift l : getLifts) {
            System.out.println(l.toString());
        }

        for (Lift l : getLifts2) {
            System.out.println(l.toString());
        }

    }

    @Test
    public void testLiftByDifferentUsers() {

        List<Bench> getLiftsByUser1 = liftDao.getBenchByUser(1);
        List<Bench> getLiftsByUser2 = liftDao.getBenchByUser(2);

        Assert.assertTrue(getLiftsByUser1.size() == 1);
        Assert.assertTrue(getLiftsByUser2.size() == 1);

        liftDao.insertBench(1, 1, 100, null, 1);
        liftDao.removeBench(2, 2);

        List<Bench> getUpdatedLiftsByUser1 = liftDao.getBenchByUser(1);
        List<Bench> getUpdatedLiftsByUser2 = liftDao.getBenchByUser(2);

        Assert.assertTrue(getUpdatedLiftsByUser1.size() == 2);
        Assert.assertTrue(getUpdatedLiftsByUser2.size() == 0);
    }

    @Test
    public void testGettingTopFivePRs() {
        List<Squat> squatPRs = liftDao.getTopFiveSquatPRs(1);
        Assert.assertTrue(squatPRs.size() == 1);
        Assert.assertFalse(squatPRs.size() == 5);
        Assert.assertTrue(squatPRs.get(0).getWeightLifted() == 100);

        List<Bench> benchPRs = liftDao.getTopFiveBenchPRs(1);
        Assert.assertTrue(benchPRs.size() == 2);
        Assert.assertTrue(benchPRs.get(0).getWeightLifted() == 140);
        Assert.assertTrue(benchPRs.get(1).getWeightLifted() == 110);

        List<Deadlift> deadliftPRs = liftDao.getTopFiveDeadliftPRs(5);
        Assert.assertTrue(deadliftPRs.size() == 0);
    }

    @After
    public void tearDown() {
        liftDao = null;
        database.shutdown();
    }

}
