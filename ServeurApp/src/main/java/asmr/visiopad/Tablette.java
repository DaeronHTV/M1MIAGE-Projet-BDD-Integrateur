package asmr.visiopad;

import asmr.visiopad.enumeration.EtatTablette;

/**
 * Permet de représenter sous la forme d'une classe
 * les tuples ainsi que leur données provenant de la 
 * Table Tablette de la base de données
 * @author Mohammad Oubari
 */
public class Tablette {
    
    private String idTablette;
    private EtatTablette etatTablette;
    
    /**
     * Constructeur par Defaut
     */
    public Tablette() {
    }

    /**
     * Constructeur prenant en paramètre plusieurs string
     * afin d'initialiser l'instance
     * @param idTablette
     * @param etatTablette
     */
    public Tablette(String idTablette, EtatTablette etatTablette) {
        this.idTablette = idTablette;
        this.etatTablette = etatTablette;
    }

    public String getIdTablette() {
        return idTablette;
    }

    public void setIdTablette(String idTablette) {
        this.idTablette = idTablette;
    }

    public EtatTablette getEtatTablette() {
        return etatTablette;
    }

    public void setEtatTablette(EtatTablette etatTablette) {
        this.etatTablette = etatTablette;
    }
}
