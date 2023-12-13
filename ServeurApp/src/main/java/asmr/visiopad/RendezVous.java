package asmr.visiopad;

import java.util.Date;

import asmr.visiopad.enumeration.EtatRDV;
import asmr.visiopad.enumeration.StatusRDV;

/**
 * Permet de représenter sous la forme d'une classe
 * les tuples ainsi que leur données provenant de la 
 * Table RendezVous de la base de données
 * @author Mohammad Oubari
 * @implNote cette classe respecte le design pattern Builder
 */
public class RendezVous {

    private String idRendezVous;
    private String idContact;
    private String idResident;
    private String idTablette;
    private String idCreneau;
    private String idRemplacant;
    private Date dateCreneau;
    private String heureDebutRDV;
    private String heureFinRDV; 
    private StatusRDV statutRendezVous;
    private EtatRDV etatRendezVous;
    private String commentaire;
    private int avisNbEtoile;

    /**
     * Constructeur par Defaut
     */
    public RendezVous(){
    }

    /**
     * Constructeur prenant en paramètre plusieurs string
     * afin d'initialiser l'instance
     * @param builder
     */
    private RendezVous(RendezVousBuilder builder) {
        this.idRendezVous = builder.idRendezVous;
        this.idCreneau = builder.idCreneau;
        this.idResident = builder.idResident;
        this.idTablette = builder.idTablette;
        this.idRemplacant = builder.idRemplacant;
        this.dateCreneau = builder.dateCreneau;
        this.heureDebutRDV = builder.heureDebutRDV;
        this.heureFinRDV = builder.heureFinRDV;
        this.statutRendezVous = builder.statutRendezVous;
        this.etatRendezVous = builder.etatRendezVous;
        this.commentaire = builder.commentaire;
        this.avisNbEtoile = builder.avisNbEtoile;
        this.idContact = builder.idContact;
    }

    public String getIdRendezVous() {
        return idRendezVous;
    }

    public void setIdRendezVous(String idRendezVous) {
        this.idRendezVous = idRendezVous;
    }

    public String getIdContact() {
        return idContact;
    }

    public void setIdContact(String idContact) {
        this.idContact = idContact;
    }

    public String getIdResident() {
        return idResident;
    }

    public void setIdResident(String idResident) {
        this.idResident = idResident;
    }

    public String getIdTablette() {
        return idTablette;
    }

    public void setIdTablette(String idTablette) {
        this.idTablette = idTablette;
    }

    public String getIdCreneau() {
        return idCreneau;
    }

    public void setIdCreneau(String idCreneau) {
        this.idCreneau = idCreneau;
    }

    public String getIdRemplacant() {
        return idRemplacant;
    }

    public void setIdRemplacant(String idRemplacant) {
        this.idRemplacant = idRemplacant;
    }

    public Date getDateCreneau() {
        return dateCreneau;
    }

    public void setDateCreneau(Date dateCreneau) {
        this.dateCreneau = dateCreneau;
    }

    public String getHeureDebutRDV() {
        return heureDebutRDV;
    }

    public void setHeureDebutRDV(String heureDebutRDV) {
        this.heureDebutRDV = heureDebutRDV;
    }

    public String getHeureFinRDV() {
        return heureFinRDV;
    }

    public void setHeureFinRDV(String heureFinRDV) {
        this.heureFinRDV = heureFinRDV;
    }

    public StatusRDV getStatutRendezVous() {
        return statutRendezVous;
    }

    public void setStatutRendezVous(StatusRDV statutRendezVous) {
        this.statutRendezVous = statutRendezVous;
    }

    public EtatRDV getEtatRendezVous() {
        return etatRendezVous;
    }

    public void setEtatRendezVous(EtatRDV etatRendezVous) {
        this.etatRendezVous = etatRendezVous;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getAvisNbEtoile() {
        return avisNbEtoile;
    }

    public void setAvisNbEtoile(int avisNbEtoile) {
        this.avisNbEtoile = avisNbEtoile;
    }

    public static class RendezVousBuilder 
    {
        private String idRendezVous;
        private String idContact;
        private String idResident;
        private String idTablette;
        private String idCreneau;
        private String idRemplacant;
        private Date dateCreneau;
        private String heureDebutRDV;
        private String heureFinRDV; 
        private StatusRDV statutRendezVous;
        private EtatRDV etatRendezVous;
        private String commentaire;
        private int avisNbEtoile;

        public RendezVousBuilder(String idRendezVous, String idResident, String idCreneau, String idRemplacant, String idContact) {
            this.idRendezVous = idRendezVous;
            this.idResident = idResident;
            this.idCreneau = idCreneau;
            this.idRemplacant = idRemplacant;
            this.idContact = idContact;
        }
        public RendezVousBuilder dateCreneau(Date dateCreneau) {
            this.dateCreneau = dateCreneau;
            return this;
        }
        public RendezVousBuilder heureDebutRDV(String heureDebutRDV) {
            this.heureDebutRDV = heureDebutRDV;
            return this;
        }
        public RendezVousBuilder heureFinRDV(String heureFinRDV) {
            this.heureFinRDV = heureFinRDV;
            return this;
        }
        public RendezVousBuilder statutRendezVous(StatusRDV statutRendezVous) {
            this.statutRendezVous = statutRendezVous;
            return this;
        }
        public RendezVousBuilder etatRendezVous(EtatRDV etatRendezVous) {
            this.etatRendezVous = etatRendezVous;
            return this;
        }
        public RendezVousBuilder commentaire(String commentaire) {
            this.commentaire = commentaire;
            return this;
        }
        public RendezVousBuilder avisNbEtoile(int avisNbEtoile) {
            this.avisNbEtoile = avisNbEtoile;
            return this;
        }
        public RendezVousBuilder idTablette(String idTablette) {
            this.idTablette = idTablette;
            return this;
        }
        public RendezVous build() {
            RendezVous rdv =  new RendezVous(this);
            return rdv;
        }
    }
}
