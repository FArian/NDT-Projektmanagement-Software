package actors;

import actors.serverInterface.json.TOJSON;
import org.apache.http.conn.util.InetAddressUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;

/**
 * Created by F.Arian on 06.11.17.
 */
public class LocalHostAddress {

    public static String getLocalIpAddress() {
        try {

            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                     enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    String ipv4;
                    // for getting IPV4 format
                    if (!inetAddress.isLoopbackAddress() && InetAddressUtils.isIPv4Address(ipv4 = inetAddress.getHostAddress())) {
                        //System.out.println(ipv4);
                        String ip = inetAddress.getHostAddress().toString();
                        return ip;
                    }
                }
            }
        } catch (Exception ex) {

            //Logger.getLogger("IP Address", ex.toString());
        }
        return null;
    }

    public void connectionInfo() {
        try {
            ServerSocket serverSocket = new ServerSocket();
            Socket socket = serverSocket.accept();
            startHandler(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startHandler(final Socket socket) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                    String hostIp = getLocalIpAddress();
                    writer.write(String.valueOf(TOJSON.sendLocalHostAdress(hostIp)));
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeSocket();

                }

            }

            private void closeSocket() {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

    }
}
