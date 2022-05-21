package com.xmu.haruki.sensors;

import com.xmu.haruki.po.BasicMessage;
import com.xmu.haruki.po.IntMessage;

public class Temperature extends Sensor{
    public final static int MAX_SLEEP_TEMP=28;
    public final static int MIN_SLEEP_TEMP=10;

    public Temperature(String target){
        super(target,Sensor.TEMPERATURE_PROPERTY);
        message=new IntMessage();
    }

    public Temperature(int frequency, String target){
        super(frequency,target, Sensor.TEMPERATURE_PROPERTY);
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
            mes.setData(random.nextInt(MAX_SLEEP_TEMP-MIN_SLEEP_TEMP)+MIN_SLEEP_TEMP);
        }
        int data=mes.getData();
        int addition=0;
        if (data>MAX_SLEEP_TEMP){
            addition=-random.nextInt(3);
        }
        else if (data<MIN_SLEEP_TEMP){
            addition=random.nextInt(3);
        }
        else addition=random.nextInt(6)-3;
        mes.setData(addition+data);
    }

    public void genExceptionalData(boolean type) {
        if (type){
            ((IntMessage)message).setData(MAX_SLEEP_TEMP+ random.nextInt(5));
        }
        else
            ((IntMessage)message).setData(MIN_SLEEP_TEMP+random.nextInt(5)-5);
    }
}
