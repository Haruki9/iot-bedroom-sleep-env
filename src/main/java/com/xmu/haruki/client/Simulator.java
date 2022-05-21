package com.xmu.haruki.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.xmu.haruki.po.BasicMessage;
import com.xmu.haruki.sensors.Sensor;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import java.util.Random;

public class Simulator implements Runnable{
    public static final String TEMPERATURE_TOPIC="monitor/temperature";
    public static final String HUMIDITY_TOPIC="monitor/humidity";
    public static final String AIR_QUALITY_TOPIC="monitor/airQuality";
    public static final String LIGHT_TOPIC="monitor/light";
    public static final String NOISE_TOPIC="monitor/noise";

    public static final ObjectMapper objectMapper=new ObjectMapper();
    private int interval;
    private boolean onExcepPeriod;
    private int excepPeriod;
    private MqttClient client;
    private String topic;

    private Sensor sensor;

    public Simulator(MqttClient client,String topic,Sensor sensor){
        this.client=client;
        this.sensor=sensor;
        objectMapper.registerModule(new JavaTimeModule());
        this.topic=
                sensor.getTarget()+"/"+topic;
        interval= 60/sensor.getFrequency();
        onExcepPeriod=false;
        excepPeriod=0;
    }

    private void genException(){
        onExcepPeriod=new Random().nextBoolean();
        //产生两分钟的异常值
        excepPeriod=new Random().nextInt(120/interval);
    }

    private void publish(){
        MqttMessage mqttMessage=new MqttMessage();
        mqttMessage.setQos(0);
        mqttMessage.setRetained(true);
        BasicMessage message;
        if (!onExcepPeriod){
            message =sensor.genMessage();
        }else {
            message =sensor.genExceptionalMessage();
            excepPeriod--;
            if (excepPeriod==0){
                onExcepPeriod=false;
            }
        }
        try{
            mqttMessage.setPayload(objectMapper.writeValueAsBytes(message));
            client.publish(topic, mqttMessage);
        }catch (MqttException| JsonProcessingException e){
            e.printStackTrace();
        }
//        SqlSession session=SqlUtil.getSessionFactory().openSession(true);
//        InformationMapper mapper=session.getMapper(InformationMapper.class);
//        if (message.getProperty().equals(Sensor.AIR_QUALITY_PROPERTY))
//            mapper.insertAirMessage((AirQualityMessage) message);
//        else
//            mapper.insertIntMessage((IntMessage) message);
//        session.close();
    }

    @Override
    public void run(){
        int randomTime=new Random().nextInt(24)+12;;
        while (true){
            if (randomTime==0){
                randomTime=new Random().nextInt(24)+12;
                genException();
            }
            try {
                publish();
                Thread.sleep(interval*1000);
            } catch ( InterruptedException e) {
                e.printStackTrace();
            }
            randomTime--;
        }
    }

}
