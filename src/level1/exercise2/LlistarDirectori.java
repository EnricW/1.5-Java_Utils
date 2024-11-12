package level1.exercise2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class LlistarDirectori {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Ús: java LlistarDirectori <directori>");
            return;
        }

        File directori = new File(args[0]);

        if (!directori.exists() || !directori.isDirectory()) {
            System.out.println("El camí especificat no és un directori vàlid.");
            return;
        }

        System.out.println("Contingut de l'arbre de directoris de '" + directori.getPath() + "':");
        llistarArbre(directori, 0);
    }

    private static void llistarArbre(File directori, int nivell) {
        File[] contingut = directori.listFiles();

        if (contingut == null || contingut.length == 0) {
            System.out.println(" ".repeat(nivell * 2) + "(Directori buit)");
            return;
        }

        Arrays.sort(contingut);

        SimpleDateFormat formatData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (File element : contingut) {
            String tipus = element.isDirectory() ? "D" : "F";
            String dataModificacio = formatData.format(new Date(element.lastModified()));
            System.out.println(" ".repeat(nivell * 2) + "- [" + tipus + "] " + element.getName() + " (Última modificació: " + dataModificacio + ")");

            // Si és un directori, crida el mètode recursivament
            if (element.isDirectory()) {
                llistarArbre(element, nivell + 1);
            }
        }
    }
}
