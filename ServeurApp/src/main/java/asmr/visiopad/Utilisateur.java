package asmr.visiopad;

/**
 * Permet de représenter sous la forme d'une classe
 * les tuples ainsi que leur données provenant de la 
 * Table Utilisateur de la base de données
 * @author Mohammad Oubari
 * @implNote Herite de la classe Personne
 * @see Personne
 */
public class Utilisateur extends Personne {

    private String numeroTelephone;
    private String adresseMail;

    /**
     * Constructeur par defaut de la classe
     * Appel le constructeur par defaut de Personne
     * @see Personne#Personne()
     */
    public Utilisateur() {
        super();
    }

    /**
     * Cosntructeur prenant en paramètre plusieurs string
     * afin d'initialiser l'instance
     * @param idUtilisateur - Identifiant égale à l'ID de la personne
     * @param prenom - prénom de la personne
     * @param nom - nom de la personne
     * @param numeroTelephone - telephone de la personne
     * @param adressMail - Adresse mail de la personne
     * @see Personne#Personne(String, String, String)
     */
    public Utilisateur(String idUtilisateur, String prenom, String nom, String numeroTelephone, String adressMail) {
        super(idUtilisateur, prenom, nom);
        this.adresseMail = adressMail;
        this.numeroTelephone = numeroTelephone;
    }

    /**
     * Renvoie le numéro de telephone
     * @return le numero de telephone
     */
    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    /**
     * Permet de changer le numero de telephone
     * @param numeroTelephone - le nouveau numéro de telephone
     */
    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    /**
     * Renvoie l'adresse mail de la personne
     * @return l'adresse mail
     */
    public String getAdresseMail() {
        return adresseMail;
    }

    /**
     * Permet de changer l'adresse mail de l'utilisateur
     * @param adresseMail - la nouvelle adresse mail
     */
    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

}
