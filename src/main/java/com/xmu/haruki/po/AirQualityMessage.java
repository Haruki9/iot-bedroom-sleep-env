package com.xmu.haruki.po;

import lombok.*;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AirQualityMessage extends BasicMessage{
    private Integer CO;
    private Integer PM25;
    private Integer PM10;
    private Integer O2;
    private Integer CO2;
    private String level;
}
