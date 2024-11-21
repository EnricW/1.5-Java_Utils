package level1.exercise3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ListDirectory {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java ListDirectory <directory> <output_file.txt>");
            return;
        }

        String inputPath = args[0].replace("/", File.separator).replace("\\", File.separator);
        String outputPath = args[1].replace("/", File.separator).replace("\\", File.separator);

        File directory = new File(inputPath);
        File outputFile = new File(outputPath);

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("The specified path is not a valid directory.");
            return;
        }

        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write("Contents of the directory tree of '" + directory.getPath() + "':\n");
            listTree(directory, 0, writer);
            System.out.println("Result saved to: " + outputFile.getPath());
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the output file.");
            e.getMessage();
        }
    }

    private static void listTree(File directory, int level, FileWriter writer) throws IOException {
        File[] contents = directory.listFiles();

        if (contents == null || contents.length == 0) {
            writer.write(" ".repeat(level * 2) + "(Empty directory)\n");
            return;
        }

        Arrays.sort(contents);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (File element : contents) {
            String type = element.isDirectory() ? "D" : "F";
            String lastModified = dateFormat.format(new Date(element.lastModified()));
            writer.write(" ".repeat(level * 2) + "- [" + type + "] " + element.getName() + " (Last modified: " + lastModified + ")\n");

            if (element.isDirectory()) {
                listTree(element, level + 1, writer);
            }
        }
    }
}
