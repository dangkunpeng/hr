package com.magpie.devOps.ops;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagpieRepo extends JpaRepository<Magpie, String> {

    List<Magpie> findByDayLike(String day);
}
