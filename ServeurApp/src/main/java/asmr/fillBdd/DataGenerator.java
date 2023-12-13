package asmr.fillBdd;

import java.sql.Date;
import java.util.ArrayList;
import asmr.dataUtilities.FileTools;
import asmr.visiopad.enumeration.EtatTablette;
import asmr.visiopad.enumeration.EtatUnite;
import asmr.visiopad.enumeration.StatutResident;

/**
 * Une classe permettant de générer des informations qui seront insérés
 * dans les différentes isntances à enregistrer en base de données
 * @implNote Cette classe respecte le design pattern <code>singleton</code>
 * @author Avanzino Aurélien
 * @since 01/002/2021
 */
@SuppressWarnings(value = "deprecation")
public class DataGenerator {
    public static volatile DataGenerator instance = null;
    String[] mail = {"@gmail.com", "@hotmail.fr", "@hotmail.com", "@outlook.fr", "outlook.com", "@sfr.fr", 
        "@orange.fr", "@yahoo.fr"};
    public String[] prenomF;
    public String[] prenomM;
    public ArrayList<Tuple<String, EtatUnite>> unites = new ArrayList<Tuple<String,EtatUnite>>();

    /**
     * Constructeur de la classe qui récupère la liste des
     * prénoms féminins et masculins dans les différents fichiers
     * csv
     * @see PathFile#PrenomF
     * @see PathFile#PrenomM
     * @since 01/02/2021
     */
    public DataGenerator(){
        String temp = FileTools.fileToString(PathFile.PrenomF);
        this.prenomF = temp.split("\n");
        temp = FileTools.fileToString(PathFile.PrenomM);
        this.prenomM = temp.split("\n");
        temp = FileTools.fileToString(PathFile.Unite);
        String[] table = temp.split(",");
        for(String s: table){
            if(s.split(";")[1] == "OUVERTE"){
                unites.add(new Tuple<String,EtatUnite>(s.split(";")[0], EtatUnite.OUVERTE));
            } else if(s.split(";")[1] == "DEJOUR"){
                unites.add(new Tuple<String,EtatUnite>(s.split(";")[0], EtatUnite.DEJOUR));
            }else if(s.split(";")[1] == "FERMEE"){
                unites.add(new Tuple<String,EtatUnite>(s.split(";")[0], EtatUnite.FERMEE));
            }else{
                unites.add(new Tuple<String,EtatUnite>(s.split(";")[0], EtatUnite.CONTAGIEUSE));
            }
        }
    }

    /**
     * Créer l'instance de la classe si nécessaires et la renvoie
     * @return L'instance de la classe DataGenerator
     * @since 01/02/2021
     */
    public final static DataGenerator getInstance() {
        if(DataGenerator.instance == null){
            synchronized(DataGenerator.class){
                if(DataGenerator.instance == null){
                    DataGenerator.instance = new DataGenerator();
                }
            }
        }
        return DataGenerator.instance;
    }

    /**
     * Retourne de manière aléatoire un nom de famille
     * @return Un nom de famille
     * @since 01/02/2021
     */
    public String getFamillyName(){
        String temp1 = getMName();
        String temp2 = getWName();
        return Math.random() < 0.5 ? temp1 : temp2;
    }

    /**
     * 
     * @param nombre Le nombre de nom de famille à générer
     * @return La liste des noms familles créés
     * @since 01/02/2021
     */
    public String[] getListFamilyName(int nombre){
        String[] result = new String[nombre];
        int i = 0;
        while(i < nombre){result[i] = getFamillyName(); i++;}
        return result;
    }

    /**
    * Génère aléatoirement un nom féminin
    * @return Un nom fémnin
    * @since 01/02/2021
    */
    public String getWName(){
        int choix = (int)Math.round(Math.random() * 898);
        //System.out.println(prenomF.length);
        return prenomF[choix];
    }

    /**
     * Créer une liste de prénoms féminins aléatoirement
     * @param nombre Le nombre de prénoms à créer
     * @return La liste des noms féminins créés
     */
    public String[] getListWName(int nombre){
        String[] result = new String[nombre];
        int i = 0;
        while(i < nombre){ result[i] = getWName(); i++;}
        return result;
    }

    /**
     * Génére aléatoirement un nom masculin
     * @return le nom généré 
     * @since 01/02/2021
     */
    public String getMName(){
        int choix = (int)Math.round(Math.random() * 899);
        return prenomM[choix];
    }

    /**
     * Créer une liste de prénoms masculins aléatoirement
     * @param nombre Le nombre de prénoms à créer
     * @return La liste des noms masculins créés
     */
    public String[] getListMName(int nombre){
        String[] result = new String[nombre];
        int i = 0;
        while(i < nombre){ result[i] = getMName(); i++;}
        return result;
    }

    /**
     * Permet de générer une adresse mail aléatoire en fonction
     * du nom et du prénom de la personne
     * @param name Le prenom de la personne
     * @param familyName Le nom de famille de la personne
     * @return L'adresse mail de la personne
     * @since 01/02/2021
     */
    public String getMailAdresse(String name, String familyName){
        int choix = (int)Math.round(Math.random() * 7);
        return name+"."+familyName+mail[choix];
    }

    /**
     * Génére aléatoirement un numéro de telephone
     * @return Le numérp teléphone
     * @since 01/02/2021
     */
    public String getTelephone(){
        String telephone = "0";
        for(int i = 0; i < 9; i++){ telephone += (int)Math.round(Math.random()*9); }
        return telephone.length() == 10 ? telephone : null;
    }

    /**
     * Permet de générer aléatoirement si un compte
     * est valdie ou non
     * @return la validité du compte
     * @since 01/02/2021
     */
    public int getValide(){ return Math.random() < 0.5 ? 0 : 1; }

    /**
     * 
     * @return
     */
    public EtatTablette getEtatTablette(){return Math.random() < 0.5 ? EtatTablette.HS : EtatTablette.ENCHARGEMENT;}

    public String getFonction(){
        String[] fonction = {"médecin", "infirmière", "administratif", "secretaire"};
        return fonction[(int)Math.round(Math.random()*3)];
    }

    public StatutResident getStatutResident(){
        return Math.random() > 0.5 ? StatutResident.DISPONIBLE : Math.random() < 0.2 ? StatutResident.ANCIENRESIDENT : StatutResident.INDISPONIBLE;
    }

    public Date getDate(){
        int[] years = {1950, 1940, 1945, 1960, 1965, 1955, 1958, 1968, 1970, 1969, 1961};
        int day = (int)Math.round(Math.random()*30);
        int month = (int)Math.round(Math.random()*12);
        int year = years[(int)Math.round(Math.random()*10)];
        return new Date(year, month, day);
    }
}
