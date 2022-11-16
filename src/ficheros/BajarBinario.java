package ficheros;

import models.AnimalModel;

import java.io.*;
import java.util.ArrayList;

public class BajarBinario {
    private static ArrayList<AnimalModel> animalesList;

    public static void main(String[] args) {
        animalesList = new ArrayList<>();
        AnimalModel animal = new AnimalModel("Perro", "American Stanford", 5, "Gris");
        animalesList.add(animal);

        try {
            descargarDeBinario(animalesList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void descargarDeBinario(ArrayList<AnimalModel> animalesList) throws IOException, ClassNotFoundException {
        File file = new File("binario.dat");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

        int i = 1;

        try {
            while (true) {
                animalesList.add((AnimalModel) ois.readObject());
            }
        } catch (EOFException eo) {
            System.out.println("FIN DE LECTURA.");
        }
        for (AnimalModel a : animalesList) {
            System.out.println(a.toString());
        }
    }
}
