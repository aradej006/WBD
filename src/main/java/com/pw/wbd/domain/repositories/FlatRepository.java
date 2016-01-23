package com.pw.wbd.domain.repositories;

import com.pw.wbd.domain.entities.Flat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by arade on 20-Jan-16.
 */
public interface FlatRepository extends JpaRepository<Flat, Integer> {

    List<Flat> findByPricePerMeterBetweenAndAreaBetweenAndFlorBetweenAndBuilding_District(double startPrice, double endPrice,
                                                                                            double startArea, double endArea,int startFlor, int endFlor, String district);
    List<Flat> findByArea(double value);
    List<Flat> findByFlor(int value);
    List<Flat> findByPricePerMeter(double value);
    List<Flat> findByCondition(String value);
    List<Flat> findByBuilding_Id(int value);
    List<Flat> findByEmployee_Id(int value);
    List<Flat> findByRoomsQuantity(int value);
}
