package asmr.visiopad;

/**
 * Permet de représenter sous la forme d'une classe
 * les données se trouvant dans la table Personne de 
 * la base de données
 * @implSpec Herite de la classe Utilisateur
 * @author Mohammad Oubari
 * @see Utilisateur
 */
public class Personnel extends Utilisateur {

    private String fonction;

    /**
     * Constructeur par defaut. Appele le constructeur 
     * par defaut de Utilisateur
     * @see Utilisateur#Utilisateur()
     */
    public Personnel(){
        super();
    }

    /**
     * Constructeur prenant en paramètre différents afin de
     * pré-remplir l'instance
     * @param idPersonnel - Identifiant égal à l'identifiant de Utilsiateur
     * @param prenom - Nom de L'utilisateur
     * @param nom - Prénom de l'utilisateur
     * @param numeroTelephone - Numéro de telephone de l'utilisateur
     * @param adressMail - Adresse Mail de l'utilisateur
     * @param fonction - Fonction de l'utilisateur
     * @see Utilisateur#Utilisateur(String, String, String, String, String)
     */
    public Personnel(String idPersonnel, String prenom, String nom, String numeroTelephone, String adressMail, String fonction) {
        super(idPersonnel, prenom, nom, numeroTelephone, adressMail);
        this.fonction = fonction;
    }
    
    /**
     * Récupère la fonction du personnel
     * @return la fonction du personnel
     */
    public String getFonction() {
        return fonction;
    }

    /**
     * Permet de changer la fonction du personnel
     * @param fonction - La nouvelle fonction du personnel
     */
    public void setFonction(String fonction) {
        this.fonction = fonction;
    }
}
