package main;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import parser.Parser;

public class ConsoleThread extends Thread {

    private ArrayList<Parser<?>> parsers = new ArrayList<>();
    private boolean isActive = true;
    private Scanner input = new Scanner(System.in);

    public static Object lock = new Object();

    public ConsoleThread(Parser<?>... allParsers) {
        for (Parser<?> p : allParsers) {
            parsers.add(p);
            p.setLock(lock);
        }
    }

    @Override
    public void run() {
        while (isActive) {
            String option = input.nextLine();

            if (Constants.KEYBOARD_WAIT.equals(option)) {
                parsers.forEach(p -> p.setWait(true));
            } else if (Constants.KEYBOARD_GO.equals(option)) {
                parsers.forEach(p -> {
                    p.setWait(false);
                });
                synchronized (lock) {
                    lock.notifyAll();
                }
            } else if (Constants.KEYBOARD_END.equals(option)) {
                this.setActive(false);
            }
        }

        input.close();

    }

    /**
     * @return the isActive
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
        if (!this.isActive) {
            try {
                // prekida unos u konzolu
                Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}