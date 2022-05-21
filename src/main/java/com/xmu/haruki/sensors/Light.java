package com.xmu.haruki.sensors;

import com.xmu.haruki.po.BasicMessage;
import com.xmu.haruki.po.IntMessage;

public class Light extends Sensor{
    public static final int MAX_LIGHT=30;
    public static final int MIN_LIGHT=0;

    public Light(String target) {
        super(target, Sensor.LIGHT_PROPERTY);
        message=new IntMessage();
    }

    public Light(int frequency, String target) {
        super(frequency, target, Sensor.LIGHT_PROPERTY);
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
            mes.setData(random.nextInt(MAX_LIGHT-MIN_LIGHT)+MIN_LIGHT);
        }
        int data=mes.getData();
        int addition=0;
        if (data>MAX_LIGHT){
            addition=random.nextInt(5)-5;
        }
        else if (data<=MIN_LIGHT){
            addition=random.nextInt(5);
        }
        else addition=random.nextInt(10)-5;
        mes.setData(addition+data);
    }

    public void genExceptionalData(boolean type) {
        if (type){
            ((IntMessage)message).setData(MAX_LIGHT+ random.nextInt(15));
        }
        else
            ((IntMessage)message).setData(0);
    }
}
