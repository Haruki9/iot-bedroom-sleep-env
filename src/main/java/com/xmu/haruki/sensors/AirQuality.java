package com.xmu.haruki.sensors;


import com.xmu.haruki.po.AirQualityMessage;
import com.xmu.haruki.po.BasicMessage;

import java.util.HashMap;
import java.util.Random;

//产生时间序列数据，空气质量指标包括PM2.5、PM10、CO、CO2、O2
public class AirQuality extends Sensor{
    public final static int MAX_PM_25=50;
    public final static int MIN_PM_25=0;
    public final static int MAX_PM_10=30;
    public final static int MIN_PM_10=0;
    public final static int MAX_CO=5;
    public final static int MIN_CO=0;
    public final static int MAX_CO2=20;
    public final static int MIN_CO2=10;
    public final static int MIN_O2=40;
    public final static int MAX_O2=60;

    public final static HashMap<Integer,String> LEVEL_MAP=new HashMap<>();
    static {
        LEVEL_MAP.put(0,"优");
        LEVEL_MAP.put(1,"良");
        LEVEL_MAP.put(2,"中");
        LEVEL_MAP.put(3,"劣");
        LEVEL_MAP.put(4,"差");
    }

    private final static Random random=new Random();
    private int frequency;

    public AirQuality(String target) {
        super(target,Sensor.AIR_QUALITY_PROPERTY);
        this.frequency=12;
        message=new AirQualityMessage();
    }

    public AirQuality(int frequency, String target) {
        this(target);
        this.frequency=frequency;
        message=new AirQualityMessage();
    }

    @Override
    public BasicMessage genMessage() {
        super.setCommon();
        genData();
        return message;
    }

    @Override
    public BasicMessage genExceptionalMessage(){
        super.setCommon();
        genExceptionalData();
        return message;
    }


    public void genData() {
        AirQualityMessage airMessage=((AirQualityMessage)message);
        airMessage.setCO(random.nextInt(MAX_CO)+1);
        airMessage.setO2(random.nextInt(20)+40);
        airMessage.setPM10(random.nextInt(MAX_PM_10)+1);
        if (airMessage.getCO2()==null)airMessage.setCO2(random.nextInt(MAX_CO2)+1);
        if (airMessage.getPM25()==null)airMessage.setPM25(random.nextInt(MAX_PM_25)+1);
        int addition=0;
        if (airMessage.getPM25()<MAX_PM_25/2){
            addition=random.nextInt(3)*random.nextInt(2);
        }
        else if (airMessage.getPM25()<MAX_PM_25){
            int v=random.nextInt(3);
            addition=random.nextBoolean()?v:-v;
        }
        else addition=random.nextInt(5)-5;
        System.out.println(addition);
        airMessage.setPM25(airMessage.getPM25()+addition);
        airMessage.setCO2(airMessage.getCO2()+addition);
        airMessage.setLevel(LEVEL_MAP.get(random.nextInt(5)));
    }

    public void genExceptionalData() {
        AirQualityMessage airMessage=((AirQualityMessage)message);
        airMessage.setCO2(random.nextInt()+MAX_CO2);
        airMessage.setPM25(random.nextInt()+MAX_PM_25);
        airMessage.setLevel(LEVEL_MAP.get(random.nextInt(5)));
    }
}
