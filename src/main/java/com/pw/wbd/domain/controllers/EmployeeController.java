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

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

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
        List<String> attrs = new LinkedList<>();
        for (Field field : Employee.class.getDeclaredFields()) {
            attrs.add(field.getName());
        }
        model.addAttribute("attrs", attrs);
        return "employee/list";
    }

    @RequestMapping(value = "/employees/{attribute}/{value}", method = RequestMethod.GET)
    public String listFiltered(@PathVariable String attribute, @PathVariable String value, Model model) {
        List<Employee> objects = new LinkedList<>();
        if(attribute.equals("id")){
            objects.add(employeeRepository.findOne(Integer.parseInt(value)));
            if(objects.get(0)==null) objects.remove(0);
        }else if(attribute.equals("position")){
            objects = employeeRepository.findByPosition(value);
        }else if(attribute.equals("lastname")){
            objects = employeeRepository.findByLastname(value);
        }else if(attribute.equals("firstname")){
            objects = employeeRepository.findByFirstname(value);
        }else if(attribute.equals("email")){
            objects = employeeRepository.findByEmail(value);
        }else if(attribute.equals("phone")){
            objects = employeeRepository.findByPhone(value);
        }else if(attribute.equals("secondname")){
            objects = employeeRepository.findBySecondname(value);
        }else if(attribute.equals("pesel")){
            objects = employeeRepository.findByPesel(value);
        }

        model.addAttribute("objects", objects);
        List<String> attrs = new LinkedList<>();
        for (Field field : Employee.class.getDeclaredFields()) {
            attrs.add(field.getName());
        }
        model.addAttribute("attrs", attrs);
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
