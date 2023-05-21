package com.example.scdproiect.Services;

import com.example.scdproiect.Data.Employee;
import com.example.scdproiect.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> allEmployees(){
        return employeeRepository.findAll();
    }

    public Optional<Employee> insertEmployee(Employee employee){
        employeeRepository.saveAndFlush(employee);
        return employeeRepository.findById(employee.getId());
    }

    public Optional<Employee> updateEmployeeName(long id, String name){
        employeeRepository.updateEmployeeName(id, name);
        return employeeRepository.findById(id);
    }

    public void deleteEmployee(long id){
        employeeRepository.deleteEmployeeById(id);
    }
}
