package io;

public class LogFile extends File{

	public LogFile(String filename){
		super(filename);
	}//LogFile
	
	public LogFile(){		
		super(LogFile.FindName(false));
	}//LogFile	with name the today's date
	
	
}//LogFile