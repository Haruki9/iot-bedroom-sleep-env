package com.xmu.haruki.sensors;

import com.xmu.haruki.po.BasicMessage;
import com.xmu.haruki.po.IntMessage;

public class Noise extends Sensor{
    public final static Integer MAX_SLEEP_DB=40;
    public final static Integer MIN_SLEEP_DB=15;
    public Noise(String target) {
        super(target, Sensor.NOISE_PROPERTY);
        message=new IntMessage();
    }

    public Noise(int frequency, String target) {
        super(frequency, target, Sensor.NOISE_PROPERTY);
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
            mes.setData(random.nextInt(MAX_SLEEP_DB-MIN_SLEEP_DB)+MIN_SLEEP_DB);
        }
        int data=mes.getData();
        int addition=0;
        if (data>MAX_SLEEP_DB){
            addition=-random.nextInt(5);
        }
        else if (data<MIN_SLEEP_DB){
            addition=random.nextInt(5);
        }
        else addition=random.nextInt(10)-5;
        mes.setData(addition+data);
    }

    public void genExceptionalData(boolean type) {
        if (type){
            ((IntMessage)message).setData(MAX_SLEEP_DB + random.nextInt(15));
        }
        ((IntMessage)message).setData(MIN_SLEEP_DB - random.nextInt(15));
    }
}
