package com.xmu.haruki.service;


import com.xmu.haruki.information.AirQuality;
import com.xmu.haruki.information.BasicInformation;
import com.xmu.haruki.mapper.InformationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class BedRoomService {
    @Autowired
    InformationMapper informationMapper;

    public List<BasicInformation> queryMessage(BasicInformation information){
        return informationMapper.selectMessage(information);
    }

    public List<AirQuality> queryAirQualityMessage(BasicInformation information){
        return informationMapper.selectAirQualityMessage(information);
    }

    public List<BasicInformation> queryMessageByInterval(String property, LocalDate beginDate, LocalDate endDate, LocalTime beginTime, LocalTime endTime){
        return informationMapper.selectMessageByTimeInterval(property, beginDate, endDate, beginTime, endTime);
    }

    public List<AirQuality> queryAirMessageByInterval(String property, LocalDate beginDate, LocalDate endDate, LocalTime beginTime, LocalTime endTime){
        return informationMapper.selectAirQualityMessageByTimeInterval(property, beginDate, endDate, beginTime, endTime);
    }

    public void insertBasicInformation(BasicInformation information){
        informationMapper.insertBasicInformation(information);
    }

    public void insertAirQulity(AirQuality airQuality){
        informationMapper.insertAirQuality(airQuality);
    }

}
