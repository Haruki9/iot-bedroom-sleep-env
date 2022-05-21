package com.xmu.haruki.information;

import lombok.*;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class AirQuality extends BasicInformation{
    private Integer pm10;
    private Integer pm25;
    private Integer co2;
    private Integer co;
    private Integer o2;
    private String level;

    public AirQuality(String target){
        super(target,"air-quality");
    }
}
