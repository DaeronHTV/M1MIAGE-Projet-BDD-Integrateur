package asmr.fillBdd;

import java.util.ArrayList;

import asmr.dataAccess.ContactDAO;
import asmr.dataAccess.EtablissementDAO;
import asmr.dataAccess.InviteDAO;
import asmr.dataAccess.PersonneDAO;
import asmr.dataAccess.PersonnelDAO;
import asmr.dataAccess.ResidentDAO;
import asmr.dataAccess.TabletteDAO;
import asmr.dataAccess.UniteDAO;
import asmr.dataAccess.UtilisateurDAO;
import asmr.visiopad.Contact;
import asmr.visiopad.Etablissement;
import asmr.visiopad.Invite;
import asmr.visiopad.Personne;
import asmr.visiopad.Personnel;
import asmr.visiopad.Resident;
import asmr.visiopad.Tablette;
import asmr.visiopad.Unite;
import asmr.visiopad.Utilisateur;

public class Main {

    /**
     * Programme permettant de remplir la base de données avec des 
     * valeurs aléatoire
     * @implNote La liste des établissement a été récupéré de la liste
     * du gouvernement
     * @param args
     */
    public static void main(String[] args) {
        int Nombre = 6000;
        ArrayList<Personne> listPUtil = new ArrayList<Personne>();
        ArrayList<Personne> listPInv = new ArrayList<Personne>();
        ArrayList<Personne> listPRes = new ArrayList<Personne>();
        
        EtablissementDAO etablissementDAO = new EtablissementDAO();
        Etablissement[] listEtablissement = ListGenerator.getDataEtablissement();
        for(Etablissement etb: listEtablissement){etablissementDAO.create(etb);};

        PersonneDAO personneDAO = new PersonneDAO();
        Personne[] personnes = ListGenerator.getDataPersonne(Nombre);
        for(Personne pers : personnes){personneDAO.create(pers);};

        UniteDAO uniteDAO = new UniteDAO();
        Unite[] unites = ListGenerator.getDataUnite(etablissementDAO.getAll());
        for(Unite unite: unites){ uniteDAO.create(unite); }

        TabletteDAO tabletteDAO = new TabletteDAO();
        Tablette[] tablettes = ListGenerator.getDataTablettes(6000);
        for(Tablette tab: tablettes){tabletteDAO.create(tab);}

        ArrayList<Personne> listPersonne = personneDAO.getAll();
        System.out.println(listPersonne.size());
        int i = 0;
        for(Personne p: listPersonne){
            if(i < 1000){listPInv.add(p);}
            else if(i < 3500){listPRes.add(p);}
            else{listPUtil.add(p);}
            i++;
        }

        InviteDAO inviteDAO = new InviteDAO();
        Invite[] invites = ListGenerator.getDataInvite(listPInv);
        for(Invite inv: invites){inviteDAO.create(inv);}

        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        Utilisateur[] utilisateurs = ListGenerator.getDatautilisateur(listPUtil);
        for(Utilisateur util: utilisateurs){utilisateurDAO.create(util);}

        ArrayList<Utilisateur> listUtilisateur = utilisateurDAO.getAll();
        int taille = (listUtilisateur.size()/2);
        ArrayList<Utilisateur> listUtilPerso = new ArrayList<Utilisateur>();
        ArrayList<Utilisateur> listUtilContact = new ArrayList<Utilisateur>();
        i = 0;
        for(Utilisateur u : listUtilisateur){
            if(i < taille){listUtilPerso.add(u);}
            else{listUtilContact.add(u);}
            i++;
        }

        PersonnelDAO personnelDAO = new PersonnelDAO();
        Personnel[] personnels = ListGenerator.getDataPersonnel(listUtilPerso);
        for(Personnel p: personnels){personnelDAO.create(p);}

        ContactDAO contactDAO = new ContactDAO();
        Contact[] contacts = ListGenerator.getDataContact(listUtilContact);
        for(Contact c: contacts){contactDAO.create(c);}
        ResidentDAO residentDAO = new ResidentDAO();
        
        ArrayList<Personnel> perso = personnelDAO.getAll();
        ArrayList<Unite> ListUnites = uniteDAO.getAll();
        Resident[] residents = ListGenerator.getDataResident(listPersonne, perso, ListUnites);
        for(Resident r: residents){residentDAO.create(r);}
    }
}
