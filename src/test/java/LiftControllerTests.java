import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import powerlifting.Application;
import powerlifting.controller.LiftController;
import powerlifting.model.Bench;
import powerlifting.model.Deadlift;
import powerlifting.model.Squat;
import powerlifting.service.LiftService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ComponentScan({"powerlifting"})
@ContextConfiguration(classes = Application.class)
@WebMvcTest(LiftController.class)
public class LiftControllerTests {

    private MockMvc mvc;
    private JacksonTester<Deadlift> deadliftJacksonTester;

    @Mock
    private LiftService service;

    @InjectMocks
    private LiftController liftController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(liftController).build();
    }

    @Test
    public void testIsMockMvcNull() {
        Assert.assertNotNull(mvc);
    }

    @Test
    public void testIsServiceNull() {
        Assert.assertNotNull(service);
    }

    @Test
    public void testIsControllerNull() {
        Assert.assertNotNull(liftController);
    }

    @Test
    public void testGetBenchByUserInDb() throws Exception {
        Bench bench = new Bench(5, 3, 100, true);
        bench.setUserId(1);
        bench.setLiftId(69);

        List<Bench> list = new ArrayList<>();
        list.add(bench);

        service.insertBenchToDatabase(bench, 1);

        when(service.getBenchByUserFromDao(1)).thenReturn(list);

        mvc.perform(get("/{id}/bench", 1)
                .param("id", "1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].liftId").value(69))
                .andExpect(jsonPath("$[0].reps").value(5))
                .andExpect(jsonPath("$[0].sets").value(3))
                .andExpect(jsonPath("$[0].weightLifted").value(100.0))
                .andExpect(jsonPath("$[0].bench").value(true))
                .andExpect(jsonPath("$[0].userId").value(1))
                .andDo(print());
    }

    @Test
    public void testGetSquatsByUserInDb() throws Exception {
        Squat squat = new Squat(1, 1, 180, true);
        squat.setUserId(1);
        squat.setLiftId(69);

        Squat squat2 = new Squat(3, 3, 140, true);
        squat2.setUserId(1);
        squat2.setLiftId(87);

        List<Squat> list = new ArrayList<>();
        list.add(squat);
        list.add(squat2);

        service.insertSquatToDatabase(squat, 1);
        service.insertSquatToDatabase(squat2, 1);

        when(service.getSquatByUserFromDao(1)).thenReturn(list);

        mvc.perform(get("/{id}/squat", 1)
                .param("id", "1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].liftId").value(69))
                .andExpect(jsonPath("$[1].liftId").value(87))
                .andExpect(jsonPath("$[0].weightLifted").value(180.0))
                .andExpect(jsonPath("$[1].weightLifted").value(140.0))
                .andExpect(jsonPath("$[0].squat").value(true))
                .andExpect(jsonPath("$[1].squat").value(true))
                .andExpect(jsonPath("$[0].userId").value(1))
                .andExpect(jsonPath("$[1].userId").value(1))
                .andDo(print());
    }

    @Test
    public void testInsertDeadliftIntoDb() throws Exception {
        //Deadlift deadlift = new Deadlift(3, 3, 180.0, true, 1);
        //deadlift.setUserId(1);

        mvc.perform(post("/newDeadlift")
                .param("userId", "1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(deadliftJacksonTester.write(new Deadlift(3, 3, 180.0, true)).getJson()))
                .andExpect(status().isCreated())
                .andDo(print());

        /*MockHttpServletResponse response = mvc.perform(post("/newDeadlift")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(deadliftJacksonTester.write(new Deadlift(3, 3, 180.0, true, 69)).getJson()))
                .andReturn()
                .getResponse();

        Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());*/
    }

    @Test
    public void testInsertDeadliftIntoDbWithWrongUserId() throws Exception {
        mvc.perform(post("/newDeadlift")
                .param("userId", "53")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(deadliftJacksonTester.write(new Deadlift(3, 3, 180.0, true)).getJson()))
                .andExpect(status().isBadRequest())
                .andDo(print());

        /*MockHttpServletResponse response = mvc.perform(post("/newDeadlift")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(deadliftJacksonTester.write(new Deadlift(3, 3, 180.0, true, 69)).getJson()))
                .andReturn()
                .getResponse();

        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());*/
    }

    @Test
    public void testRemoveDeadliftFromDb() {
       // throw new NotImplementedException();
    }

    @After
    public void tearDown() {
        service = null;
        mvc = null;
    }

}
