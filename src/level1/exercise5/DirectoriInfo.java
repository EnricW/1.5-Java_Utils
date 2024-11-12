package level1.exercise5;

import java.io.Serializable;

class DirectoriInfo implements Serializable {
    private String nom;
    private String dataModificacio;

    public DirectoriInfo(String nom, String dataModificacio) {
        this.nom = nom;
        this.dataModificacio = dataModificacio;
    }

    @Override
    public String toString() {
        return nom + " (Última modificació: " + dataModificacio + ")";
    }
}
