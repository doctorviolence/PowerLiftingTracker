package controller;

import model.Lift;
import org.assertj.core.util.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.LiftService;

import java.util.List;

@RestController
class LiftController {

    private LiftService service;

    @Autowired
    public LiftController(LiftService service){
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Lift> getLiftsByUser(long id) {
        return service.getLiftsByUser(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void create(@RequestBody Lift lift, long id) {
        Preconditions.checkNotNull(lift);
        service.insertLiftIntoDatabase(lift, id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        service.deleteLiftFromDatabase(id);
    }

}
