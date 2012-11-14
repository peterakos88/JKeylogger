package network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import util.ShutDownNotification;

public class NetStatic {

	//Net static is a wrapper for static elements of websites
	
	public static String GoogleTypingRecogntion = "clients1.google.gr";
	
	public static ArrayList<String > Connections = new ArrayList<String>();
	public static Map<String,Object> Times = new HashMap<String, Object>();
	
	public static ArrayList<ShutDownNotification> SD = new ArrayList<ShutDownNotification>();

}//NetStatic