package level2.exercise1;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

public class LlistarDirectori {
    public static void main(String[] args) {
        Properties config = new Properties();

        // Carregar configuració des del fitxer config.properties
        try (InputStream input = new FileInputStream("config.properties")) {
            config.load(input);
        } catch (IOException ex) {
            System.out.println("Error en carregar el fitxer de configuració.");
            ex.printStackTrace();
            return;
        }

        // Obtenir el directori i el fitxer de sortida des de la configuració
        String rutaDirectori = config.getProperty("directori");
        String rutaFitxerSortida = config.getProperty("fitxerSortida");

        if (rutaDirectori == null || rutaFitxerSortida == null) {
            System.out.println("El fitxer de configuració no conté els paràmetres necessaris.");
            return;
        }

        File directori = new File(rutaDirectori);
        File fitxerSortida = new File(rutaFitxerSortida);

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
