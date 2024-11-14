package level1.exercise5;

import java.io.Serializable;

class DirectoryInfo implements Serializable {
    private String name;
    private String modificationDate;

    public DirectoryInfo(String name, String modificationDate) {
        this.name = name;
        this.modificationDate = modificationDate;
    }

    @Override
    public String toString() {
        return name + " (Last modification: " + modificationDate + ")";
    }
}
