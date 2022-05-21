package com.xmu.haruki.sensors;

import com.xmu.haruki.po.BasicMessage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class Sensor{

    public static final String MOTION_PROPERTY="motion";

    private Integer frequency;
    private String target;
    private String property;
    protected BasicMessage message;
    protected final static Random random=new Random();

    public Sensor(String target,String property){
        this.frequency=12;
        this.target=target;
        this.property=property;
        message=new BasicMessage();
    }

    public Sensor(int frequency,String target,String property){
        if (frequency<=0){
            throw new IllegalArgumentException("Can't accept zero or negative number.");
        }
        else {
            this.frequency=frequency;
        }
        this.target=target;
        this.property=property;
        message=new BasicMessage();
    }

    public String getTarget(){return target;}

    public Integer getFrequency(){
        return this.frequency;
    }

    public void setCommon(){
        message.setTarget(this.target);
        message.setProperty(this.property);
        message.setEventDate(LocalDate.now());
        message.setEventTime(LocalTime.now());
    }
    public BasicMessage genMessage(){
        return message;
    }

    public BasicMessage genExceptionalMessage(){
        return message;
    }
}
