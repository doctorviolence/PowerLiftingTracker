import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import powerlifting.Application;
import powerlifting.controller.LiftController;
import powerlifting.model.Bench;
import powerlifting.service.LiftService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ComponentScan({"powerlifting"})
@ContextConfiguration(classes = Application.class)
@WebMvcTest(LiftController.class)
public class LiftControllerTests {

    @Autowired
    private MockMvc mvc;

    @Mock
    private LiftService service;

    @InjectMocks
    private LiftController liftController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mvc = MockMvcBuilders.standaloneSetup(liftController).build();
    }

    @Test
    public void isMockMvcNull() {
        Assert.assertNotNull(mvc);
    }

    @Test
    public void isServiceNull() {
        Assert.assertNotNull(service);
    }

    @Test
    public void isControllerNull() {
        Assert.assertNotNull(liftController);
    }

    @Test
    public void getBenchByUserInDb() throws Exception {
        Bench bench = new Bench(5, 3, 100, true);
        bench.setUserId(1);
        bench.setLiftId(69);

        List<Bench> list = new ArrayList<>();
        list.add(bench);

        service.insertLiftIntoDatabase(bench, 1);

        when(service.getBenchByUserFromDao(1)).thenReturn(list);

        /*
        
        ERROR HERE: "Request processing failed; nested exception is java.lang.NullPointerException"

        Albeit leaving this for now...

         */
        mvc.perform(get("/{id}/bench", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("liftId").value(1))
                .andExpect(jsonPath("reps").value(1))
                .andExpect(jsonPath("sets").value(1))
                .andExpect(jsonPath("weightLifted").value(100))
                .andExpect(jsonPath("bench").value(true))
                .andExpect(jsonPath("userId").value(1));
    }

    @After
    public void tearDown() {
        service = null;
        mvc = null;
    }

}
