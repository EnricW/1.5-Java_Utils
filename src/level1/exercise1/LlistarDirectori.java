package level1.exercise1;

import java.io.File;
import java.util.Arrays;

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

        String[] fitxers = directori.list();

        if (fitxers == null || fitxers.length == 0) {
            System.out.println("El directori està buit.");
            return;
        }

        Arrays.sort(fitxers);

        System.out.println("Contingut del directori '" + directori.getPath() + "' en ordre alfabètic:");
        for (String fitxer : fitxers) {
            System.out.println(fitxer);
        }
    }
}
