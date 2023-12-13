package asmr.visiopad;

import java.util.Date;

/**
 * Permet de représenter sous la forme d'une classe
 * les tuples ainsi que leur données provenant de la 
 * Table PersonnelResident de la base de données
 * Cette table représente la relatione entre 
 * Personnel et Resident
 * @author Mohammad Oubari
 */
public class PersonnelResident {
    
    private String idPersonnelResident;
    private String idResident;
    private String idPersonnel;
    private Date dateDebut;
    private Date dateFin;
    
    /**
     * Constructeur par Defaut
     */
    public PersonnelResident() {
    }

    /**
     * Constructeur prenant en paramètre plusieurs string
     * afin d'initialiser l'instance
     * @param idPersonnelResident
     * @param idResident
     * @param idPersonnel
     * @param dateDebut
     * @param dateFin
     */
    public PersonnelResident(String idPersonnelResident, String idResident, String idPersonnel, Date dateDebut, Date dateFin) {
        this.idPersonnelResident = idPersonnelResident;
        this.idResident = idResident;
        this.idPersonnel = idPersonnel;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public String getIdPersonnelResident() {
        return idPersonnelResident;
    }

    public void setIdPersonnelResident(String idPersonnelResident) {
        this.idPersonnelResident = idPersonnelResident;
    }

    public String getIdResident() {
        return idResident;
    }

    public void setIdResident(String idResident) {
        this.idResident = idResident;
    }

    public String getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(String idPersonnel) {
        this.idPersonnel = idPersonnel;
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
