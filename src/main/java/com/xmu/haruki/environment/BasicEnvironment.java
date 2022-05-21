package com.xmu.haruki.environment;

import com.xmu.haruki.information.BasicInformation;
import com.xmu.haruki.sensors.BasicSensor;
import lombok.Data;

import java.util.HashMap;


@Data
public class BasicEnvironment {
    private String name;
    protected HashMap<String, BasicInformation> informationMap=new HashMap<>();
    protected HashMap<String, BasicSensor> sensorMap=new HashMap<>();

}
