package com.example.scdproiect.Controllers;

import com.example.scdproiect.Data.Workday;
import com.example.scdproiect.Services.WorkdayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/workday")
public class WorkdayController {
    @Autowired
    private WorkdayService workdayService;


    @GetMapping(path = "/all")
    public List<Workday> allWorkdays(){return workdayService.allWorkdays();}

    @PostMapping(path = "/post", consumes = "application/json")
    public ResponseEntity postWorkday (@RequestBody Workday workday){
        Optional<Workday> createdWorkday = workdayService.insertWorkday(workday);
        return ResponseEntity.ok().body(createdWorkday);
    }

    @PutMapping(path = "/put")
    public ResponseEntity putWorkday (@RequestParam(name = "employee_id") long employeeId, @RequestParam(name = "date") LocalDate date, @RequestParam(name = "workTime")LocalTime wokrtime){
        Optional<Workday> updatedWorkday = workdayService.updateWorkday(employeeId, wokrtime, date);
        return ResponseEntity.ok().body(updatedWorkday);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity deteWorkday (@RequestParam(name = "employee_id") long employeeId, @RequestParam(name = "date") LocalDate date){
        workdayService.deleteWorkday(employeeId, date);
        return ResponseEntity.ok().body("The workday of employee with id= " + employeeId + " from " + date + " has been deleted");
    }
}
