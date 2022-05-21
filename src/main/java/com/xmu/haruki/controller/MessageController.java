package com.xmu.haruki.controller;

import com.xmu.haruki.po.AirQualityMessage;
import com.xmu.haruki.po.BasicMessage;
import com.xmu.haruki.po.IntMessage;
import com.xmu.haruki.sensors.Sensor;
import com.xmu.haruki.service.BedRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

//@RestController
//@RequestMapping(value = "/",produces = "application/json;charset=utf-8")
public class MessageController {

    @Autowired
    BedRoomService bedRoomService;

    @GetMapping("dashboard")
    public Object messageDashboard(){
        return new ModelAndView("dashboard");
    }

    @GetMapping("temperature")
    public Object temperatureMessage(@RequestParam(required = false) LocalDate beginDate, @RequestParam(required = false) LocalDate endDate,
                                     @RequestParam(required = false) LocalTime beginTime, @RequestParam(required = false) LocalTime endTime){
        return bedRoomService.queryMessageByInterval(Sensor.TEMPERATURE_PROPERTY,beginDate,endDate,beginTime,endTime);
    }

    @GetMapping("humidity")
    public Object humidityMessage(@RequestParam(required = false) LocalDate beginDate, @RequestParam(required = false) LocalDate endDate,
                                  @RequestParam(required = false) LocalTime beginTime, @RequestParam(required = false) LocalTime endTime){
        return bedRoomService.queryMessageByInterval(Sensor.HUMIDITY_PROPERTY,beginDate,endDate,beginTime,endTime);
    }
    @GetMapping("noise")
    public Object noiseMessage(@RequestParam(required = false) LocalDate beginDate, @RequestParam(required = false) LocalDate endDate,
                               @RequestParam(required = false) LocalTime beginTime, @RequestParam(required = false) LocalTime endTime){
        return bedRoomService.queryMessageByInterval(Sensor.NOISE_PROPERTY,beginDate,endDate,beginTime,endTime);
    }
    @GetMapping("light")
    public Object lightMessage(@RequestParam(required = false) LocalDate beginDate, @RequestParam(required = false) LocalDate endDate,
                               @RequestParam(required = false) LocalTime beginTime, @RequestParam(required = false) LocalTime endTime){
        return bedRoomService.queryMessageByInterval(Sensor.LIGHT_PROPERTY,beginDate,endDate,beginTime,endTime);
    }
    @GetMapping("airquality")
    public Object airQualityMessage(@RequestParam(required = false) LocalDate beginDate, @RequestParam(required = false) LocalDate endDate,
                                    @RequestParam(required = false) LocalTime beginTime, @RequestParam(required = false) LocalTime endTime){
        return bedRoomService.queryAirMessageByInterval(Sensor.AIR_QUALITY_PROPERTY,beginDate,endDate,beginTime,endTime);
    }
}
