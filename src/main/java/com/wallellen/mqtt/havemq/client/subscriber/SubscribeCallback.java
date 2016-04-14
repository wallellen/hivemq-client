package com.wallellen.mqtt.havemq.client.subscriber;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Created by <a href="mailto:wallellen@hotmail.com">WALLE</a> on 4/14/16.
 */
public class SubscribeCallback implements MqttCallback {
    @Override
    public void connectionLost(Throwable cause) {
        cause.printStackTrace();
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.err.println("Message arrived. Topic: " + topic + " Message: " + message.toString());

        if("home/LWT".equals(topic)) {
            System.err.println("Senor gone!");
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}
