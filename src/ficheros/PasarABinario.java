package ficheros;

import models.AnimalModel;

import java.io.*;
import java.util.ArrayList;

public class PasarABinario {
    private static ArrayList<AnimalModel> animalesList;
    public static void main(String[] args) {
        animalesList = new ArrayList<>();
        AnimalModel animal = new AnimalModel("Perro", "American Stanford", 5, "Gris");
        animalesList.add(animal);

        try {
            escribirBinario(animalesList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void escribirBinario(ArrayList<AnimalModel> animales) throws IOException {
        File file = new File("binario.dat");
        FileOutputStream fos = new FileOutputStream(file, true);
        ObjectOutputStream ous = new ObjectOutputStream(fos);

        for (AnimalModel a : animalesList) {
            ous.writeObject(a);
        }

        ous.close();

        System.out.println("Hecho");
    }
}
