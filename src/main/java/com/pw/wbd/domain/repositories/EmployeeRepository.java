package com.pw.wbd.domain.repositories;

import com.pw.wbd.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by arade on 20-Jan-16.
 */
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    List<Employee> findByPosition(String value);
    List<Employee> findByFirstname(String value);
    List<Employee> findByLastname(String value);
    List<Employee> findByEmail(String value);
    List<Employee> findByPhone(String value);
    List<Employee> findBySecondname(String value);
    List<Employee> findByPesel(String value);
}
