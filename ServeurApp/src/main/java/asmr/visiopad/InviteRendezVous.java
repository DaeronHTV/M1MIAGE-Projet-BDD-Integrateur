package asmr.visiopad;

/**
 * Permet de représenter sous la forme d'une classe
 * les tuples ainsi que leur données provenant de la 
 * Table InviteRendezVous de la base de données
 * Cette table représente la relation entre Invite
 * et RendezVous
 * @author Mohammad Oubari
 */
public class InviteRendezVous {

    private String idInvite;
    private String idRendezVous;

    /**
     * Constructeur par Defaut
     */
    public InviteRendezVous(){
    }
    
    /**
     * Constructeur prenant en paramètre plusieurs string
     * afin d'initialiser l'instance
     * @param idInvite
     * @param idRendezVous
     */
    public InviteRendezVous(String idInvite, String idRendezVous){
        this.idInvite = idInvite;
        this.idRendezVous = idRendezVous;
    }

    public String getIdInvite() {
        return idInvite;
    }

    public void setIdInvite(String idInvite) {
        this.idInvite = idInvite;
    }

    public String getIdRendezVous() {
        return idRendezVous;
    }

    public void setIdRendezVous(String idRendezVous) {
        this.idRendezVous = idRendezVous;
    }
    
}
