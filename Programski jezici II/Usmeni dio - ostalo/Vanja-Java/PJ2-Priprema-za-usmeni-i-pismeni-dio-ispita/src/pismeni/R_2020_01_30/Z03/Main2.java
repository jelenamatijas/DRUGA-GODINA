package pismeni.R_2020_01_30.Z03;
import java.io.*;
import java.util.*;
import java.nio.file.*;

public class Main2{

    public static ArrayList<File> files = new ArrayList<>();
    public static ArrayList<Path> fajls = new ArrayList<>();

    public static void main(String[] args){
        try{
            for(int i = 0; i< args.length; i++){
                switch(args[i]){
                    case "--starts-with":
                        startsWith(args[++i]);
                        break;
                    case "--ends-with":
                        endsWith(args[++i]);
                        break;
                    case "--sort asc":
                        sortAsc(true);
                        break;
                    case "--sort desc":
                        sortAsc(false);
                        break;
                    case ">>":
                        printToFile(args[++i]);
                        break;
                    default: System.out.println("nepoznata opcija");
                }
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void startsWith(String pattern){
        System.out.println("STARTS WITH " + pattern + " working directory " +  System.getProperty("user.dir"));
        File[] files = new File(System.getProperty("user.dir")).listFiles();
        for(int i = 0; i<files.length; i++){
            String fileName = files[i].getName();
            if(fileName.startsWith(pattern))
                System.out.println(fileName);
        }
    }

    public static void endsWith(String pattern){
        System.out.println("ENDS WITH " + pattern + " working directory " +  System.getProperty("user.dir"));
        File[] files = new File(System.getProperty("user.dir")).listFiles();
        for(int i = 0; i<files.length; i++){
            String fileName = files[i].getName();
            if(fileName.endsWith(pattern))
                System.out.println(fileName);
        }
    }

    public static void sortAsc(boolean asc) throws IOException{
        File[] files = new File(System.getProperty("user.dir")).listFiles();
        for(int i = 0; i<files.length; i++){
            fajls.add(files[i].toPath());
        }
        if(asc){
            System.out.println("SORT ASC");
            fajls.sort((f1,f2) -> {
                try{
                    return Long.compare(Files.size(f2), Files.size(f1));
                }catch(IOException e) {
                    e.printStackTrace();
                }
                return 0;
            });
        }
        else {
            System.out.println("SORT DESC");
            fajls.sort((f1,f2) -> {
                try{
                    return Long.compare(Files.size(f1), Files.size(f2));
                }catch(IOException e) {
                    e.printStackTrace();
                }
                return 0;
            });
        }
        fajls.stream().forEach(f -> {
            try{
                System.out.println(f.getFileName() + " size: " + Files.size(f));
            }catch(IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void printToFile(String fileName){
        System.out.println("PRINT TO FILE " + fileName);
    }
}