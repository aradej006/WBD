package com.pw.wbd.domain.repositories;

import com.pw.wbd.domain.entities.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by arade on 20-Jan-16.
 */
public interface HouseRepository extends JpaRepository<House, Integer> {

    List<House> findByBuilding_Project_Developer_Name(@Param(value = "name") String name);

    List<House> findByPricePerMeterBetweenAndAreaBetweenAndLandAreaBetweenAndBuilding_District(double startPrice, double endPrice,
                                  double startArea, double endArea,double startLandArea, double endLandArea, String district);

}
