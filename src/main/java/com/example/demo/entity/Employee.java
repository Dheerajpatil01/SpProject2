package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message="Name is required")
    @Pattern(
    		regexp = "^[A-Za-z]+( [A-Za-z]+){0,2}$",
    		message = "Only alphabets and maximum 2 spaces allowed"
    		)
    		private String name;
    

    @Min(value=18,message="Minimum age is 18")
    @Max(value=60,message="Maximum age is 60")
    private Integer age;

    private String designation;

    private Double salary;

    public Employee() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}