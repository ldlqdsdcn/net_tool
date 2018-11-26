package indi.liudalei.net_tool;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

/**
 * Created by Administrator on 2018/7/20.
 */
public class DaytimeUDPServer {
    private final static int PORT = 30301;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            while (true) {
                try {
                    DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
                    socket.receive(request);

                    String daytime = "server current date:"+new Date().toString();
                    byte[] data = daytime.getBytes("ASCII");
                    DatagramPacket response = new DatagramPacket(data, data.length, request.getAddress(), request.getPort());
                    socket.send(response);
                    String data_=new String(request.getData(),"ASCII");
                    System.out.println("收到数据："+data_);
                    System.out.println(daytime + " " + request.getAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
