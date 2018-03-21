package powerlifting.controller;

import org.springframework.http.MediaType;
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

@RestController("liftController")
public class LiftController {

    private LiftService service;

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    public LiftController(LiftService service) {
        this.service = service;
    }

    @GetMapping("/{id}/squat")
    @ResponseBody
    public List<Squat> getSquatByUser(Long id) {
        return service.getSquatByUserFromDao(id);
    }

    @GetMapping("/{id}/bench")
    @ResponseBody
    public List<Bench> getBenchByUser(@RequestParam(required = true) Long id) {
        return service.getBenchByUserFromDao(id);
    }

    @GetMapping("/{id}/deadlift")
    @ResponseBody
    public List<Deadlift> getDeadliftByUser(Long id) {
        return service.getDeadliftByUserFromDao(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
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
