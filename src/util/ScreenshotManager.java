package util;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import static debug.DebugSettings.debug;

import javax.imageio.ImageIO;

public class ScreenshotManager {

	public ScreenshotManager(){
		
	}
	
	public static void CaptureScreen(){
		 try {
		        Robot robot = new Robot();
		        Rectangle captureSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		        BufferedImage bufferedImage = robot.createScreenCapture(captureSize);
		        debug(io.File.FindDateImageName());
		        

		        String dateDir = (io.File.FindDateImageName().substring(0,10));

		        
		        File dir = new File("screen\\"+dateDir);
		        
		        dir.mkdirs();
		        
		        
		        File outputfile = new File(dir.getAbsolutePath()+"\\"+io.File.FindDateImageName()+".png");
		        ImageIO.write(bufferedImage, "png", outputfile);		        
		        
		        
		 	}//try
		    catch(Exception e) {
		    	e.printStackTrace();
		    }//catch		
	}//CaptureScreen
	

	public static File GetCaptureScreenFile(){
		 try {
		        Robot robot = new Robot();
		        Rectangle captureSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		        BufferedImage bufferedImage = robot.createScreenCapture(captureSize);
		        debug(io.File.FindDateImageName());
		        
		        File dir = new File("screen");
		        dir.mkdir();
		        
		        File outputfile = new File(dir.getAbsolutePath()+"\\"+io.File.FindDateImageName()+".png");
		        ImageIO.write(bufferedImage, "png", outputfile);		        
		        
		        return outputfile;
		 	}//try
		    catch(Exception e) {
		    	e.printStackTrace();
		    }//catch		
		return null;
	}//CaptureScreen
	
}//ScreenshotManager