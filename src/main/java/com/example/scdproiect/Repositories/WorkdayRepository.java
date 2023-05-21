package com.example.scdproiect.Repositories;

import com.example.scdproiect.Data.Employee;
import com.example.scdproiect.Data.Workday;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface WorkdayRepository extends JpaRepository<Workday,Long> {

    Optional<Workday> findWorkdayByEmployeeAndDate(Employee employee, LocalDate date);

    @Transactional
    @Modifying
    @Query(value = "update workday set worktime = :workTime where employee_id = :employeeId and workday.date = :date", nativeQuery = true)
    void updateWorkday(@Param("employeeId") long employeeId, @Param("workTime")LocalTime workTime, @Param("date")LocalDate date);

    @Transactional
    @Modifying
    @Query(value = "delete from workday where employee_id = :employeeId and workday.date = :date", nativeQuery = true)
    void deleteWorkday (@Param("employeeId") long employeeId, @Param("date")LocalDate date);
}
