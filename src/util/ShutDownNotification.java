package util;

import static debug.DebugSettings.debug;

import java.util.Timer;
import java.util.TimerTask;

import network.NetStatic;

public class ShutDownNotification extends TimerTask{


	  private Timer timer;
	  public static int ONE_HOURS = 3600;
	  public static int TWO_HOURS = 7200;
	  private String Host;
	  
	  public String ActiveActivity;
	  
	  public ShutDownNotification(int seconds,String host){
		  debug("New ShutDown Notification()!");
		  timer = new Timer ();
		  timer.schedule (this,seconds*1000);
		  Host = host;
	  }//Notification

	@Override
	public void run(){
		NetStatic.Connections.remove(Host);
		debug(Host + " removed by Notification!");
	}//run
	
	public void ReSchedule(int seconds){
		  timer = new Timer ();	//point to a new timer
		  timer.schedule (this,seconds*1000);//schedule the event in the given time
	}//Schedule
	
	public String GetHost(){return Host;}
	
	public void Stop(){
		timer.cancel();
		debug("Stopped "+Host);
	}//Stop		
}
