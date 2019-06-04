package com.cambeeler;

import java.io.IOException;
import java.net.*;

public class Main {

    public static void main(String[] args) {
        try
        {
            DatagramSocket socket = new DatagramSocket(5000);

            String echo;
            while(true)
            {
                byte[] buffer = new byte[50];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String test = new String(buffer, 0, packet.getLength());
                System.out.println("Text Received: " + test);
                if(test.equalsIgnoreCase("exit")){
                    break;
                }
                echo = "echo: " + test;
                byte[] buffer2 = echo.getBytes();
                InetAddress inetAddress = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buffer2, buffer2.length, inetAddress, port);
                socket.send(packet);

            }
        }
        catch(SocketTimeoutException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException f)
        {
            System.out.println(f.getMessage());
        }
    }
}
