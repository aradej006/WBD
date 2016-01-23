package com.pw.wbd.domain.repositories;

import com.pw.wbd.domain.entities.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by arade on 20-Jan-16.
 */
public interface BuildingRepository extends JpaRepository<Building, Integer>{
    List<Building> findByCity(String city);
    List<Building> findByStreet(String street);
    List<Building> findByBuildingNumber(String value);
    List<Building> findByPostCode(String value);
    List<Building> findByDistrict(String value);
    List<Building> findByFlorQuantity(int value);
    List<Building> findByType(String value);
    List<Building> findByProject_Id(int value);
}
