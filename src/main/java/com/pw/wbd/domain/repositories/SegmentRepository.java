package com.pw.wbd.domain.repositories;

import com.pw.wbd.domain.entities.Segment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by arade on 20-Jan-16.
 */
public interface SegmentRepository extends JpaRepository<Segment,Integer> {

    List<Segment> findByPricePerMeterBetweenAndAreaBetweenAndFlorBetweenAndBuilding_District(double startPrice, double endPrice,
                                                                                             double startArea, double endArea, int startFlor, int endFlor, String district);
    List<Segment> findByArea(double value);
    List<Segment> findByPricePerMeter(double value);
    List<Segment> findByBuilding_Id(int value);
    List<Segment> findByEmployee_Id(int value);
    List<Segment> findByFlor(int value);
    List<Segment> findByRoomsQuantity(int value);
}
