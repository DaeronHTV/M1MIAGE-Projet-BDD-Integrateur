package asmr.dataAccess;

import asmr.visiopad.Affectation;
import asmr.visiopad.Contact;
import asmr.visiopad.ContactResident;
import asmr.visiopad.CreneauVisio;
import asmr.visiopad.Etablissement;
import asmr.visiopad.Invite;
import asmr.visiopad.InviteRendezVous;
import asmr.visiopad.Personne;
import asmr.visiopad.Personnel;
import asmr.visiopad.PersonnelResident;
import asmr.visiopad.RendezVous;
import asmr.visiopad.Resident;
import asmr.visiopad.Tablette;
import asmr.visiopad.Unite;
import asmr.visiopad.Utilisateur;

public class DAOFactory {

    public static SqlDAO<Affectation> getAffectationDAO(){
        return new AffectationDAO();
    }
    
    public static SqlDAO<Etablissement> getEtablissementDAO(){
        return new EtablissementDAO();
    }

    public static SqlDAO<Invite> getInviteDAO(){
        return new InviteDAO();
    }

    public static SqlDAO<Personne> getPersonneDAO(){
        return new PersonneDAO();
    }

    public static SqlDAO<Personnel> getPersonnelDAO(){
        return new PersonnelDAO();
    }

    public static SqlDAO<Tablette> getTabletteDAO(){
        return new TabletteDAO();
    }

    public static SqlDAO<Unite> getUniteDAO(){
        return new UniteDAO();
    }

    public static SqlDAO<RendezVous> getRendezVousDAO(){
        return new RendezVousDAO();
    }

    public static SqlDAO<Resident> getResidentDAO(){
        return new ResidentDAO();
    }

    public static SqlDAO<Utilisateur> getUtilisateurDAO(){
        return new UtilisateurDAO();
    }

    public static SqlDAO<Contact> getContactDAO(){
        return new ContactDAO();
    }

    public static SqlDAO<ContactResident> getContactResidentDAO(){
        return new ContactResidentDAO();
    }

    public static SqlDAO<CreneauVisio> getCreneauVisioDAO(){
        return new CreneauVisioDAO();
    }

    public static SqlDAO<PersonnelResident> getPersonnelResidentDAO(){
        return new PersonnelResidentDAO();
    }

    public static SqlDAO<InviteRendezVous> getInviteRendezVousDAO(){
        return new InviteRendezVousDAO();
    }
    
}
