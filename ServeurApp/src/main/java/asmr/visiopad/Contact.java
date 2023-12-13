package asmr.visiopad;

/**
 * Permet de représenter sous la forme d'une classe
 * les tuples ainsi que leur données provenant de la 
 * Table Utilisateur de la base de données
 * @author Mohammad Oubari
 * @implNote Herite de la classe Utilisateur
 * @see Utilsiateur
 */
public class Contact extends Utilisateur {

    private int compteValide;
    
    /**
     * Constructeur par Defaut
     * Appel le constrcuteur par defaut de la classe Utilsiateur
     * @see Utilisateur#Utilisateur()
     */
    public Contact() {
        super();
    }

    /**
     * Constructeur prenant en paramètre plusieurs string
     * afin d'initialiser l'instance
     * @param idContact
     * @param prenom
     * @param nom
     * @param numeroTelephone
     * @param adressMail
     * @param compteValide
     */
    public Contact(String idContact, String prenom, String nom, String numeroTelephone, String adressMail, int compteValide) {
        super(idContact, prenom, nom, numeroTelephone, adressMail);
        this.compteValide = compteValide;
    }

    public int getCompteValide() {
        return compteValide;
    }

    public void setCompteValide(int compteValide) {
        this.compteValide = compteValide;
    }
}
