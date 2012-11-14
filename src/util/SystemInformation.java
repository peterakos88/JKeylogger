package util;

public class SystemInformation {

	public static String GetOS(){
		return (System.getProperty("os.name"));
	}//GetOS
	
	public static String GetOSArchitecture(){
		return (System.getProperty("os.arch"));
	}
	
	public static String GetOSVersion(){
		return (System.getProperty("os.version"));
	}	
	
	public static String GetDefaultTimezone(){
		return (System.getProperty("os.timezone"));
	}	
	
	public static String GetRegion(){
		return (System.getProperty("user.region"));
	}		
	
	public static String GetUsername(){
		return (System.getProperty("user.name"));
	}			
	
	public static String GetDefaultLanguage(){
		return (System.getProperty("user.language"));
	}	
	
	public static String GetUserHomeDirectory(){
		return (System.getProperty("user.home"));
	}		
	
	//-----------------------------------------------------------
	
}//System Properties