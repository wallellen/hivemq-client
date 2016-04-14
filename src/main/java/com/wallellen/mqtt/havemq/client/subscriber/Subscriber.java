package com.wallellen.mqtt.havemq.client.subscriber;

import com.wallellen.mqtt.havemq.client.util.Utils;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * Created by <a href="mailto:wallellen@hotmail.com">WALLE</a> on 4/14/16.
 */
public class Subscriber {
    public static final String BROKER_URL = "tcp://localhost:1883";//"tcp://broker.mqttdashboard.com:1883";

    String clientId = Utils.getMacAddress() + "-sub";

    private MqttClient client;

    public Subscriber() {
        try {
            client = new MqttClient(BROKER_URL, clientId);

        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void start() {
        client.setCallback(new SubscribeCallback());
        try {
            client.connect();

            final String topic = "home/#";

            client.subscribe(topic);

            System.err.println("Subscribe is now listen to " + topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final Subscriber subscriber = new Subscriber();
        subscriber.start();
    }
}
