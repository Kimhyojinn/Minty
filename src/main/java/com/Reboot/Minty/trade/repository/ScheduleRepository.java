package com.Reboot.Minty.trade.repository;

import com.Reboot.Minty.trade.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface ScheduleRepository extends JpaRepository<Trade, Long> {
    @Modifying
    @Query("UPDATE Trade t SET t.tradeDate = :tradeDate, t.tradeTime = :tradeTime WHERE t.id = :tradeId")
    void updateScheduleInfo(@Param("tradeId") Long tradeId, @Param("tradeDate") LocalDate tradeDate, @Param("tradeTime") LocalTime tradeTime);
}
