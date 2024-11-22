package level2.exercise1;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

public class ListDirectory {
    public static void main(String[] args) {
        Properties config = new Properties();
        String configPath = "src" + File.separator + "resources" + File.separator + "config.properties";

        try (InputStream input = new FileInputStream(configPath)) {
            config.load(input);
        } catch (IOException ex) {
            System.out.println("Error loading the configuration file: " + configPath);
            System.out.println("Error details: " + ex.getMessage());
            return;
        }

        String directoryPath = config.getProperty("directory");
        String outputFilePath = config.getProperty("outputFile");

        if (directoryPath == null || outputFilePath == null) {
            System.out.println("The configuration file does not contain the necessary parameters.");
            return;
        }

        File directory = new File(directoryPath);
        File outputFile = new File(outputFilePath);

        File outputDir = outputFile.getParentFile();
        if (!outputDir.exists()) {
            boolean created = outputDir.mkdirs();
            if (created) {
                System.out.println("Created output directory: " + outputDir.getPath());
            } else {
                System.out.println("Failed to create output directory: " + outputDir.getPath());
                return;
            }
        }

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("The specified path is not a valid directory.");
            return;
        }

        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write("Contents of the directory tree for '" + directory.getPath() + "':\n");
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
            String modificationDate = dateFormat.format(new Date(element.lastModified()));
            writer.write(" ".repeat(level * 2) + "- [" + type + "] " + element.getName() + " (Last modified: " + modificationDate + ")\n");

            if (element.isDirectory()) {
                listTree(element, level + 1, writer);
            }
        }
    }
}

