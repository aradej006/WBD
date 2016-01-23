package com.pw.wbd.domain.repositories;

import com.pw.wbd.domain.entities.ValueSubdivisionNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by arade on 20-Jan-16.
 */
public interface ValueSubdivisionNumberRepository extends JpaRepository<ValueSubdivisionNumber, Integer> {
    List<ValueSubdivisionNumber> findBySubdivision_Id(int id);
    List<ValueSubdivisionNumber> findByPreference_Id(int id);
    List<ValueSubdivisionNumber> findByValue(double value);
}
