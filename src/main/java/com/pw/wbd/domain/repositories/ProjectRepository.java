package com.pw.wbd.domain.repositories;

import com.pw.wbd.domain.entities.Developer;
import com.pw.wbd.domain.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by arade on 20-Jan-16.
 */
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findByDeveloper(Developer developer);
    List<Project> findByName(String value);
    List<Project> findByDeveloper_Id(int id);
}
