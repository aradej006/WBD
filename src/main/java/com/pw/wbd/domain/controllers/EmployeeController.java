package com.pw.wbd.domain.controllers;

import com.pw.wbd.domain.entities.Employee;
import com.pw.wbd.domain.repositories.BuildingRepository;
import com.pw.wbd.domain.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by arade on 21-Jan-16.
 */
@Controller
@Transactional
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping("/employee/new")
    public String newEmployee(Model model) {
        model.addAttribute("object", new Employee());
        model.addAttribute("buildings", employeeRepository.findAll());
        return "employee/form";
    }

    @RequestMapping(value = "employee", method = RequestMethod.POST)
    public String saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/employee/get/" + employee.getId();
    }

    @RequestMapping("employee/get/{id}")
    public String showEmployee(@PathVariable Integer id, Model model) {
        model.addAttribute("object", employeeRepository.findOne(id));
        return "employee/show";
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("objects", employeeRepository.findAll());
        return "employee/list";
    }

    @RequestMapping("employee/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("object", employeeRepository.getOne(id));
        return "employee/form";
    }

    @RequestMapping("employee/delete/{id}")
    public String delete(@PathVariable Integer id){
        employeeRepository.delete(id);
        return "redirect:/employees";
    }
}
