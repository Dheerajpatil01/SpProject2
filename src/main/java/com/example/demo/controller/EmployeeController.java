package com.example.demo.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        model.addAttribute("employee", new Employee());
        return "create";
    }

    @PostMapping("/save")
    public String saveEmployee(
            @Valid @ModelAttribute Employee employee,
            BindingResult result,
            @RequestParam String action,
            Model model) {

        if(result.hasErrors()) {
            return "create";
        }

        service.save(employee);

        if(action.equals("next")) {
            model.addAttribute("employee", new Employee());
            return "create";
        }

        return "redirect:/";
    }

    @GetMapping("/display")
    public String displayEmployees(Model model) {
        model.addAttribute(
                "employees",
                service.getAllEmployees());

        return "display";
    }
    @GetMapping("/raise")
    public String raisePage()
    {
        return "raise";
    }

    @PostMapping("/raiseSalary")
    public String raiseSalary(
            @RequestParam String name,
            @RequestParam int percent,
            Model model)
    {
        if(percent < 1 || percent > 10)
        {
            model.addAttribute(
                    "message",
                    "Percentage must be between 1 and 10");

            return "raise";
        }

        boolean result =
                service.raiseSalary(name, percent);

        if(result)
        {
            model.addAttribute(
                    "message",
                    "Salary Updated Successfully");
        }
        else
        {
            model.addAttribute(
                    "message",
                    "Employee Not Found");
        }

        return "raise";
    }
    @GetMapping("/exit")
    public String exitPage()
    {
        return "exit";
    }
}