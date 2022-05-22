package com.xmu.haruki.sensors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.xmu.haruki.information.FitbitSleepInformation;
import com.xmu.haruki.util.MqttUtil;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Random;
import java.util.logging.Logger;

public class FitbitSensor implements Runnable{
    private final Logger logger= Logger.getLogger(this.getClass().getName());
    private final static Random random=new Random();
    private final static ObjectMapper objectMapper=new ObjectMapper();
    static {
        objectMapper.registerModule(new JavaTimeModule());
    }
    private FitbitSleepInformation information;
    private MqttClient client;
    private String topic;

    public FitbitSensor(FitbitSleepInformation information){
        this.client= MqttUtil.getInstance().getClient("Fitbit sleep tracker");
        this.information=information;
        this.topic="bedroom/fitbit-data";
    }

    public void publish(){
        MqttMessage mqttMessage=new MqttMessage();
        mqttMessage.setQos(0);
        mqttMessage.setRetained(true);
        try{
            mqttMessage.setPayload(objectMapper.writeValueAsBytes(information));
            client.publish(topic, mqttMessage);
        }catch (MqttException | JsonProcessingException e){
            logger.info(e.getMessage());
        }
    }

    @Override
    public void run() {
        while (true){
            try{
                Thread.sleep(60*60*1000*24);
                publish();
                information.nextDay();
//                Thread.sleep(1000*2);
            }catch (InterruptedException e){
                logger.info(e.getMessage());
            }
        }
    }
}
