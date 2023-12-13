package asmr.visiopad;

import asmr.visiopad.enumeration.EtatUnite;

/**
 * Permet de représenter sous la forme d'une classe
 * les tuples ainsi que leur données provenant de la 
 * Table Unite de la base de données
 * @author Mohammad Oubari
 */
public class Unite {

    private String idUnite;
    private String idEtablissement;
    private String nomUnite;
    private EtatUnite etatUnite;

    /**
     * Constructeur par Defaut
     */
    public Unite() {
    }

    /**
     * Constructeur prenant en paramètre plusieurs string
     * afin d'initialiser l'instance
     * @param idUnite
     * @param idEtablissement
     * @param nomUnite
     * @param etatUnite
     */
    public Unite(String idUnite, String idEtablissement, String nomUnite, EtatUnite etatUnite) {
        this.idUnite = idUnite;
        this.idEtablissement = idEtablissement;
        this.nomUnite = nomUnite;
        this.etatUnite = etatUnite;
    }

    public String getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(String idUnite) {
        this.idUnite = idUnite;
    }

    public String getIdEtablissement() {
        return idEtablissement;
    }

    public void setIdEtablissement(String idEtablissement) {
        this.idEtablissement = idEtablissement;
    }

    public String getNomUnite() {
        return nomUnite;
    }

    public void setNomUnite(String nomUnite) {
        this.nomUnite = nomUnite;
    }

    public EtatUnite getEtatUnite() {
        return etatUnite;
    }

    public void setEtatUnite(EtatUnite etatUnite) {
        this.etatUnite = etatUnite;
    }

}
