package level1.exercise5;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ListDirectory {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java ListDirectory <directory> <output_file.txt> <serialized_file.ser>");
            return;
        }

        String inputPath = args[0].replace("/", File.separator).replace("\\", File.separator);
        String outputPath = args[1].replace("/", File.separator).replace("\\", File.separator);
        String serializedPath = args[2].replace("/", File.separator).replace("\\", File.separator);

        File directory = new File(inputPath);
        File outputFile = new File(outputPath);
        File serializedFile = new File(serializedPath);

        if (directory.exists() && directory.isDirectory()) {
            List<DirectoryInfo> directoryTreeContent = listTree(directory, 0);
            try (FileWriter writer = new FileWriter(outputFile)) {
                writer.write("Contents of the directory tree of '" + directory.getPath() + "':\n");
                for (DirectoryInfo info : directoryTreeContent) {
                    writer.write(info + "\n");
                }
                System.out.println("Result saved to: " + outputFile.getPath());
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the output file.");
                e.printStackTrace();
            }

            serializeObject(directoryTreeContent, serializedFile);
            System.out.println("Object serialized to: " + serializedFile.getPath());

            List<DirectoryInfo> deserialized = deserializeObject(serializedFile);
            System.out.println("Deserialized object:");
            for (DirectoryInfo info : deserialized) {
                System.out.println(info);
            }
        } else {
            System.out.println("The specified path is not a valid directory.");
        }
    }

    private static List<DirectoryInfo> listTree(File directory, int level) {
        List<DirectoryInfo> directoryTreeContent = new ArrayList<>();
        File[] contents = directory.listFiles();

        if (contents != null) {
            Arrays.sort(contents);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for (File element : contents) {
                String type = element.isDirectory() ? "D" : "F";
                String modificationDate = dateFormat.format(new Date(element.lastModified()));
                DirectoryInfo info = new DirectoryInfo(" ".repeat(level * 2) + "- [" + type + "] " + element.getName(), modificationDate);
                directoryTreeContent.add(info);

                if (element.isDirectory()) {
                    directoryTreeContent.addAll(listTree(element, level + 1));
                }
            }
        }
        return directoryTreeContent;
    }

    private static void serializeObject(List<DirectoryInfo> content, File serializedFile) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serializedFile))) {
            oos.writeObject(content);
        } catch (IOException e) {
            System.out.println("Error serializing the object.");
            e.printStackTrace();
        }
    }

    private static List<DirectoryInfo> deserializeObject(File serializedFile) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serializedFile))) {
            return (List<DirectoryInfo>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error deserializing the object.");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}

