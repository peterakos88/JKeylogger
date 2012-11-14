package network;
import io.LogFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import util.ShutDownNotification;

import jpcap.*;
import jpcap.packet.Packet;
import keylog.Main;

public class PacketSniffer implements PacketReceiver {
	
	private static boolean DEBUG_MODE = true;
	

	public static void Main() throws IOException{
		
		PacketSniffer ps = new PacketSniffer();
		NetworkInterface[] devices = ps.GetNetworkInterfaces();
		
		JpcapCaptor captor=JpcapCaptor.openDevice(devices[1], 65535, false, 20);
		captor.setFilter("ip and tcp", true);
		
		//call processPacket() to let Jpcap call PacketPrinter.receivePacket() for every packet capture.
		
		while(true){
			captor.processPacket(10,new PacketSniffer());
		}
		//captor.close();
		
	}//main
	
	public PacketSniffer(){	
	}
	
	public NetworkInterface[] GetNetworkInterfaces(){
		//Obtain the list of network interfaces
		NetworkInterface[] devices = JpcapCaptor.getDeviceList();

		//for each network interface
		for (int i = 0; i < devices.length; i++) {
		  //print out its name and description
		  debug(i+": "+devices[i].name + "(" + devices[i].description+")");		  
		  //print out its datalink name and description
		  debug(" datalink: "+devices[i].datalink_name + "(" + devices[i].datalink_description+")");
		  //print out its MAC address
		  debug(" MAC address:");
		  for (byte b : devices[i].mac_address)
		    debug(Integer.toHexString(b&0xff) + ":");
		  debug("");	
		  //print out its IP address, subnet mask and broadcast address
		  for (NetworkInterfaceAddress a : devices[i].addresses)
		    debug(" address:"+a.address + " " + a.subnet + " "+ a.broadcast);
		}//for		
	    debug("");
		return devices;
	}//GetNetworkInterface

	@Override
	public void receivePacket(Packet arg0) {
		String content = ((new String(arg0.data)));
		
		String connect = ReadConnection(content);	//keep alive or close
		String host= ReadHost(content);
		 ShutDownNotification sd = null;
			if (connect!=null){
				if (connect.trim().equalsIgnoreCase("keep-alive")){
					if ( !NetStatic.Connections.contains(host) && (host!=null) ){
						
						NetStatic.Connections.add(host);
						debug("Live Connections: "+NetStatic.Connections.size());
						
						sd = new ShutDownNotification(180,host);
						
						NetStatic.SD.add(sd);
						NetStatic.Times.put(host, arg0.sec*1000);
						
						debug(Main.GetKeyLogger().IsReady()+" is ready");
						if(Main.GetKeyLogger()!=null && Main.GetKeyLogger().IsReady()){
							LogFile lf = Main.GetKeyLogger().GetLogFile();
							debug("write connection to file()");
							lf.WriteLine("");
							lf.WriteLine("#"+host+"^"+(String.valueOf(arg0.sec*1000)));
						}

						SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
						
						for(int i=0;i<NetStatic.Connections.size();i++){
							debug(NetStatic.Connections.get(i)+" at "+ formatter.format( ((Long)NetStatic.Times.get( NetStatic.Connections.get(i))).longValue() ) );
						}

						
					}//if
					else{
						
							if (host!=null){
								int k = -1;
								debug("Stopped "+host+ "Notification");
								ShutDownNotification sn = null;
								for(int i=0;i<NetStatic.SD.size();i++){
									if (NetStatic.SD.get(i).GetHost().equals(host)){
										sn = NetStatic.SD.get(i);
										k=i;
										break;
									}//if
								}//for
								
								if (k!=-1){
									NetStatic.SD.get(k).Stop();
									NetStatic.SD.remove(sn);
									sd = new ShutDownNotification(60,host);
									NetStatic.SD.add(sd);
								}
							}//host!=null
					}//else
				}	
				else{
					NetStatic.Connections.remove(host);
					debug("Close connection with "+host);
				}//else
			}//connect not null
			

		
		if (host!=null){
			debug(host);
			SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
			debug(formatter.format(new Date(arg0.sec*1000)));
		}//if
		
	}//receivePacket
	
	
	
	private static String ReadHost(String content){
		if (content!=null){
			if (content.contains("Host")){
				int k =(content.indexOf("Host"));
				int l = k+50;
				while(l>content.length())
					l--;
				
				String h = (content.substring(k,l));
				return (new StringTokenizer(h,"\n").nextToken().trim());
			}//if		
		}//content!=null
		
		
		
		
		return null;
	}//ReadHost
	private static String ReadTitle(String content){
		if (content!=null){
			if (content.contains("<title>")){
				int k = content.indexOf("<title>");
				int l= content.indexOf("</title>");
				
				if (k<content.length() && l<content.length())
					return content.substring(k+7,l);
			}//if
			else if(content.contains("<TITLE>")) {
				int k = content.indexOf("<TITLE>");
				int l= content.indexOf("</TITLE>");
				
				if (k<content.length() && l<content.length())
					return content.substring(k+7,l);				
			}
		}//if
		return null;
	}//ReadTitle
	
	private static String ReadConnection(String content){
		String str;
		BufferedReader reader = new BufferedReader(new StringReader(content));
		try {
		  while ((str = reader.readLine()) != null) {
		          if (str.length() > 0){
		        	  if (str.contains("Connection:") || (str.contains("Connection"))){
		        		  int k = str.indexOf("Connection:");
		        		  return str.substring(k+11);
		        	  }//if
		          }//if
		  }//while
		}//try
		catch(IOException e) {e.printStackTrace();}	
		return null;
	}
	
	
	private static String ReadTitle2(String content){
		String str;
		BufferedReader reader = new BufferedReader(new StringReader(content));
		try {
		  while ((str = reader.readLine()) != null) {
		          if (str.length() > 0){
		        	  if (str.contains("<title>")){
		        		  //debug(str);
		        		  int k = str.indexOf("<title>");
		        		  int l = str.indexOf("</title>");
		        		  return str.substring(k+7,l);
		        	  }//if
		          }//if
		  }//while
		}//try
		catch(IOException e) {e.printStackTrace();}	
		return null;
	}//ReadTitle()
	

	public static void debug(Object text){
		if (DEBUG_MODE)
			System.out.println((String)text);
	}//debug
	
	
	
}//PacketSniffer