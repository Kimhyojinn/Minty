package com.Reboot.Minty.trade.service;

import com.Reboot.Minty.trade.entity.Trade;
import com.Reboot.Minty.trade.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
    public void updateScheduleInfo(Long tradeId, LocalDate tradeDate, LocalTime tradeTime) {
        scheduleRepository.updateScheduleInfo(tradeId, tradeDate, tradeTime);
    }
}
