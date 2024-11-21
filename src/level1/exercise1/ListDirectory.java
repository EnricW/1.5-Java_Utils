package level1.exercise1;

import java.io.File;
import java.util.Arrays;

public class ListDirectory {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java ListDirectory <directory>");
            return;
        }

        String inputPath = args[0].replace("/", File.separator).replace("\\", File.separator);
        File directory = new File(inputPath);

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("The specified path is not a valid directory.");
            return;
        }

        String[] files = directory.list();

        if (files == null || files.length == 0) {
            System.out.println("The directory is empty.");
            return;
        }

        Arrays.sort(files);

        System.out.println("Contents of the directory '" + directory.getPath() + "' in alphabetical order:");
        for (String file : files) {
            System.out.println(file);
        }
    }
}