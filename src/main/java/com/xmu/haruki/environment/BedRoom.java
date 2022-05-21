package com.xmu.haruki.environment;

import com.xmu.haruki.information.BasicInformation;
import com.xmu.haruki.information.AirQuality;
import com.xmu.haruki.sensors.AirQualitySensor;
import com.xmu.haruki.sensors.BasicSensor;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BedRoom extends BasicEnvironment {
    public static final String TEMPERATURE_PROPERTY="temperature";
    public static final String HUMIDITY_PROPERTY="humidity";
    public static final String AIR_QUALITY_PROPERTY="air_quality";
    public static final String LIGHT_PROPERTY="light";
    public static final String NOISE_PROPERTY="noise";


    private final static Random random=new Random();
    private BasicInformation airQuality;
    private ExecutorService sensorPool= Executors.newFixedThreadPool(7);

    public BedRoom(){
        super.setName(this.getClass().getName());
        informationMap.put("temperature",new BasicInformation("bedroom","temperature"));
        informationMap.put("humidity", new BasicInformation("bedroom","humidity"));
        informationMap.put("noise", new BasicInformation("bedroom","noise"));
        informationMap.put("light", new BasicInformation("bedroom","light"));
        airQuality=new AirQuality("bedroom");
        informationMap.put("airquality", airQuality);

        sensorMap.put("temperature",new BasicSensor(informationMap.get("temperature")));
        sensorMap.put("humidity",new BasicSensor(informationMap.get("humidity")));
        sensorMap.put("noise",new BasicSensor(informationMap.get("noise")));
        sensorMap.put("light",new BasicSensor(informationMap.get("light")));
        sensorMap.put("airquality",new AirQualitySensor(informationMap.get("airquality")));
    }

    public void startSensors(){
        Collection<BasicSensor> sensors=sensorMap.values();
        for (BasicSensor sensor:sensors){
            sensorPool.execute(sensor);
        }
    }

    public void init(){
        informationMap.get("temperature").setBasicData(20);
        informationMap.get("humidity").setBasicData(40);
        informationMap.get("noise").setBasicData(50);
        informationMap.get("light").setBasicData(15);
        AirQuality airInformation=((AirQuality)(informationMap.get("airquality")));
        airInformation.setCo(3);
        airInformation.setCo2(30);
        airInformation.setO2(60);
        airInformation.setPm10(23);
        airInformation.setPm25(0);
        airInformation.setLevel("优");
    }

    public void environmentChange(){
        if (random.nextBoolean()){
            warmUp();
        }
        else coolDown();
        if (random.nextBoolean()){
            noiseMore();
        }
        else noiseLess();
        if (random.nextBoolean()){
            lightDown();
        }
        else lightUp();
        if (random.nextBoolean()){
            humidityLess();
        }
        else humidityMore();
        airQualityChange();
    }

    public void airQualityChange(){

    }

    public void warmUp(){
        informationMap.get("temperature").setBasicData(
                informationMap.get("temperature").getBasicData()+2
        );
    }

    public void coolDown(){
        informationMap.get("temperature").setBasicData(
                informationMap.get("temperature").getBasicData()-2
        );
    }

    public void noiseMore(){
        informationMap.get("noise").setBasicData(
                informationMap.get("noise").getBasicData()+3
        );
    }

    public void noiseLess(){
        informationMap.get("noise").setBasicData(
                informationMap.get("noise").getBasicData()-3
        );
    }

    public void humidityMore(){
        informationMap.get("humidity").setBasicData(
                informationMap.get("humidity").getBasicData()+5
        );
    }

    public void humidityLess() {
        informationMap.get("humidity").setBasicData(
                informationMap.get("humidity").getBasicData() - 5
        );
    }

    public void lightUp(){
        informationMap.get("light").setBasicData(
                informationMap.get("light").getBasicData()+2
        );
    }

    public void lightDown(){
        informationMap.get("light").setBasicData(
                informationMap.get("light").getBasicData()-2
        );
    }


}
