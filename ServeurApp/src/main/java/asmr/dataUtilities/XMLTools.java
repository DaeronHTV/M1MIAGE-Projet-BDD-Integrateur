package asmr.dataUtilities;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import asmr.annotation.Service;
import asmr.dataUtilities.log.LogHelper;

@Service(name = "XMLTools")
public class XMLTools {

    /**
     * Récupère le fichier XML et extrait son contenu
     * sous la forme d'un objet XML parseable
     * @param filepath Le chemin du fichier XML
     * @return Le document représentant le XML du fichier
     * @since 27/01/2021
     */
    public static Document getXmlDocument(String filepath){
        Document xml = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            File fileXML = new File(filepath);
            xml = builder.parse(fileXML);
        } 
        catch (Exception e) { LogHelper.error(e); }
        return xml;
    }

    /**
     * Enregistre dans un String l'arborescence entière
     * du document XML donnée en paramètre
     * @return Le contenu entier du Document XML
     * @since 30/01/2021
     */
    public static String XmlToString(Document xmlDocument){
        String result = null;
        try
        {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(xmlDocument), new StreamResult(writer));
            result = writer.getBuffer().toString();
        }
        catch(Exception e){ LogHelper.error(e); }
        return result;
    }

    @Deprecated
    public static Element getRootElement(String filepath){
        Element document = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            File fileXML = new File(filepath);
            Document xml;
            xml = builder.parse(fileXML);
            document = xml.getDocumentElement();
        } 
        catch (Exception e) {LogHelper.error(e);}
        return document;
    }
    
    /**
     * Serialise un objet java dans un fichier XML
     * @param object L'objet à encoder dans le fichier XML
     * @param fileName Le path ou le fichier va être enregistré
     * @throws FileNotFoundException - Si le fichier n'est pas trouvé
     * @throws IOException - Lors d'un problème d'écriture de l'objet
     * @since 25/01/2021
     */
    public static void encodeToFile(Object object, String fileName) throws FileNotFoundException, IOException {
        XMLEncoder encoder = new XMLEncoder(new FileOutputStream(fileName));
        try {
            encoder.writeObject(object);
            encoder.flush();
        }
        catch(Exception e){LogHelper.error(e);} 
        finally {encoder.close();}
    }

    /**
     * Récupérer les données d'un objet JAVA dans un fichier XML
     * @param fileName Le path du fichier à serialiser
     * @return L'objet se trouvant dans le fichier XML donné
     * @throws FileNotFoundException - Si le fichier n'est pas trouvé
     * @throws IOException - Si il y a problème lors de la lecture
     */
    public static Object decodeFromFile(String fileName) throws FileNotFoundException, IOException {
        Object object = null;
        XMLDecoder decoder = new XMLDecoder(new FileInputStream(fileName));
        try {object = decoder.readObject();} 
        catch(Exception e){LogHelper.error(e);}
        finally {decoder.close();}
        return object;
    }
}
