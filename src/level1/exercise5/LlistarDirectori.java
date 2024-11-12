package level1.exercise5;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class LlistarDirectori {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Ús: java LlistarDirectori <directori> <fitxer_sortida.txt> <fitxer_serialitzat.ser>");
            return;
        }

        File directori = new File(args[0]);
        File fitxerSortida = new File(args[1]);
        File fitxerSerialitzat = new File(args[2]);

        // Llistar i guardar contingut al fitxer TXT
        if (directori.exists() && directori.isDirectory()) {
            List<DirectoriInfo> contingutArbre = llistarArbre(directori, 0);
            try (FileWriter escriptor = new FileWriter(fitxerSortida)) {
                escriptor.write("Contingut de l'arbre de directoris de '" + directori.getPath() + "':\n");
                for (DirectoriInfo info : contingutArbre) {
                    escriptor.write(info + "\n");
                }
                System.out.println("Resultat guardat a: " + fitxerSortida.getPath());
            } catch (IOException e) {
                System.out.println("S'ha produït un error en escriure al fitxer de sortida.");
                e.printStackTrace();
            }

            // Serialitzar l'objecte
            serialitzarObjecte(contingutArbre, fitxerSerialitzat);
            System.out.println("Objecte serialitzat a: " + fitxerSerialitzat.getPath());

            // Desserialitzar i mostrar l'objecte
            List<DirectoriInfo> desserialitzat = desserialitzarObjecte(fitxerSerialitzat);
            System.out.println("Objecte desserialitzat:");
            for (DirectoriInfo info : desserialitzat) {
                System.out.println(info);
            }
        } else {
            System.out.println("El camí especificat no és un directori vàlid.");
        }
    }

    // Mètode per llistar el directori de forma recursiva
    private static List<DirectoriInfo> llistarArbre(File directori, int nivell) {
        List<DirectoriInfo> contingutArbre = new ArrayList<>();
        File[] contingut = directori.listFiles();

        if (contingut != null) {
            Arrays.sort(contingut);
            SimpleDateFormat formatData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for (File element : contingut) {
                String tipus = element.isDirectory() ? "D" : "F";
                String dataModificacio = formatData.format(new Date(element.lastModified()));
                DirectoriInfo info = new DirectoriInfo(" ".repeat(nivell * 2) + "- [" + tipus + "] " + element.getName(), dataModificacio);
                contingutArbre.add(info);

                // Si és un directori, crida el mètode recursivament
                if (element.isDirectory()) {
                    contingutArbre.addAll(llistarArbre(element, nivell + 1));
                }
            }
        }
        return contingutArbre;
    }

    // Mètode per serialitzar l'objecte
    private static void serialitzarObjecte(List<DirectoriInfo> contingut, File fitxerSerialitzat) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fitxerSerialitzat))) {
            oos.writeObject(contingut);
        } catch (IOException e) {
            System.out.println("Error en serialitzar l'objecte.");
            e.printStackTrace();
        }
    }

    // Mètode per desserialitzar l'objecte
    private static List<DirectoriInfo> desserialitzarObjecte(File fitxerSerialitzat) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fitxerSerialitzat))) {
            return (List<DirectoriInfo>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error en desserialitzar l'objecte.");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
