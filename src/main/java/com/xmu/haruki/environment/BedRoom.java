package com.xmu.haruki.environment;

import com.xmu.haruki.information.BasicInformation;
import com.xmu.haruki.information.AirQuality;
import com.xmu.haruki.information.FitbitSleepInformation;
import com.xmu.haruki.sensors.AirQualitySensor;
import com.xmu.haruki.sensors.BasicSensor;
import com.xmu.haruki.sensors.FitbitSensor;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class BedRoom extends BasicEnvironment {
    public static final String TEMPERATURE_PROPERTY="temperature";
    public static final String HUMIDITY_PROPERTY="humidity";
    public static final String AIR_QUALITY_PROPERTY="air_quality";
    public static final String LIGHT_PROPERTY="light";
    public static final String NOISE_PROPERTY="noise";

    public static final String TARGET="bedroom";
    private final Logger logger=Logger.getLogger(this.getClass().getName());
    public final static Random random=new Random();
    private BasicInformation airQuality;
    private FitbitSleepInformation sleepInformation;
    private FitbitSensor fitbitSensor;

    private ExecutorService sensorPool= Executors.newFixedThreadPool(7);



    public BedRoom(){
        super.setName(this.getClass().getName());
        informationMap.put("temperature",new BasicInformation(TARGET,TEMPERATURE_PROPERTY));
        informationMap.put("humidity", new BasicInformation(TARGET,HUMIDITY_PROPERTY));
        informationMap.put("noise", new BasicInformation(TARGET,NOISE_PROPERTY));
        informationMap.put("light", new BasicInformation(TARGET,LIGHT_PROPERTY));
        airQuality=new AirQuality(TARGET);
        informationMap.put("airquality", airQuality);
        sleepInformation=new FitbitSleepInformation();
        sleepInformation.init();

        sensorMap.put("temperature",new BasicSensor(informationMap.get("temperature")));
        sensorMap.put("humidity",new BasicSensor(informationMap.get("humidity")));
        sensorMap.put("noise",new BasicSensor(informationMap.get("noise")));
        sensorMap.put("light",new BasicSensor(informationMap.get("light")));
        sensorMap.put("airquality",new AirQualitySensor(informationMap.get("airquality")));
        fitbitSensor=new FitbitSensor(sleepInformation);
    }

    public void startSensors(){
        Collection<BasicSensor> sensors=sensorMap.values();
        for (BasicSensor sensor:sensors){
            sensorPool.execute(sensor);
        }
        sensorPool.execute(fitbitSensor);
    }

    public void init(){
        informationMap.get("temperature").setData(20);
        informationMap.get("humidity").setData(40);
        informationMap.get("noise").setData(50);
        informationMap.get("light").setData(15);
        AirQuality airInformation=((AirQuality)(informationMap.get("airquality")));
        airInformation.setCo(15);
        airInformation.setCo2(1000);
        airInformation.setO2(23);
        airInformation.setPm10(45);
        airInformation.setPm25(10);
        airInformation.setLevel(AirQuality.LEVELS.get(random.nextInt(5)));
    }

    public void startChange(){
        sensorPool.execute(() -> {
            while (true){
                try{
                    environmentChange();
//                    logger.info("change environment");
                    Thread.sleep(1000*30);
                }catch (InterruptedException e){
                    logger.info(e.getMessage());
                }
            }
        });
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

    public void airImprovement(){
        AirQuality airQuality= (AirQuality) informationMap.get("airquality");
        if(airQuality.getCo()>=15)airQuality.setCo(airQuality.getCo()-3);
        if(airQuality.getPm25()>=20)airQuality.setPm25(airQuality.getPm25()-2);
        if(airQuality.getPm10()>=50)airQuality.setPm10(airQuality.getPm10()-5);
        if(airQuality.getCo2()>=1500)airQuality.setCo2(airQuality.getCo2()-random.nextInt(50));
        airQuality.setO2(airQuality.getO2()+1);
        airQuality.setLevel(AirQuality.LEVELS.get(random.nextInt(5)));
    }

    public void airQualityChange(){
        AirQuality airQuality= (AirQuality) informationMap.get("airquality");
        airQuality.setPm25(airQuality.getPm25()+random.nextInt(2)*2);
        airQuality.setPm10(airQuality.getPm10()+random.nextInt(2)*2);
        airQuality.setCo(airQuality.getCo()+1);
        airQuality.setCo2(airQuality.getCo2()+random.nextInt(2)*random.nextInt(50));
        if(airQuality.getO2()>=15)airQuality.setO2(airQuality.getO2()-random.nextInt(2));
        int l=AirQuality.LEVELS.indexOf(airQuality.getLevel());
        airQuality.setLevel(AirQuality.LEVELS.get(l>=1?l-1:0));
    }

    public void warmUp(){
        informationMap.get("temperature").setData(
                informationMap.get("temperature").getData()+2
        );
    }

    public void coolDown(){
        informationMap.get("temperature").setData(
                informationMap.get("temperature").getData()-2
        );
    }

    public void noiseMore(){
        informationMap.get("noise").setData(
                informationMap.get("noise").getData()+3
        );
    }

    public void noiseLess(){
        informationMap.get("noise").setData(
                informationMap.get("noise").getData()-3
        );
    }

    public void humidityMore(){
        informationMap.get("humidity").setData(
                informationMap.get("humidity").getData()+5
        );
    }

    public void humidityLess() {
        informationMap.get("humidity").setData(
                informationMap.get("humidity").getData() - 5
        );
    }

    public void lightUp(){
        informationMap.get("light").setData(
                informationMap.get("light").getData()+2
        );
    }

    public void lightDown(){
        informationMap.get("light").setData(
                informationMap.get("light").getData()-2
        );
    }
}
