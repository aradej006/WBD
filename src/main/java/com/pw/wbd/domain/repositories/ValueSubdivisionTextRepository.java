package com.pw.wbd.domain.repositories;

import com.pw.wbd.domain.entities.ValueSubdivisionNumber;
import com.pw.wbd.domain.entities.ValueSubdivisionText;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by arade on 20-Jan-16.
 */
public interface ValueSubdivisionTextRepository extends JpaRepository<ValueSubdivisionText, Integer> {
    List<ValueSubdivisionText> findBySubdivision_Id(int id);
    List<ValueSubdivisionText> findByPreference_Id(int id);
    List<ValueSubdivisionText> findByValue(String value);
}
