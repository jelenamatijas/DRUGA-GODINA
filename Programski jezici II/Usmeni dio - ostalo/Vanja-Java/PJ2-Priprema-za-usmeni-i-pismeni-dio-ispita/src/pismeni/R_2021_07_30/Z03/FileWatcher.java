package pismeni.R_2021_07_30.Z03;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public class FileWatcher extends Thread {
    public int cnt = 0;

    public FileWatcher() {
        setDaemon(true);
    }

    @Override
    public void run() {
        try {
            WatchService service = FileSystems.getDefault().newWatchService();
            Path dir = Paths.get(Main.PATH);
            dir.register(service, ENTRY_CREATE);
            while (true) {
                WatchKey key = null;
                try {
                    key = service.take();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    if (kind.equals(ENTRY_CREATE))
                        cnt++;
                }

                boolean valid = key.reset();
                if (!valid)
                    break;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}