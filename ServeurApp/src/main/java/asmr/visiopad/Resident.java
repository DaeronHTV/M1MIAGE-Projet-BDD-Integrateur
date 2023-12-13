package asmr.visiopad;

import java.sql.Date;

import asmr.visiopad.enumeration.StatutResident;

/**
 * Permet de représenter sous la forme d'une classe
 * les tuples ainsi que leur données provenant de la 
 * Table Resident de la base de données
 * @author Mohammad Oubari
 * @implNote Herite de la classe Personne
 * @see Personne
 */
public class Resident extends Personne {

    private String idPersonnel;
    private String idUnite;
    private Date dateNaissanceResident;
    private StatutResident statutResident;

    /**
     * Constructeur par Defaut
     */
    public Resident(){
        super();
    }

    /**
     * Constructeur prenant en paramètre plusieurs string
     * afin d'initialiser l'instance
     * @param idResident
     * @param prenom
     * @param nom
     * @param idUnite
     * @param dateNaissanceResident
     * @param statutResident
     * @param idPersonnel
     */
    public Resident(String idResident, String prenom, String nom, String idUnite, Date dateNaissanceResident, StatutResident statutResident, String idPersonnel) {
        super(idResident, prenom, nom);
        this.idUnite = idUnite;
        this.dateNaissanceResident = dateNaissanceResident;
        this.statutResident = statutResident;
        this.idPersonnel = idPersonnel;
    }

    public String getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(String idUnite) {
        this.idUnite = idUnite;
    }

    public Date getDateNaissanceResident() {
        return dateNaissanceResident;
    }

    public void setDateNaissanceResident(Date dateNaissanceResident) {
        this.dateNaissanceResident = dateNaissanceResident;
    }

    public StatutResident getStatutResident() {
        return statutResident;
    }

    public void setStatutResident(StatutResident statutResident) {
        this.statutResident = statutResident;
    }

    public String getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(String idPersonnel) {
        this.idPersonnel = idPersonnel;
    }
}
