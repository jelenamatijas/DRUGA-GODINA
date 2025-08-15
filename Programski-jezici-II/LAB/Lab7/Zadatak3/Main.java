import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    private static File currentPath;
    public static void changeDir(String name){
        if(name.equals("..")){
            if(currentPath.getParentFile() != null){
                currentPath = currentPath.getParentFile();
            }else {
                File f = new File(currentPath + File.separator + name);
                currentPath = f.exists() ? f : currentPath;
            }
        }
    }

    public static void listFiles(){
        if(currentPath.isDirectory()){
            File[] files = currentPath.listFiles();
            for(File f : files){
                if(f.isDirectory() && !f.isHidden()){
                    System.out.println("<DIR> " + f.getName());
                }else if(f.isFile() && !f.isHidden()){
                    System.out.println(f.getName());
                }
            }
        }
    }

    public static void createDirectory(String name) {
        File f = new File(currentPath + File.separator + name);
        if(!f.exists()){
            f.mkdir();
            System.out.println("Direktorijum je kreiran!");
        }else{
            System.out.println("Direktorijum vec postoji!");
        }
    }

    public static void delete(String name){
        File f = new File(currentPath + File.separator + name);
        if(f.exists()){
            f.delete();
            System.out.println("Fajl je obrisan.");
        }else{
            System.out.println("Fajl ne postoji.");
        }
    }

    public static void copyImageFile(String sourceName, String destinationName) throws IOException{
        if(sourceName.endsWith("jpg") && sourceName.endsWith("jpg") && !sourceName.equals(destinationName)){
            File src = new File(currentPath + File.separator + sourceName);
            File dest = new File(currentPath + File.separator + destinationName);
            if(!src.exists()){
                System.out.println("Fajl " + sourceName + " ne postoji!");
                return;
            }

            FileInputStream in = null;
            FileOutputStream out = null;
            try{
                in = new FileInputStream(src);
                out = new FileOutputStream(dest);
                int c;

                while((c = in.read(null)) != -1 ){
                    out.write(c);
                }
            }finally{
                if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			}
        }else{
            System.out.println("Nazivi fajlova nisu korektni.");
        }
    }

    public static void listWordDoc(){
        File[] files = currentPath.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File file, String name){
                return (name.endsWith("doc") || name.endsWith("docx"));
            }
        });

        for (File f : files) {
         System.out.println(f.getName());   
        }
    }

    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        currentPath = new File(System.getProperty("user.home"));

        boolean firstRun = true;
        String option = "";
        do{
            if(option.startsWith("md")){
                String name = option.split(" ")[1];
                createDirectory(name);
            }else if(option.equals("dir")){
                listFiles();
            }else if(option.startsWith("cd")){
                String path = option.split(" ")[1];
                changeDir(path);
            }else if(option.startsWith("del")){
                String path = option.split(" ")[1];
                delete(path);
            }else if(option.startsWith("copy")){
                String paths[] = option.split(" ");
                try{
                    copyImageFile(paths[1], paths[2]);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else if(option.equals("word")){
                listWordDoc();
            }else{
                if(!firstRun){
                    System.out.println("Opcija ne postoji.");
                }else{
                     firstRun = false;
                }
            }
            System.out.println("\nOpcije:\n" + "md name == kreiranje foldera sa proslijedjenim imenom\n"
					+ "dir == prikaz sadrzaja direktorijuma\n" + "cd name == otvaranje drugog direktorijuma\n"
					+ "del name == brisanje fajla ili direktorijuma\n"
					+ "copy source destination == kopiranje jpg slika u istom folderu\n"
					+ "word == prikaz svih word datoteka\n" + "quit == izlaz\n");
			System.out.print(">> " + currentPath + " ");
        }while(!"quit".equals(option = scanner.nextLine()));
        scanner.close();
    }
}
