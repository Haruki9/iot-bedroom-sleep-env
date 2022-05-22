package com.xmu.haruki.mapper;

import com.xmu.haruki.information.AirQuality;
import com.xmu.haruki.information.BasicInformation;
import com.xmu.haruki.information.FitbitSleepInformation;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Mapper
public interface InformationMapper {
    int insertBasicInformation(BasicInformation information);

    int insertAirQuality(AirQuality airInformation);

    int insertSleepInformation(FitbitSleepInformation information);

    List<BasicInformation> selectMessage(BasicInformation information);

    List<BasicInformation> selectMessageByTimeInterval(String property, LocalDate beginDate, LocalDate endDate, LocalTime beginTime,LocalTime endTime);

    List<AirQuality> selectAirQualityMessage(BasicInformation information);

    List<AirQuality> selectAirQualityMessageByTimeInterval(String property, LocalDate beginDate, LocalDate endDate, LocalTime beginTime,LocalTime endTime);

    FitbitSleepInformation selectFitBitInformationByDate(LocalDate date);

    List<FitbitSleepInformation> selectFitBitInformationByInterval(LocalDate beginDate,LocalDate endDate);
}
