package asmr.visiopad;

import java.util.Date;

/**
 * Permet de représenter sous la forme d'une classe
 * les tuples ainsi que leur données provenant de la 
 * Table Affectation de la base de données
 * @author Mohammad Oubari
 */
public class Affectation {

    private String idAffectation;
    private String idPersonnel;
    private String idUnite;
    private Date dateDebut;
    private Date dateFin;

    /**
     * Constructeur par Defaut
     */
    public Affectation() {
    }

    /**
     * Constructeur prenant en paramètre plusieurs string
     * afin d'initialiser l'instance
     * @param idAffectation
     * @param idPersonnel
     * @param idUnite
     * @param dateDebut
     * @param dateFin
     */
    public Affectation(String idAffectation, String idPersonnel, String idUnite, Date dateDebut, Date dateFin) {
        this.idAffectation = idAffectation;
        this.idPersonnel = idPersonnel;
        this.idUnite = idUnite;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public String getIdAffectation() {
        return idAffectation;
    }

    public void setIdAffectation(String idAffectation) {
        this.idAffectation = idAffectation;
    }

    public String getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(String idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public String getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(String idUnite) {
        this.idUnite = idUnite;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    
}
