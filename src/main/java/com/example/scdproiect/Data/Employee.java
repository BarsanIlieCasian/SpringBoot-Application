package com.example.scdproiect.Data;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "enrolldate", updatable = false, nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate enrolldate = LocalDate.now();


    @OneToMany(targetEntity = Workday.class,mappedBy = "employee")
    private Set<Workday> workdaySet;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getEnrolldate() {
        return enrolldate;
    }

    public void setEnrolldate(LocalDate enrolldate) {
        this.enrolldate = enrolldate;
    }
}
