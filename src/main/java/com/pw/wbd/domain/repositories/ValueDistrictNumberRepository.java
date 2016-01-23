package com.pw.wbd.domain.repositories;

import com.pw.wbd.domain.entities.ValueDistrictNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by arade on 20-Jan-16.
 */
public interface ValueDistrictNumberRepository extends JpaRepository<ValueDistrictNumber,Integer> {
    List<ValueDistrictNumber> findByDistrict_Id(int id);
    List<ValueDistrictNumber> findByPreference_Id(int id);
    List<ValueDistrictNumber> findByValue(double value);
}
