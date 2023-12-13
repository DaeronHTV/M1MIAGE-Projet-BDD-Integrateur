package asmr.visiopad;

/**
 * Permet de représenter sous la forme d'une classe
 * les tuples ainsi que leur données provenant de la 
 * Table ContactResident de la base de données
 * @author Mohammad Oubari
 */
public class ContactResident {
    
    private String idResident;
    private String idContact;

    /**
     * Constructeur par Defaut
     */
    public ContactResident(){
    }
    
    /**
     * Constructeur prenant en paramètre plusieurs string
     * afin d'initialiser l'instance
     * @param idContact
     * @param idResident
     */
    public ContactResident(String idContact, String idResident){
        this.idResident = idResident;
        this.idContact = idContact;
    }

    public String getIdResident() {
        return idResident;
    }

    public void setIdResident(String idResident) {
        this.idResident = idResident;
    }

    public String getIdContact() {
        return idContact;
    }

    public void setIdContact(String idContact) {
        this.idContact = idContact;
    }

}
