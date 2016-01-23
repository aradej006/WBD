package com.pw.wbd.domain.repositories;

import com.pw.wbd.domain.entities.Preference;
import oracle.jdbc.proxy.annotation.Pre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by arade on 20-Jan-16.
 */
public interface PreferenceRepository extends JpaRepository<Preference, Integer> {
    List<Preference> findByName(String value);
}
