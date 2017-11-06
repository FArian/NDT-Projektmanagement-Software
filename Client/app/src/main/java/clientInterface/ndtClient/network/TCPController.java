package rzeznika.ndtClient.network;
import android.util.Log;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;


/**
 * Created by F.Arian on 22.06.17.
 */

public class TCPController {

    /**
     * create new Websocket "ws://IP:port= :9000/link "address with the new IP Automatically
     *
     * @return
     */
    public static String webAddress(String serverIp) {
        String wsPlace = "ws://";
        String myHostIp = serverIp;
        String port = ":9000/";
        String link = "gamesocket";
        StringBuffer sbResult = new StringBuffer("");
        sbResult.setLength(wsPlace.length() + myHostIp.length() + port.length() + link.length());
        for (int i = 0; i < wsPlace.length(); i++) {
            sbResult.replace(0, wsPlace.length(), "ws://");
        }
        for (int i = wsPlace.length(); i < wsPlace.length() + myHostIp.length(); i++) {
            sbResult.replace(wsPlace.length(), wsPlace.length() + myHostIp.length(), myHostIp);
        }
        for (int i = wsPlace.length() + myHostIp.length(); i < wsPlace.length() + myHostIp.length()
                + port.length(); i++) {
            sbResult.replace(wsPlace.length() + myHostIp.length(), wsPlace.length() + myHostIp.length() + port.length(),
                    port);
        }
        for (int i = wsPlace.length() + myHostIp.length() + port.length(); i < wsPlace.length() + myHostIp.length()
                + port.length() + link.length(); i++) {
            sbResult.replace(wsPlace.length() + myHostIp.length() + port.length(),
                    wsPlace.length() + myHostIp.length() + port.length() + link.length(), link);
        }
        return sbResult.toString();
    }


    public String getCurrentIp() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("SRM", ex.toString());
        }
        return null;
    }

    public static String host() {
        return System.getProperty("host", "localhost");
    }
    /**
     * Gets the textual representation of the local host.
     *
     * @return the textual representation of the local host.
     */
    private String getLocalHost() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        }
        catch (Exception e) {
            return "127.0.0.1";
        }
    }


}
