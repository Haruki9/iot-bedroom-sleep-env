package com.xmu.haruki;

import com.xmu.haruki.client.Simulator;
import com.xmu.haruki.sensors.*;
import com.xmu.haruki.util.MqttUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulatorClient {
    public static void main(String[] args) {
        ExecutorService simulatorPool=Executors.newFixedThreadPool(5);
        MqttUtil util=MqttUtil.getInstance();
        Sensor tempSensor=new Temperature("bedroom");
        Simulator tempSimulator=new Simulator(util.getClient("temp-client"),Simulator.TEMPERATURE_TOPIC,tempSensor);
        simulatorPool.execute(tempSimulator);

        Sensor humiditySensor=new Humidity("bedroom");
        Simulator humiditySimulator=new Simulator(util.getClient("humidity-client"),Simulator.HUMIDITY_TOPIC,humiditySensor);
        simulatorPool.execute(humiditySimulator);

        Sensor lightSensor=new Light("bedroom");
        Simulator lightSimulator=new Simulator(util.getClient("light-client"),Simulator.LIGHT_TOPIC,lightSensor);
        simulatorPool.execute(lightSimulator);

        Sensor noiseSensor=new Noise("bedroom");
        Simulator noiseSimulator=new Simulator(util.getClient("noise-client"),Simulator.NOISE_TOPIC,noiseSensor);
        simulatorPool.execute(noiseSimulator);

        AirQuality airSensor=new AirQuality("bedroom");
        Simulator airSimulator=new Simulator(util.getClient("air-client"),Simulator.AIR_QUALITY_TOPIC,airSensor);
        simulatorPool.execute(airSimulator);
    }
}
