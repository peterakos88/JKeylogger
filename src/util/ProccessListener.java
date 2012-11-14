package util;

import java.util.EventListener;

public interface ProccessListener extends EventListener {
    void processFinished(Process process);
    
}