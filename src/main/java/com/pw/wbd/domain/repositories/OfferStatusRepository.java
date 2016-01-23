package com.pw.wbd.domain.repositories;

import com.pw.wbd.domain.entities.OfferStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by arade on 20-Jan-16.
 */
public interface OfferStatusRepository extends JpaRepository<OfferStatus, Integer> {
    List<OfferStatus> findByStatus(String value);
    List<OfferStatus> findByCustomer_Id(int value);
    List<OfferStatus> findByOffer_Id(int value);
}
