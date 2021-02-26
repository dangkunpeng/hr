package com.magpie.devOps.holiday;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * SlfHolidayRepo
 */
@Transactional
@Repository
public interface SlfHolidayRepo extends JpaRepository<SlfHoliday, String>{

    List<SlfHoliday> findListBySlfYear(String sflYear);

    @Modifying
    @Query(nativeQuery = true, value="update slf_holiday u set u.holiday = 0 where u.slf_year = :sflYear")
    void resetHolidayByYear(@Param(value="sflYear") String sflYear);

    @Modifying
    @Query(nativeQuery = true, value="update slf_holiday u set u.holiday = 1 where u.slf_date in :list")
    void setHolidayByYear(@Param(value="list") List<String> list);
    
    
    List<SlfHoliday> findListBySlfYearAndHoliday(String slfYear, Integer isHoliday);
    
    @Query(nativeQuery = true, value="select max(slf_year) slf_year from slf_holiday")
    List<String> findMaxSlfYear();
    
}