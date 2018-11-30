package webapp;

import java.net.InetAddress;

public class Utils {
	public static String getHostName(int port) throws Exception {
		InetAddress inetAddress = InetAddress.getLocalHost();
		return inetAddress.getHostAddress() + ":" + port;
	}
	
	public static String getHostName() throws Exception {
		InetAddress inetAddress = InetAddress.getLocalHost();
		return inetAddress.getHostAddress();
	}
	
	public static String getComputerName() throws Exception {
		return System.getenv("COMPUTERNAME");
	}
}