package level1.exercise3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class LlistarDirectori {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Ús: java LlistarDirectori <directori> <fitxer_sortida.txt>");
            return;
        }

        File directori = new File(args[0]);
        File fitxerSortida = new File(args[1]);

        if (!directori.exists() || !directori.isDirectory()) {
            System.out.println("El camí especificat no és un directori vàlid.");
            return;
        }

        try (FileWriter escriptor = new FileWriter(fitxerSortida)) {
            escriptor.write("Contingut de l'arbre de directoris de '" + directori.getPath() + "':\n");
            llistarArbre(directori, 0, escriptor);
            System.out.println("Resultat guardat a: " + fitxerSortida.getPath());
        } catch (IOException e) {
            System.out.println("S'ha produït un error en escriure al fitxer de sortida.");
            e.printStackTrace();
        }
    }

    private static void llistarArbre(File directori, int nivell, FileWriter escriptor) throws IOException {
        File[] contingut = directori.listFiles();

        if (contingut == null || contingut.length == 0) {
            escriptor.write(" ".repeat(nivell * 2) + "(Directori buit)\n");
            return;
        }

        Arrays.sort(contingut);

        SimpleDateFormat formatData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (File element : contingut) {
            String tipus = element.isDirectory() ? "D" : "F";
            String dataModificacio = formatData.format(new Date(element.lastModified()));
            escriptor.write(" ".repeat(nivell * 2) + "- [" + tipus + "] " + element.getName() + " (Última modificació: " + dataModificacio + ")\n");

            // Si és un directori, crida el mètode recursivament
            if (element.isDirectory()) {
                llistarArbre(element, nivell + 1, escriptor);
            }
        }
    }
}
