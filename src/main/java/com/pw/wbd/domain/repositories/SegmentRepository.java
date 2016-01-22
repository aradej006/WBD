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

}
