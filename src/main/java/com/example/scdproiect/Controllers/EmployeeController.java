package com.example.scdproiect.Controllers;

import com.example.scdproiect.Data.Employee;
import com.example.scdproiect.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path = "/all")
    public List<Employee> allEmployees() {return employeeService.allEmployees();}

    @PostMapping(path = "/post",consumes = "application/json")
    public ResponseEntity postEmployee(@RequestBody Employee employee)
    {
        Optional<Employee> createdEmployee = employeeService.insertEmployee(employee);
        if (createdEmployee.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("The employee was not inserted in the database");
        }
    }

    @PutMapping(path = "put")
    public ResponseEntity putEmployeeName (@RequestParam(name = "id") long id, @RequestParam(name = "name") String name){
        Optional<Employee> updatedEmployee = employeeService.updateEmployeeName(id, name);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedEmployee.get());
    }

    @DeleteMapping(path = "delete")
    public ResponseEntity deleteEmployeeById (@RequestParam(name = "id") long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().body(("Employee with id= " + id + " has been deleted "));
    }
}
