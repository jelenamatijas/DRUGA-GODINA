package parser;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Parser<T> extends Thread {

    private String path;
    private HashMap<String, T> data = new HashMap<>();
    public final int SLEEP_TIME = 1000;
    private boolean wait = false;
    private Object lock;

    public Parser() {
    }

    public void checkWait() {

        if (isWait()) {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * @param lock the lock to set
     */
    public void setLock(Object lock) {
        this.lock = lock;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @return the data
     */
    public HashMap<String, T> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(HashMap<String, T> data) {
        this.data = data;
    }

    /**
     * @return the data
     */
    public ArrayList<T> getDataList() {
        return new ArrayList<>(data.values());
    }

    /**
     * @return the wait
     */
    public boolean isWait() {
        return wait;
    }

    /**
     * @param wait the wait to set
     */
    public void setWait(boolean wait) {
        this.wait = wait;
    }

}