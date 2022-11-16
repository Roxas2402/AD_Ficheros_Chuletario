package ficheros;

import models.AnimalModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class PasarAXML {
    private static ArrayList<AnimalModel> animalesList;

    public static void main(String[] args) {
        animalesList = new ArrayList<>();
        AnimalModel animal = new AnimalModel("Perro", "American Stanford", 5, "Gris");
        animalesList.add(animal);

        try {
            escribirXML(animalesList);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    private static void escribirXML(ArrayList<AnimalModel> animalesList) throws TransformerException, ParserConfigurationException {
        String file = "archivo.xml";

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();

        Element raiz = doc.createElement("Animales");
        doc.appendChild(raiz);
        for (AnimalModel a : animalesList) {
            Element animal = doc.createElement("animal");
            raiz.appendChild(animal);

            Element especie = doc.createElement("especie");
            especie.setTextContent(a.getEspecie());
            animal.appendChild(especie);

            Element raza = doc.createElement("raza");
            raza.setTextContent(a.getRaza());
            animal.appendChild(raza);

            Element edad = doc.createElement("edad");
            edad.setTextContent(String.valueOf(a.getEdad()));
            animal.appendChild(edad);

            Element color = doc.createElement("color");
            color.setTextContent(a.getColor());
            animal.appendChild(color);
        }

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        DOMSource ds = new DOMSource(doc);

        t.setOutputProperty(OutputKeys.INDENT, "yes");

        StreamResult sr = new StreamResult(new File(file));
        t.transform(ds, sr);
    }
}
