package com.xmu.haruki.sensors;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.xmu.haruki.information.BasicInformation;
import com.xmu.haruki.util.MqttUtil;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.logging.Logger;

public class BasicSensor implements Runnable{
    protected final static Logger logger= Logger.getLogger("sensor logger");
    protected final static ObjectMapper objectMapper=new ObjectMapper();
    static {
        objectMapper.registerModule(new JavaTimeModule());
    }
    protected BasicInformation information;
    protected MqttClient client;
    protected String topic;
    private int frequency;

    public BasicSensor(BasicInformation information){
        this.information=information;
        this.client= MqttUtil.getInstance().getClient(information.getProperty()+"client");
        this.frequency=2;
        this.topic=information.getTarget()+"/"+information.getProperty();
    }

    public BasicSensor(BasicInformation information,int frequency){
        this(information);
        this.frequency=frequency;
        this.topic=information.getTarget()+"/"+information.getProperty();
    }

    public void publish(){
        MqttMessage mqttMessage=new MqttMessage();
        mqttMessage.setQos(0);
        mqttMessage.setRetained(true);
        try{
            information.setDateTime();
            mqttMessage.setPayload(objectMapper.writeValueAsBytes(information));
            client.publish(topic, mqttMessage);
        }catch (MqttException | JsonProcessingException e){
            logger.info(e.getMessage());
        }
    }

    @Override
    public void run() {
        while(true){
            try{
                publish();
                Thread.sleep(60/frequency*1000);
            }catch (InterruptedException e){
                logger.info(e.getMessage());
            }
        }
    }
}
