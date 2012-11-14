package util;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.jna.*;
import com.sun.jna.win32.*;
import static debug.DebugSettings.debug;

/**
 * Utility method to retrieve the idle time on Windows and sample code to test it.
 * JNA shall be present in your classpath for this to work (and compile).
 * @author ochafik
 */
public class Win32IdleTime {

    public interface Kernel32 extends StdCallLibrary {
        Kernel32 INSTANCE = (Kernel32)Native.loadLibrary("kernel32", Kernel32.class);

        /**
         * Retrieves the number of milliseconds that have elapsed since the system was started.
         * @see http://msdn2.microsoft.com/en-us/library/ms724408.aspx
         * @return number of milliseconds that have elapsed since the system was started.
         */
        public int GetTickCount();
    };

    public interface User32 extends StdCallLibrary {
        User32 INSTANCE = (User32)Native.loadLibrary("user32", User32.class);

        /**
         * Contains the time of the last input.
         * @see http://msdn.microsoft.com/library/default.asp?url=/library/en-us/winui/winui/windowsuserinterface/userinput/keyboardinput/keyboardinputreference/keyboardinputstructures/lastinputinfo.asp
         */
        public static class LASTINPUTINFO extends Structure {
            public int cbSize = 8;

            /// Tick count of when the last input event was received.
            public int dwTime;
        }

        /**
         * Retrieves the time of the last input event.
         * @see http://msdn.microsoft.com/library/default.asp?url=/library/en-us/winui/winui/windowsuserinterface/userinput/keyboardinput/keyboardinputreference/keyboardinputfunctions/getlastinputinfo.asp
         * @return time of the last input event, in milliseconds
         */
        public boolean GetLastInputInfo(LASTINPUTINFO result);
    };

    /**
     * Get the amount of milliseconds that have elapsed since the last input event
     * (mouse or keyboard)
     * @return idle time in milliseconds
     */
    public static int getIdleTimeMillisWin32() {
        User32.LASTINPUTINFO lastInputInfo = new User32.LASTINPUTINFO();
        User32.INSTANCE.GetLastInputInfo(lastInputInfo);
        return Kernel32.INSTANCE.GetTickCount() - lastInputInfo.dwTime;
    }

    enum State {
        UNKNOWN, ONLINE, IDLE, AWAY
    };

    public static String GetIdle() {
        if (!System.getProperty("os.name").contains("Windows")) {
            debug("ERROR: Only implemented on Windows");
            return null;
        }
        State state = State.UNKNOWN;
        DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");

        for (;;) {
            int idleSec = getIdleTimeMillisWin32() / 1000;

            State newState =
                idleSec < 30 ? State.ONLINE :
                idleSec > 5 * 60 ? State.AWAY : State.IDLE;

            if (newState != state) {
                state = newState;
                debug(dateFormat.format(new Date()) + " # " + state);
                return state.name();
            }
            try { Thread.sleep(1000); } catch (Exception ex) {}
        }
    }
    
    public static String STATE_ONLINE = "ONLINE";
    public static String STATE_AWAY = "AWAY";
    public static String STATE_IDLE = "IDLE";
    public static String STATE_UNKNOWN = "UNKNOWN";


}