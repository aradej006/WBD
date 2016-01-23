package com.pw.wbd.domain.repositories;

import com.pw.wbd.domain.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by arade on 20-Jan-16.
 */
public interface DistrictRepository extends JpaRepository<District, Integer> {
    List<District> findByName(String name);
    List<District> findByCity(String city);
}
