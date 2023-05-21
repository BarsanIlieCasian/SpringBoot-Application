package com.example.scdproiect.Services;

import com.example.scdproiect.Data.Workday;
import com.example.scdproiect.Repositories.EmployeeRepository;
import com.example.scdproiect.Repositories.WorkdayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class WorkdayService {
    @Autowired
    private WorkdayRepository workdayRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


    public List<Workday> allWorkdays(){
        return workdayRepository.findAll();
    }

    public Optional<Workday> insertWorkday(Workday workday){
        workdayRepository.saveAndFlush(workday);
        return workdayRepository.findById(workday.getId());
    }

    public Optional<Workday> updateWorkday(long employeeId, LocalTime workTime, LocalDate date){
        workdayRepository.updateWorkday(employeeId, workTime, date);
        return workdayRepository.findWorkdayByEmployeeAndDate(employeeRepository.findById(employeeId).get(),date);
    }

    public void deleteWorkday(long employeeId, LocalDate date){
        workdayRepository.deleteWorkday(employeeId, date);
    }
}
