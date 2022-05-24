package com.xmu.haruki.util;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.HashMap;

public class MqttUtil {
    private HashMap<String, MqttClient> clientMap=new HashMap<>();

    public MqttUtil(){

    }

    public static class MqttSingleton{
        public static final MqttUtil instance=new MqttUtil();
    }

    public static MqttUtil getInstance(){
        return MqttSingleton.instance;
    }

    public MqttClient getClient(String clientId){
        if (clientMap.containsKey(clientId)){
            return clientMap.get(clientId);
        }

        String serverUrl="tcp://localhost:1883";
        String username="";
        String passwd="";

        try{
            MqttConnectOptions options = new MqttConnectOptions();
            // 设置连接的用户名
            //options.setUserName(username);
            // 设置连接的密码
            //options.setPassword(passwd.toCharArray());
            // 设置是否清空session
            // 设置为false表示服务器会保留客户端的连接记录
            // 设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(false);
            // 设置会话心跳时间
            options.setAutomaticReconnect(true);
            // 设置超时时间,单位为秒
            options.setConnectionTimeout(10);
            // 设置会话心跳时间,单位为秒
            // 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);

            // host:服务连接地址
            // clientIId:连接MQTT的客户端ID，一般以唯一标识符表示
            // MemoryPersistence:clientId的保存形式，默认为以内存保存
            MqttClient client = new MqttClient(serverUrl, clientId, new MemoryPersistence());
//            // 设置回调
//            MyMqttCallback callback = new MyMqttCallback();
//            client.setCallback(callback);
            // 建立连接
            client.connect(options);
            // 按clientId保存
            clientMap.put(clientId, client);
            return  client;
        }catch (MqttException e){
            e.printStackTrace();
        }
        return null;
    }

    static class MyMqttCallback implements MqttCallbackExtended {
        @Override
        public void connectComplete(boolean b, String s) {
            System.out.println("连接成功");
        }

        @Override
        public void connectionLost(Throwable throwable) {
            System.out.println("连接断开:" + throwable.getMessage());
        }

        @Override
        public void messageArrived(String topic, MqttMessage message) {
            System.out.println("接收消息主题:" + topic);
            System.out.println("接收消息Qos:" + message.getQos());
            System.out.println("接收消息内容:" + new String(message.getPayload()));
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            System.out.println("发送成功:" + iMqttDeliveryToken.isComplete());
        }
    }
}
