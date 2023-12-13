package asmr.visiopad;

import java.util.Date;

/**
 * Permet de représenter sous la forme d'une classe
 * les tuples ainsi que leur données provenant de la 
 * Table CreneauVisio de la base de données
 * @author Mohammad Oubari
 */
public class CreneauVisio {

    private String idCreneau;
    private String idPersonnel;
    private Date dateCreneau;
    private int disponisble;
    private int dureeCreneau;

	/**
     * Constructeur par Defaut
     */
    public CreneauVisio(){
    }

	/**
	 * Constructeur prenant en paramètre plusieurs string
     * afin d'initialiser l'instance
	 * @param idCreneau
	 * @param idPersonnel
	 * @param dateCreneau
	 * @param disponisble
	 * @param dureeCreneau
	 */
    public CreneauVisio(String idCreneau, String idPersonnel, Date dateCreneau, int disponisble, int dureeCreneau) {
        this.idCreneau = idCreneau;
        this.idPersonnel = idPersonnel;
        this.dateCreneau = dateCreneau;
        this.disponisble = disponisble;
        this.dureeCreneau = dureeCreneau;
    }

	public String getIdCreneau() {
		return idCreneau;
	}

	public void setIdCreneau(String idCreneau) {
		this.idCreneau = idCreneau;
	}

	public String getIdPersonnel() {
		return idPersonnel;
	}

	public void setIdPersonnel(String idPersonnel) {
		this.idPersonnel = idPersonnel;
	}

	public Date getDateCreneau() {
		return dateCreneau;
	}

	public void setDateCreneau(Date dateCreneau) {
		this.dateCreneau = dateCreneau;
	}

	public int getDisponisble() {
		return disponisble;
	}

	public void setDisponisble(int disponisble) {
		this.disponisble = disponisble;
	}

	public int getDureeCreneau() {
		return dureeCreneau;
	}

	public void setDureeCreneau(int dureeCreneau) {
		this.dureeCreneau = dureeCreneau;
	}
}
