package powerlifting.controller;

import powerlifting.model.Bench;
import powerlifting.model.Deadlift;
import powerlifting.model.Lift;
import org.assertj.core.util.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import powerlifting.model.Squat;
import powerlifting.service.LiftService;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController("lift")
public class LiftController {

    private LiftService service;

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    public LiftController(LiftService service) {
        this.service = service;
    }

    @RequestMapping("getliftsbyuser")
    @ResponseBody
    public List<Squat> getSquatByUser(Long id) {
        return service.getSquatByUserFromDao(id);
    }

    @RequestMapping("getliftsbyuser")
    @ResponseBody
    public List<Bench> getBenchByUser(Long id) {
        return service.getBenchByUserFromDao(id);
    }

    @RequestMapping("getliftsbyuser")
    @ResponseBody
    public List<Deadlift> getDeadliftByUser(Long id) {
        return service.getDeadliftByUserFromDao(id);
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

    @RequestMapping(value = "deletesquat/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteSquat(@PathVariable("id") Long id, Long userId) {
        service.deleteSquatFromDatabase(id, userId);
    }

    @RequestMapping(value = "deletebench/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteBench(@PathVariable("id") Long id, Long userId) {
        service.deleteBenchFromDatabase(id, userId);
    }

    @RequestMapping(value = "deletedeadlift/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteDeadlift(@PathVariable("id") Long id, Long userId) {
        service.deleteDeadliftFromDatabase(id, userId);
    }

}
