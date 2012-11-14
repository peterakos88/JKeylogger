package keylog;

import io.File;
import static debug.DebugSettings.debug;

public class KeylogReader {

	private File LogFile;
	
	public KeylogReader(String filename){
		
		LogFile = new File(filename);
		
		String content = LogFile.Read();
		
		debug(content);
		
	}//KeyLogReader
	
	
}//KeyLogReader