package ficheros;

import models.AnimalModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BajarXML {
    private static ArrayList<AnimalModel> animalesList;

    public static void main(String[] args) {
        animalesList = new ArrayList<>();
        AnimalModel animal = new AnimalModel("Perro", "American Stanford", 5, "Gris");
        animalesList.add(animal);

        try {
            leerXML(animalesList);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void leerXML(ArrayList<AnimalModel> animalesList) throws ParserConfigurationException, IOException, SAXException {
        File file = new File("archivo.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(file);
        document.getDocumentElement().normalize();

        NodeList nodos = document.getElementsByTagName("animal");

        for (int i = 0; i < nodos.getLength(); i++) {
            Node nodo = nodos.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element animal = (Element) nodo;
                String especie = animal.getElementsByTagName("especie").item(0).getTextContent();
                String raza = animal.getElementsByTagName("raza").item(0).getTextContent();
                String color = animal.getElementsByTagName("color").item(0).getTextContent();
                int edad = Integer.parseInt(animal.getElementsByTagName("edad").item(0).getTextContent());

                AnimalModel animal1 = new AnimalModel(especie, raza, edad, color);
                System.out.println(animal1);

                for (AnimalModel a : animalesList) {
                    System.out.println(a.toString());
                }

            }
        }
    }
}
