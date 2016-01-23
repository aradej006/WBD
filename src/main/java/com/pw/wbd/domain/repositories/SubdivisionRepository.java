package com.pw.wbd.domain.repositories;

import com.pw.wbd.domain.entities.Subdivision;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by arade on 20-Jan-16.
 */
public interface SubdivisionRepository extends JpaRepository<Subdivision, Integer> {

    List<Subdivision> findByName(String name);
    List<Subdivision> findByCity(String city);
    List<Subdivision> findByDistrict_Id(int id);
}
