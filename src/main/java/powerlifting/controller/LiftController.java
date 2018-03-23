package powerlifting.controller;

import org.springframework.http.ResponseEntity;
import powerlifting.model.Bench;
import powerlifting.model.Deadlift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import powerlifting.model.Squat;
import powerlifting.service.LiftService;
import powerlifting.service.UserService;

import java.util.List;

/*

Notes: Controller accepts e.g. squat as a JSON object with the following syntax: {"reps":5,"sets":3,"weightLifted":100}

*/
@RestController("liftController")
public class LiftController {

    private LiftService liftService;

    private UserService userService;

    @Autowired
    public LiftController(LiftService liftService, UserService userService) {
        this.liftService = liftService;
        this.userService =  userService;
    }

    @GetMapping("/squat")
    @ResponseBody
    public List<Squat> getSquatByUser(@RequestParam(required = true) Long id) {
        return liftService.getSquatByUserFromDao(id);
    }

    @GetMapping("/bench")
    @ResponseBody
    public List<Bench> getBenchByUser(@RequestParam(required = true) Long id) {
        return liftService.getBenchByUserFromDao(id);
    }

    @GetMapping("/deadlift")
    @ResponseBody
    public List<Deadlift> getDeadliftByUser(@RequestParam(required = true) Long id) {
        return liftService.getDeadliftByUserFromDao(id);
    }

    @PostMapping(value = "/insertsquat", consumes = "application/json")
    public ResponseEntity insertNewSquat(@RequestBody Squat squat, @RequestParam("id") Long id) {
        boolean user = userService.findUserInDb(id);

        if (!user) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such user.");
        } else {
            liftService.insertSquatToDatabase(squat, id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Squat inserted");
        }
    }

    @PostMapping(value = "/insertbench", consumes = "application/json")
    public ResponseEntity insertNewBench(@RequestBody Bench bench, @RequestParam("id") Long id) {
        boolean user = userService.findUserInDb(id);

        if (!user) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such user.");
        } else {
            liftService.insertBenchToDatabase(bench, id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Bench inserted");
        }
    }

    @PostMapping(value = "/insertdeadlift", consumes = "application/json")
    public ResponseEntity insertNewDeadlift(@RequestBody Deadlift deadlift, @RequestParam("id") Long id) {
        boolean user = userService.findUserInDb(id);

        if (!user) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such user.");
        } else {
            liftService.insertDeadliftToDatabase(deadlift, id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Deadlift inserted");
        }
    }

    @DeleteMapping(value = "deletesquat")
    public ResponseEntity deleteSquat(@RequestParam("id") Long id, @RequestParam("userId") Long userId) {
        liftService.deleteSquatFromDatabase(id, userId);
        return ResponseEntity.status(HttpStatus.OK).body("Squat deleted");
    }

    @DeleteMapping(value = "deletebench")
    public ResponseEntity deleteBench(@RequestParam("id") Long id, @RequestParam("userId") Long userId) {
        liftService.deleteBenchFromDatabase(id, userId);
        return ResponseEntity.status(HttpStatus.OK).body("Bench deleted");
    }

    @DeleteMapping(value = "deletedeadlift")
    public ResponseEntity deleteDeadlift(@RequestParam("id") Long id, @RequestParam("userId") Long userId) {
        liftService.deleteDeadliftFromDatabase(id, userId);
        return ResponseEntity.status(HttpStatus.OK).body("Deadlift deleted");
    }

}
