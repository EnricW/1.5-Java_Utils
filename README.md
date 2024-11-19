# Java Utils 🎯

## 📄 Descripció - Enunciat de l'exercici

Aquest projecte està format per 6 exercicis que exploren diferents utilitats a Java:

### Nivell 1 ⭐
Aquests exercicis proporcionen una progressió d'aprenentatge sobre manipulació de fitxers i E/S en Java:

#### Exercici 1:
Introducció bàsica a File/Path per llistar directoris amb ordenació alfabètica i arguments per consola.

Crea una classe que llisti alfabèticament el contingut d'un directori rebut per paràmetre.

#### Exercici 2:
Aprofundiment amb recorregut recursiu de directoris, obtenint metadades (tipus i data) dels elements.

Afegeix a la classe de l’exercici anterior, la funcionalitat de llistar un arbre de directoris amb el contingut de tots els seus nivells (recursivament) de manera que s'imprimeixin en pantalla en ordre alfabètic dins de cada nivell, indicant a més si és un directori (D) o un fitxer (F), i la seva última data de modificació.

#### Exercici 3:
Introducció a l'escriptura de fitxers (FileWriter/BufferedWriter) redirigint la sortida a un TXT.

Modifica l’exercici anterior. Ara, en lloc de mostrar el resultat per la pantalla, guarda el resultat en un fitxer TXT.

#### Exercici 4:
Lectura de fitxers de text (FileReader/BufferedReader) mostrant contingut per consola.

Afegeix la funcionalitat de llegir qualsevol fitxer TXT i mostra el seu contingut per consola.

#### Exercici 5:
Serialització/Deserialització d'objectes Java (ObjectInputStream/ObjectOutputStream) amb fitxers .ser.

Ara el programa ha de serialitzar un Objecte Java a un fitxer .ser i després l’ha de desserialitzar.

### Nivell 2 ⭐⭐
#### Exercici 1:
Aquest exercici se centra en la configuració externa d'aplicacions Java, específicament: L'exercici ensenya a externalitzar paràmetres d'una aplicació (rutes de directoris i fitxers) utilitzant fitxers de configuració, ja sigui amb Java Properties (properties.load()) o Apache Commons Configuration, permetent així modificar el comportament del programa sense canviar el codi font, aplicant bones pràctiques de configuració d'aplicacions i separació de configuració i lògica.

Executa l'exercici 3 del nivell anterior parametritzant tots els mètodes en un fitxer de configuració. Pots utilitzar un fitxer Java Properties, o bé la llibreria Apache Commons Configuration si ho prefereixes.
De l'exercici anterior, parametritza el següent: Directori a llegir i Nom i directori del fitxer TXT resultant.

## 💻 Tecnologies Utilitzades

- Java SE Development Kit (JDK) 17
- IntelliJ IDEA
- Git
- GitHub

## 📋 Requisits

- Java JDK 17 o superior
- IntelliJ IDEA (Community o Ultimate Edition)
- Git instal·lat al sistema

## 🛠️ Instal·lació

1. Clona el repositori:
```bash
git clone https://github.com/EnricW/1.5-Java_Utils.git
```

2. Obre el projecte amb IntelliJ IDEA:
   - Obre IntelliJ IDEA
   - File -> Open
   - Selecciona la carpeta del projecte
   - Espera que el projecte s'indexi i es configurin les dependències

## ▶️ Execució

1. Navega fins a la classe principal que conté el mètode main a la terminal
2. Per a cada exercici, fes servir les comandes corresponents:
   - Exercici 1: java ListDirectory <directory>
   - Exercici 2: java ListDirectory <directory>
   - Exercici 3: java ListDirectory <directory> <output_file.txt>
   - Exercici 4: java ListDirectory <directory> <output_file.txt>
   - Exercici 5: java ListDirectory <directory> <output_file.txt> <serialized_file.ser>
