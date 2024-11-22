package level1.exercise4;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ListDirectory {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java level1.exercise3.ListDirectory \"path/to/input/directory\" \"path/to/output/file.txt\"");
            return;
        }

        String inputPath = args[0].replace("/", File.separator).replace("\\", File.separator);
        String outputPath = args[1].replace("/", File.separator).replace("\\", File.separator);

        File directory = new File(inputPath);
        File outputFile = new File(outputPath);

        if (outputFile.exists() && outputFile.isFile() && outputFile.getName().endsWith(".txt")) {
            readAndDisplayFile(outputFile);
        } else if (directory.exists() && directory.isDirectory()) {
            try (FileWriter writer = new FileWriter(outputFile)) {
                writer.write("Contents of the directory tree of '" + directory.getPath() + "':\n");
                listTree(directory, 0, writer);
                System.out.println("Result saved to: " + outputFile.getPath());
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the output file.");
                e.getMessage();
            }
        } else {
            System.out.println("The specified path is not a valid directory or an existing TXT file.");
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
            String modificationDate = dateFormat.format(new Date(element.lastModified()));
            writer.write(" ".repeat(level * 2) + "- [" + type + "] " + element.getName() + " (Last modification: " + modificationDate + ")\n");

            if (element.isDirectory()) {
                listTree(element, level + 1, writer);
            }
        }
    }

    private static void readAndDisplayFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.getMessage();
        }
    }
}
