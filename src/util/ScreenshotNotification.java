package util;

import java.util.Timer;
import java.util.TimerTask;
import static debug.DebugSettings.debug;

public class ScreenshotNotification extends TimerTask{
	
	  private Timer timer;
	  public static int ONE_HOURS = 3600;
	  public static int TWO_HOURS = 7200;
	  
	  private int Seconds;
	  
	  public String ActiveActivity;
	  
	  public ScreenshotNotification(int seconds){
		  debug("New Notification()!");
		  timer = new Timer ();
		  timer.schedule (this,seconds*1000);
		  Seconds = seconds;
	  }//Notification

	@Override
	public void run(){
		
		if (!Win32IdleTime.GetIdle().equals(Win32IdleTime.STATE_IDLE)){
			new Thread(){
				public void run(){
					ScreenshotManager.CaptureScreen();
					new ScreenshotNotification(Seconds);
				}
			}.start();
			
		}//if not idle take a pic
		//timer.cancel();
		//timer = new Timer();
		//timer.schedule(this,Seconds*1000);
		
		//@SuppressWarnings("unused")
		//ScreenshotNotification sn = new ScreenshotNotification(Seconds);
	}//run
	
	public void ReSchedule(int seconds){
		  timer = new Timer ();	//point to a new timer
		  timer.schedule (this,seconds*1000);//schedule the event in the given time
	}//Schedule
	
	public void Stop(){
		timer.cancel();
		debug("ScreenshotNotification: Stopped Screenshot Notification");
	}//Stop

	
}//Notification