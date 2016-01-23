package com.pw.wbd.domain.repositories;

import com.pw.wbd.domain.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by arade on 20-Jan-16.
 */
public interface OfferRepository extends JpaRepository<Offer,Integer> {
    List<Offer> findByEmployee_Id(int value);
    List<Offer> findByFlat_Id(int value);
    List<Offer> findByHouse_Id(int value);
    List<Offer> findByType(String value);
    List<Offer> findBySegment_Id(int value);
}
