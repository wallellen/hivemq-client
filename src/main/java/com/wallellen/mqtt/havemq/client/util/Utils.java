package com.wallellen.mqtt.havemq.client.util;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Random;

/**
 * Created by <a href="mailto:wallellen@hotmail.com">WALLE</a> on 4/14/16.
 */
public class Utils {
    private static final Random random = new Random();

    public static String getMacAddress() {
        String result = "";

        try {
            for (NetworkInterface ni : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                byte[] address = ni.getHardwareAddress();

                if (address != null) {
                    for (int i = 0; i < address.length; i++) {
                        result += String.format((i == 0 ? "" : "-") + "%02X", address[i]);
                    }

                    return result;
                }
            }
        } catch (SocketException ex) {
            System.err.println("");
            ex.printStackTrace();
            System.exit(-1);
        }

        return result;
    }

    public static int createRandomNumberBetween(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}
