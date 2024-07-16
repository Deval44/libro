package system.library.libro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import system.library.libro.entity.Rack;
import system.library.libro.service.RackService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/racks")
public class RackController {

    @Autowired
    private RackService rackService;

    @GetMapping
    public List<Rack> getAllRacks(){
        return rackService.getAllRacks();
    }

    @PostMapping("/number/{num}")
    public List<Rack> addRacks(@PathVariable int num){
        return rackService.addRacks(num);
    }
}
