package com.xmu.haruki.sensors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xmu.haruki.information.BasicInformation;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class AirQualitySensor extends BasicSensor{

    public AirQualitySensor(BasicInformation information) {
        super(information);
    }

    public AirQualitySensor(BasicInformation information, int frequency) {
        super(information, frequency);
    }


    @Override
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
}
