package powerlifting.controller;

import powerlifting.model.Lift;
import org.assertj.core.util.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import powerlifting.service.LiftService;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController("lift")
public class LiftController {

    private LiftService service;

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    public LiftController(LiftService service){
        this.service = service;
    }

    @RequestMapping("getliftsbyuser")
    @ResponseBody
    public List<Lift> getLiftsByUser(Long id) {
        return service.getLiftsByUser(id);
    }

    @RequestMapping("getlifts")
    @ResponseBody
    public List<Lift> getAllLifts() {
        return service.getAllLiftsInDb();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void create(@RequestBody Lift lift, Long id) {
        Preconditions.checkNotNull(lift);
        service.insertLiftIntoDatabase(lift, id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        service.deleteLiftFromDatabase(id);
    }

}
