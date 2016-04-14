package com.wallellen.mqtt.havemq.client.publisher;

import com.wallellen.mqtt.havemq.client.util.Utils;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/**
 * Created by <a href="mailto:wallellen@hotmail.com">WALLE</a> on 4/14/16.
 */
public class Publisher {
    public static final String BROKER_URL = "tcp://localhost:1883";//"tcp://broker.mqttdashboard.com:1883";
    public static final String TOPIC_BRIGHTNESS = "home/brightness";
    public static final String TOPIC_TEMPERATURE = "home/temperature";

    private MqttClient client;

    public Publisher() {
        String clientId = Utils.getMacAddress() + "-pub";

        try {
            client = new MqttClient(BROKER_URL, clientId);
        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }


    }

    public void start() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setWill(client.getTopic("home/LWT"), "I'm gone".getBytes(), 2, true);

        try {
            client.connect(options);

            while (true) {
                printBrightness();
                Thread.sleep(500);
                printTemperature();
                Thread.sleep(500);
            }

        } catch (MqttException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printTemperature() throws MqttException {
        final MqttTopic topic = client.getTopic(TOPIC_TEMPERATURE);
        final int temperature = Utils.createRandomNumberBetween(20, 30);
        final String temp = temperature + " C";

        topic.publish(new MqttMessage(temp.getBytes()));


        System.err.println("Publish data. Topic: " + topic.getName() + " Message: " + temp);
    }

    private void printBrightness() throws MqttException {
        final MqttTopic topic = client.getTopic(TOPIC_BRIGHTNESS);
        final int bright = Utils.createRandomNumberBetween(0, 100);
        final String temp = bright + " %";

        topic.publish(new MqttMessage(temp.getBytes()));

        System.err.println("Publish data. Topic: " + topic.getName() + " Message: " + temp);
    }

    public static void main(String[] args) {
        new Publisher().start();
    }
}
