package com.xmu.haruki.service;


import com.xmu.haruki.information.AirQuality;
import com.xmu.haruki.information.BasicInformation;
import com.xmu.haruki.information.FitbitSleepInformation;
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

    public List<BasicInformation> queryBasicInfo(BasicInformation information){
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

    public FitbitSleepInformation queryFitbitSleepInformationByDate(LocalDate date){return informationMapper.selectFitBitInformationByDate(date);}

    public void insertBasicInformation(BasicInformation information){
        informationMapper.insertBasicInformation(information);
    }

    public void insertAirQuality(AirQuality airQuality){
        informationMapper.insertAirQuality(airQuality);
    }

    public void insertFitBitSleepData(FitbitSleepInformation information){informationMapper.insertSleepInformation(information);}

    public List<FitbitSleepInformation> queryFitbitInformationSevendays(LocalDate endDate){
        LocalDate beginDate=endDate.minusDays(7);
        return informationMapper.selectFitBitInformationByInterval(beginDate,endDate);
    }

}
