package asmr.dataUtilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import asmr.annotation.*;
import asmr.dataUtilities.log.LogHelper;

/**
 * Il s'agit d'une classe contenant l'ensemble des méthodes permettant de faire
 * des traitement sur des fichiers afin de pouvoir être utilisé par le serveur
 * et les différents services
 * @author Avanzino Aurélien
 * @since 28/01/2021
 */
@Service(name="FileTools")
public class FileTools {
    
    /**
     * Transforme le contenu d'un fichier se trouvant dans le path 
     * donné sous la forme d'une chaine de caractères
     * @param path Chemin absolue ou relatif du fichier à traiter
     * @return Le contenu du fichier en string ou <code>null</code>
     * si il y a une exception lors du traitement
     * @since 28/01/2021
     */
    public static String fileToString(String path){
        String str = null;
        try
        {
            InputStream is = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader buffer = new BufferedReader(isr);
            String line = buffer.readLine();
            StringBuilder builder = new StringBuilder();   
            while(line != null){
               builder.append(line).append("\n");
               line = buffer.readLine();
            }
            str = builder.toString();
            buffer.close();
        }
        catch(Exception e){ LogHelper.error(e); }
        return str;
    }

    /**
     * Permet de recuperer le chemin absolu du dossier
     * parent contenant le jar executable
     * @return Le chemin 
     */
    public static String getRootPath(){
        File f = new File("");
        return f.getAbsolutePath();
    }

    public static boolean createFolder(String path, String name){
        boolean result = false;
        File logDirectory = new File(path+File.separatorChar+name);
	    if(!logDirectory.exists()){
	    	result = logDirectory.mkdirs();
        }
        return logDirectory.exists() || result;
    }
}
