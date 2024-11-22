package level1.exercise2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ListDirectory {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java level1.exercise2.ListDirectory \"path/to/directory\"");
            return;
        }

        String inputPath = args[0].replace("/", File.separator).replace("\\", File.separator);
        File directory = new File(inputPath);

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("The specified path is not a valid directory.");
            return;
        }

        System.out.println("Contents of the directory tree of '" + directory.getPath() + "':");
        listTree(directory, 0);
    }

    private static void listTree(File directory, int level) {
        File[] contents = directory.listFiles();

        if (contents == null || contents.length == 0) {
            System.out.println(" ".repeat(level * 2) + "(Empty directory)");
            return;
        }

        Arrays.sort(contents);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (File element : contents) {
            String type = element.isDirectory() ? "D" : "F";
            String lastModified = dateFormat.format(new Date(element.lastModified()));
            System.out.println(" ".repeat(level * 2) + "- [" + type + "] " + element.getName() + " (Last modified: " + lastModified + ")");

            if (element.isDirectory()) {
                listTree(element, level + 1);
            }
        }
    }
}
