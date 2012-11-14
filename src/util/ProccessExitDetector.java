package util;

import java.util.ArrayList;
import java.util.List;

public class ProccessExitDetector extends Thread{

    /** The process for which we have to detect the end. */
    private Process process;
    /** The associated listeners to be invoked at the end of the process. */
    private List<util.ProccessListener> listeners = new ArrayList<util.ProccessListener>();

    /**
     * Starts the detection for the given process
     * @param process the process for which we have to detect when it is finished
     */
    public ProccessExitDetector(Process process) {
        try {
            // test if the process is finished
            process.exitValue();
            throw new IllegalArgumentException("The process is already ended");
        } catch (IllegalThreadStateException exc) {
            this.process = process;
        }
    }

    /** @return the process that it is watched by this detector. */
    public Process getProcess() {
        return process;
    }

    public void run() {
        try {
            // wait for the process to finish
            process.waitFor();
            // invokes the listeners
            for (ProccessListener listener : listeners) {
                listener.processFinished(process);
            }
        } catch (InterruptedException e) {
        }
    }

    /** Adds a process listener.
     * @param listener the listener to be added
     */
    public void addProcessListener(ProccessListener listener) {
        listeners.add(listener);
    }

    /** Removes a process listener.
     * @param listener the listener to be removed
     */
    public void removeProcessListener(ProccessListener listener) {
        listeners.remove(listener);
    }
}