package com.xmu.haruki.sensors;

import com.xmu.haruki.po.BasicMessage;
import com.xmu.haruki.po.IntMessage;

public class Humidity extends Sensor{
    public final static int MAX_SLEEP_HUMIDITY=60;
    public final static int MIN_SLEEP_HUMIDITY=20;


    public Humidity(String target) {
        super(target, Sensor.HUMIDITY_PROPERTY);
        message=new IntMessage();
    }

    public Humidity(int frequency, String target) {
        super(frequency, target, Sensor.HUMIDITY_PROPERTY);
        message=new IntMessage();
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
        genExceptionalData(random.nextBoolean());
        return message;
    }


    public void genData() {
        IntMessage mes=((IntMessage)message);
        if (mes.getData()==null){
            mes.setData(random.nextInt(MAX_SLEEP_HUMIDITY-MIN_SLEEP_HUMIDITY)+MIN_SLEEP_HUMIDITY);
        }
        int data=mes.getData();
        int addition=0;
        if (data>MAX_SLEEP_HUMIDITY){
            addition=-random.nextInt(20);
        }
        else if (data<MIN_SLEEP_HUMIDITY){
            addition=random.nextInt(20);
        }
        else addition=random.nextInt(40)-20;
        mes.setData(addition+data);
    }

    public void genExceptionalData(boolean type) {
        if (type){
            ((IntMessage)message).setData(MAX_SLEEP_HUMIDITY+ random.nextInt(20));
        }
        else
            ((IntMessage)message).setData(MIN_SLEEP_HUMIDITY-random.nextInt(20));
    }
}
