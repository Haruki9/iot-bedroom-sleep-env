package com.xmu.haruki.configuration;

import com.xmu.haruki.environment.BedRoom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BedRoomConfiguration {
    @Value("${bedroom.enable-sensor}")
    private boolean enableSensor;

    @Bean
    public BedRoom bedRoom(){
        BedRoom bedRoom=new BedRoom();
        bedRoom.init();
        bedRoom.startChange();
        if (enableSensor)bedRoom.startSensors();
        return bedRoom;
    }
}
