package com.xmu.haruki.information;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class BasicInformation {
    protected Integer id;
    protected LocalDate eventDate;
    protected LocalTime eventTime;
    protected String target;
    protected String property;
    protected Integer basicData;

    public BasicInformation(String target,String property){
        this.target=target;
        this.property=property;
    }

    public void setDateTime(){
        this.eventDate=LocalDate.now();
        this.eventTime=LocalTime.now();
    }
}
