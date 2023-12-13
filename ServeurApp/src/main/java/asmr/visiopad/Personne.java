package asmr.visiopad;

/**
 * Permet de représenter sous la forme d'une classe
 * les tuples ainsi que leur données provenant de la 
 * Table Personne de la base de données
 * @author Mohammad Oubari
 */
public class Personne {

    private String idPersonne;
    private String nom;
    private String prenom;

    /**
     * Constructeur par Defaut
     */
    public Personne() {
    }

    /**
     * Constructeur prenant en paramètre plusieurs string
     * afin d'initialiser l'instance
     * @param idPersonne
     * @param prenom
     * @param nom
     */
    public Personne(String idPersonne, String prenom, String nom) {
        this.idPersonne = idPersonne;
        this.prenom = prenom;
        this.nom = nom;
    }

    public String getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(String idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
