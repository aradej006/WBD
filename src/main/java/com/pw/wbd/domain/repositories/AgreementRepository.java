package com.pw.wbd.domain.repositories;

import com.pw.wbd.domain.entities.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by arade on 20-Jan-16.
 */

public interface AgreementRepository extends JpaRepository<Agreement, Integer>{
    List<Agreement> findByType(String type);
    List<Agreement> findByCustomer_Id(int id);
    List<Agreement> findByEmployee_Id(int id);
    List<Agreement> findBySegment_Id(int id);
    List<Agreement> findByHouse_Id(int id);
    List<Agreement> findByFlat_Id(int id);
}
