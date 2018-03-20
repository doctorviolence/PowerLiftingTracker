import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import powerlifting.controller.LiftController;

@RunWith(SpringRunner.class)
@WebMvcTest(LiftController.class)
public class LiftControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getLiftsInDb() {

    }

}
