package asmr.fillBdd;

import java.util.ArrayList;

import asmr.dataUtilities.BddHelper;
import asmr.dataUtilities.FileTools;
import asmr.visiopad.Contact;
import asmr.visiopad.Etablissement;
import asmr.visiopad.Invite;
import asmr.visiopad.Personne;
import asmr.visiopad.Personnel;
import asmr.visiopad.Resident;
import asmr.visiopad.Tablette;
import asmr.visiopad.Unite;
import asmr.visiopad.Utilisateur;
import asmr.visiopad.Etablissement.EtablissementBuilder;

/**
 * Une classe statique permettant de générer les instances des tuples
 * qui seront enregistrées dans la base de donnéess
 * @author Avanzino Aurélien
 * @since 01/02/2021
 * @see DataGenerator
 */
public class ListGenerator {
    private static DataGenerator dg = DataGenerator.getInstance();
    
    /**
     * Recupère la liste complète des établissement se trouvant dans le fichier
     * csv et créer la liste des instances d'établissements
     * @return La liste des etablissements
     * @since 01/02/2021
     * @see PathFile#Etablissement
     */
    public static Etablissement[] getDataEtablissement(){
        String[] temp = FileTools.fileToString(PathFile.Etablissement).split("\n");
        Etablissement[] list = new Etablissement[temp.length];
        int i = 0;
        while(i < temp.length){
            String nom = BddHelper.toASCII(temp[i].split(";")[0]);
            String mail = temp[i].split(";")[6];
            String rue = BddHelper.toASCII(temp[i].split(";")[2]);
            int codePostal = Integer.parseInt(temp[i].split(";")[3]);
            int nbPlaces = Integer.parseInt(temp[i].split(";")[1]);
            String ville = BddHelper.toASCII(temp[i].split(";")[4]);
            String tel = BddHelper.toASCII(temp[i].split(";")[5].replaceAll(" ", ""));
            int nbUnitesOff = (int)Math.round(Math.random() * 25);
            list[i] = new EtablissementBuilder(null,nom).adresseMail(mail).codePostal(codePostal).nbPlaces(nbPlaces)
            .nbUnitesOff(nbUnitesOff).pays("FRANCE").numeroTelephone(tel).rue(rue).ville(ville).build();
            i++;
        }
        return list;
    }

    public static Unite[]  getDataUnite(ArrayList<Etablissement> etablissements){
        int i = 0;
        for(Etablissement etb: etablissements){ i = i + etb.getNbUnitesOff(); }
        System.out.println(i);
        Unite[] result = new Unite[i];
        int j = 0;
        for(Etablissement etb: etablissements){
            i = 0;
            while(i < etb.getNbUnitesOff()){
                result[j] = new Unite(null, etb.getId(), BddHelper.toASCII(dg.unites.get(i).getLeft()), dg.unites.get(0).getRight());
                i++;j++;
            }
        }
        return result;
    }

    /**
     * Créer une liste d'instance de <code>Personne</code> en
     * fonction de nombre de personne demandé
     * @param nombre Le nombre de personne à créer
     * @return La liste des personnes créées
     * @since 01/02/2021
     */
    public static Personne[] getDataPersonne(int nombre){
        String[] prenomM = dg.getListMName(nombre);
        String[] prenomF = dg.getListWName(nombre);
        String[] famillyName = dg.getListFamilyName(nombre);
        Personne[] list = new Personne[nombre];
        int i = 0;
        while(i < nombre){
            if(i%2 == 0){list[i] = new Personne(null, BddHelper.toASCII(prenomF[i]), BddHelper.toASCII(famillyName[i]));}
            else{ list[i] = new Personne(null, BddHelper.toASCII(prenomM[i]), BddHelper.toASCII(famillyName[i])); }
            i++;
        }
        return list;
    }

    /**
     * Créer une liste d'utilisateur enregistrable dans la base de données
     * @param nombre Le nombre d'utilisateur à créer
     * @param personnes La liste des personnes créées
     * @return La liste des utilisateurs créée
     * @throws Exception Si le nombre d'<code>Utilisateur</code> à créer est supérieur
     * au nombre de personne présent dans la liste
     */
    public static Utilisateur[] getDatautilisateur(ArrayList<Personne> personnes) {
        Utilisateur[] utilisateurs = new Utilisateur[personnes.size()];
        int i = 0;
        for(Personne p: personnes){
            utilisateurs[i] = new Utilisateur(p.getIdPersonne(), p.getPrenom(), p.getNom(), dg.getTelephone(), dg.getMailAdresse(p.getPrenom(), p.getNom()));
            i++;
        }
        return utilisateurs;
    }

    public static Invite[] getDataInvite(ArrayList<Personne> personnes) {
        Invite[] invites = new Invite[personnes.size()];
        int i = 0;
        for(Personne p: personnes){
            invites[i] = new Invite(p.getIdPersonne(), p.getPrenom(), p.getNom(), dg.getMailAdresse(p.getPrenom(), p.getNom()));
            i++;
        }
        return invites;
    }

    public static Personnel[] getDataPersonnel(ArrayList<Utilisateur> utilisateurs){
        Personnel[] personnels = new Personnel[utilisateurs.size()];
        int i = 0;
        for(Utilisateur u: utilisateurs){
            personnels[i] = new Personnel(u.getIdPersonne(), u.getPrenom(), u.getPrenom(), dg.getTelephone(), u.getAdresseMail(), BddHelper.toASCII(dg.getFonction()));
            i++;
        }
        return personnels;
    }

    public static Contact[] getDataContact(ArrayList<Utilisateur> utilisateurs){
        Contact[] contacts = new Contact[utilisateurs.size()];
        int i = 0;
        for(Utilisateur u: utilisateurs){
            contacts[i] = new Contact(u.getIdPersonne(), u.getPrenom(), u.getNom(), u.getNumeroTelephone(), u.getAdresseMail(), dg.getValide());
            i++;
        }
        return contacts;
    }

    public static Resident[] getDataResident(ArrayList<Personne> personnes, ArrayList<Personnel> personnels, ArrayList<Unite> unites){
        Resident[] residents = new Resident[personnes.size()];
        int i = 0; int j = 0; int t = 0;
        for(Personne p: personnes){
            residents[j] = new Resident(p.getIdPersonne(), p.getNom(), p.getPrenom(), unites.get(t).getIdUnite(), dg.getDate(), dg.getStatutResident(), personnels.get(i).getIdPersonne());
            i++;j++;t++;
            if(i == personnels.size()-1){i = 0;}
            if(t == 28){t = 0;}
        }
        return residents;
    }

    public static Tablette[] getDataTablettes(int nombre){
        Tablette[] tablettes = new Tablette[nombre];
        for(int i = 0; i < nombre; i++){tablettes[i] = new Tablette(null, dg.getEtatTablette());}
        return tablettes;
    }
}
