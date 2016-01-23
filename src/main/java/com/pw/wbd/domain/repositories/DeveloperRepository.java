package com.pw.wbd.domain.repositories;

import com.pw.wbd.domain.entities.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeveloperRepository extends JpaRepository<Developer, Integer> {

    List<Developer> findByName(String value);
}
