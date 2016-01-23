package com.pw.wbd.domain.repositories;

import com.pw.wbd.domain.entities.ValueDistrictNumber;
import com.pw.wbd.domain.entities.ValueDistrictText;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by arade on 20-Jan-16.
 */
public interface ValueDistrictTextRepository extends JpaRepository<ValueDistrictText, Integer> {
    List<ValueDistrictText> findByDistrict_Id(int id);
    List<ValueDistrictText> findByPreference_Id(int id);
    List<ValueDistrictText> findByValue(String value);
}
