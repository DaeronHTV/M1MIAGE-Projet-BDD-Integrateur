package asmr.visiopad;

/**
 * Permet de représenter sous la forme d'une classe
 * les tuples ainsi que leur données provenant de la 
 * Table Invite de la base de données
 * @author Mohammad Oubari
 * @implNote Herite de la classe Personne
 * @see Personne
 */
public class Invite extends Personne {

    private String adresseMailInvite;

    /**
     * Constructeur par Defaut
     */
    public Invite(){
    }

    /**
     * Constructeur prenant en paramètre plusieurs string
     * afin d'initialiser l'instance
     * @param idInvite
     * @param prenom
     * @param nom
     * @param adresseMailInvite
     */
    public Invite(String idInvite, String prenom, String nom, String adresseMailInvite) {
        super(idInvite, prenom, nom);
        this.adresseMailInvite = adresseMailInvite;
    }

    public String getAdresseMailInvite() {
        return adresseMailInvite;
    }

    public void setAdresseMailInvite(String adresseMailInvite) {
        this.adresseMailInvite = adresseMailInvite;
    }

}
