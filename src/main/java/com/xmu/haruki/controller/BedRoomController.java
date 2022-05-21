package com.xmu.haruki.controller;

import com.xmu.haruki.environment.BedRoom;
import com.xmu.haruki.information.AirQuality;
import com.xmu.haruki.information.BasicInformation;
import com.xmu.haruki.po.IntMessage;
import com.xmu.haruki.service.BedRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

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

    @PostMapping("basic-info")
    public void insertBasicInfo(@RequestBody BasicInformation information){
        System.out.println(information);
        bedRoomService.insertBasicInformation(information);
    }

    @PostMapping("air-quality")
    public void insertAirQuality(@RequestBody AirQuality information){
        bedRoomService.insertAirQulity(information);
    }

    @GetMapping("temperature-up")
    public Integer temperatureUp(){
        bedRoom.init();
        Integer data=bedRoom.getInformationMap().get("temperature").getBasicData();
        System.out.println(data);
        return data;
    }
}
