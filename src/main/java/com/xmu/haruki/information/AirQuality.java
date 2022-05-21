package com.xmu.haruki.information;

import com.xmu.haruki.environment.BedRoom;
import lombok.*;

import java.util.ArrayList;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class AirQuality extends BasicInformation{
    public static ArrayList<String> LEVELS=new ArrayList<>();
    static {
        LEVELS.add("优");LEVELS.add("良");LEVELS.add("中");LEVELS.add("劣");LEVELS.add("差");
    }

    private Integer pm10;
    private Integer pm25;
    private Integer co2;
    private Integer co;
    private Integer o2;
    private String level;

    public AirQuality(String target){
        super(target, BedRoom.AIR_QUALITY_PROPERTY);
    }
}
