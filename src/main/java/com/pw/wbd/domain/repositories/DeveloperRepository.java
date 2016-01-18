package com.pw.wbd.domain.repositories;

import com.pw.wbd.domain.entities.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface DeveloperRepository extends JpaRepository<Developer, Long>{

    List<Developer> findByName(@Param("name") String name);

}
