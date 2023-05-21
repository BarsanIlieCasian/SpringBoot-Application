package com.example.scdproiect.Repositories;

import com.example.scdproiect.Data.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Transactional
    @Modifying
    @Query(value = "update employee set name = :name where id = :id", nativeQuery = true)
    int updateEmployeeName (@Param("id") long id,@Param("name") String name);

    @Modifying
    @Query(value = "DELETE FROM workday WHERE employee_id = :id", nativeQuery = true)
    void deleteWorkdaysByEmployeeId(@Param("id") long id);

    @Modifying
    @Query(value = "DELETE FROM employee WHERE id = :id", nativeQuery = true)
    void deleteEmployee(@Param("id") long id);

    @Transactional
    default void deleteEmployeeById(long id) {
        deleteWorkdaysByEmployeeId(id);
        deleteEmployee(id);
    }
}
