package com.xmu.haruki.controller;

import com.xmu.haruki.environment.BedRoom;
import com.xmu.haruki.information.AirQuality;
import com.xmu.haruki.information.BasicInformation;
import com.xmu.haruki.information.FitbitSleepInformation;
import com.xmu.haruki.service.BedRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "bedroom",produces = "application/json;charset=utf-8")
public class BedRoomController {
    @Autowired
    BedRoom bedRoom;

    @Autowired
    BedRoomService bedRoomService;

    @GetMapping("dashboard")
    public Object bedroomDashboard(){
//        BasicInformation information=new BasicInformation();
//        information.setEventDate(LocalDate.now());
//
//        information.setProperty(BedRoom.TEMPERATURE_PROPERTY);
//        List<BasicInformation> temperatureList= bedRoomService.queryMessage(information);
//        information.setProperty(BedRoom.HUMIDITY_PROPERTY);
//        List<BasicInformation> humidityList=bedRoomService.queryMessage(information);
//        information.setProperty(BedRoom.NOISE_PROPERTY);
//        List<BasicInformation> noiseList=bedRoomService.queryMessage(information);
//        information.setProperty(BedRoom.LIGHT_PROPERTY);
//        List<BasicInformation> lightList=bedRoomService.queryMessage(information);
        ModelAndView dashboard=new ModelAndView("dashboard");
        return dashboard;
    }

    @GetMapping("basic-info")
    public Object selectBasicInfo(@RequestParam String property, @RequestParam(required = false) LocalDate date){
        BasicInformation information=new BasicInformation();
        information.setProperty(property);
        if (date==null)information.setEventDate(LocalDate.now());
        else information.setEventDate(date);
        return bedRoomService.queryBasicInfo(information);
    }

    @GetMapping("air-info")
    public Object selectAirQualityInfo(@RequestParam String property, @RequestParam(required = false) LocalDate date){
        BasicInformation information=new BasicInformation();
        information.setProperty(property);
        if (date==null)information.setEventDate(LocalDate.now());
        else information.setEventDate(date);
        return bedRoomService.queryAirQualityMessage(information);
    }

    @GetMapping("sleep-info")
    public Object querySleepInfo(@RequestParam LocalDate date){
        return bedRoomService.queryFitbitSleepInformationByDate(date);
    }

    @GetMapping("sevendays-sleep-info")
    public Object querySevenDaysSleepInfo(@RequestParam LocalDate lastDate){
        return bedRoomService.queryFitbitInformationSevendays(lastDate);
    }

    @PostMapping("basic-info")
    public void insertBasicInfo(@RequestBody BasicInformation information){
        bedRoomService.insertBasicInformation(information);
    }

    @PostMapping("air-quality")
    public void insertAirQuality(@RequestBody AirQuality information){
        bedRoomService.insertAirQuality(information);
    }

    @PostMapping("sleep-data")
    public void insertSleepData(@RequestBody FitbitSleepInformation information){
        bedRoomService.insertFitBitSleepData(information);
    }

    @CrossOrigin
    @GetMapping("warm-up")
    public void warmUp(){
        int times=BedRoom.random.nextInt(2)+1;
        while (times>0){
            bedRoom.warmUp();
            times--;
        }
    }

    @CrossOrigin
    @GetMapping("cool-down")
    public void coolDown(){
        int times=BedRoom.random.nextInt(2)+1;
        while (times>0){
            bedRoom.coolDown();
            times--;
        }
    }

    @CrossOrigin
    @GetMapping("humidity-more")
    public void humidityMore(){
        int times=BedRoom.random.nextInt(2)+1;
        while (times>0){
            bedRoom.humidityMore();
            times--;
        }
    }

    @CrossOrigin
    @GetMapping("humidity-less")
    public void humidityLess(){
        int times=BedRoom.random.nextInt(2)+1;
        while (times>0){
            bedRoom.humidityLess();
            times--;
        }
    }

    @CrossOrigin
    @GetMapping("light-down")
    public void lightDown(){
        int times=BedRoom.random.nextInt(2)+1;
        while (times>0){
            bedRoom.lightDown();
            times--;
        }
    }

    @CrossOrigin
    @GetMapping("light-up")
    public void lightUp(){
        int times=BedRoom.random.nextInt(2)+1;
        while (times>0){
            bedRoom.lightUp();
            times--;
        }
    }


    @CrossOrigin
    @GetMapping("noise-more")
    public void noiseMore(){
        int times=BedRoom.random.nextInt(2)+1;
        while (times>0){
            bedRoom.noiseMore();
            times--;
        }
    }

    @CrossOrigin
    @GetMapping("noise-less")
    public void noiseLess(){
        int times=BedRoom.random.nextInt(2)+1;
        while (times>0){
            bedRoom.noiseLess();
            times--;
        }
    }

    @CrossOrigin
    @GetMapping("air-improve")
    public void airImprove(){
        int times=BedRoom.random.nextInt(2)+1;
        while (times>0){
            bedRoom.airImprovement();
            times--;
        }
    }
}
