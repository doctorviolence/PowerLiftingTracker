package powerlifting.controller;

import org.springframework.http.ResponseEntity;
import powerlifting.dal.exceptions.DbException;
import powerlifting.model.Bench;
import powerlifting.model.Deadlift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import powerlifting.model.Squat;
import powerlifting.model.User;
import powerlifting.service.LiftService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static sun.plugin2.util.PojoUtil.toJson;

@RestController("liftController")
public class LiftController {

    private LiftService service;

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

    @PostMapping("/newSquat")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertNewSquat(@RequestBody Squat squat, Long id) {
        service.insertSquatToDatabase(squat, id);
    }

    @PostMapping("/newBench")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertNewBench(@RequestBody Bench bench, Long id) {
        service.insertBenchToDatabase(bench, id);
    }

    @PostMapping(value = "/newDeadlift", consumes = "application/json")
    //@ResponseStatus//(HttpStatus.CREATED)
    public ResponseEntity<Deadlift> insertNewDeadlift(@RequestBody Deadlift deadlift, @RequestParam("userId") Long id) throws DbException {
        User user = service.findUserInDb(id);

        if (user != null) {
            service.insertDeadliftToDatabase(deadlift);
            return new ResponseEntity<Deadlift>(deadlift, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
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
