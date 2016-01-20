package com.pw.wbd.domain.repositories;

import com.pw.wbd.domain.entities.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<Developer, Integer> {

}
