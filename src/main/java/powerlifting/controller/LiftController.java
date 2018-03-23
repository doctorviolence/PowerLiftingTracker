package powerlifting.controller;

import org.springframework.http.ResponseEntity;
import powerlifting.model.Bench;
import powerlifting.model.Deadlift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import powerlifting.model.Squat;
import powerlifting.service.LiftService;

import java.util.List;

/*

Notes: Controller accepts e.g. squat as a JSON object with the following syntax: {"reps":5,"sets":3,"weightLifted":100}

*/
@RestController("liftController")
public class LiftController {

    private LiftService service;

    @Autowired
    public LiftController(LiftService service) {
        this.service = service;
    }

    @GetMapping("/squat")
    @ResponseBody
    public List<Squat> getSquatByUser(@RequestParam(required = true) Long id) {
        return service.getSquatByUserFromDao(id);
    }

    @GetMapping("/bench")
    @ResponseBody
    public List<Bench> getBenchByUser(@RequestParam(required = true) Long id) {
        return service.getBenchByUserFromDao(id);
    }

    @GetMapping("/deadlift")
    @ResponseBody
    public List<Deadlift> getDeadliftByUser(@RequestParam(required = true) Long id) {
        return service.getDeadliftByUserFromDao(id);
    }

    @PostMapping(value = "/insertsquat", consumes = "application/json")
    public ResponseEntity insertNewSquat(@RequestBody Squat squat, @RequestParam("id") Long id) {
        boolean user = service.findUserInDb(id);

        if (!user) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such user.");
        } else {
            service.insertSquatToDatabase(squat, id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Squat inserted");
        }
    }

    @PostMapping(value = "/insertbench", consumes = "application/json")
    public ResponseEntity insertNewBench(@RequestBody Bench bench, @RequestParam("id") Long id) {
        boolean user = service.findUserInDb(id);

        if (!user) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such user.");
        } else {
            service.insertBenchToDatabase(bench, id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Bench inserted");
        }
    }

    @PostMapping(value = "/insertdeadlift", consumes = "application/json")
    public ResponseEntity insertNewDeadlift(@RequestBody Deadlift deadlift, @RequestParam("id") Long id) {
        boolean user = service.findUserInDb(id);

        if (!user) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such user.");
        } else {
            service.insertDeadliftToDatabase(deadlift, id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Deadlift inserted");
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
