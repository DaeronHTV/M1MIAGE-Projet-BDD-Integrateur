package asmr.Integration.mail.mailUtilities;

import asmr.Routeur;
import asmr.dataUtilities.FileTools;

/**
 * Il s'agit d'une enumeration gardant en mémoire l'ensemble des données
 * sur les différents mails envoyés automatiquement par le service mail
 * afin de simplifier leur utilisation
 * @author Avanzino Aurélien
 * @since 29/01/2021
 */
public enum MailType {
    /**
     * Contient l'ensemble des informations relatifs au mail automatique envoyé 
     * lorsqu'un nouveau compte est créé par un utilisateur
     */
    ACCOUNTCREATE(FileTools.fileToString(Routeur.AccountCreate_msg), "VisioPad - Création de compte réussi !"),
    ACCOUNTVERIFIED(FileTools.fileToString(Routeur.AccountVerified_msg), "VisioPad - Compte vérifié !"),
    RAPPELRDV(FileTools.fileToString(Routeur.RendezVousPartial1_msg), "VisioPad - Rappel rendez-vous !"),
    CONTACT(FileTools.fileToString(Routeur.ContactPartial_msg), "Ticket Support");

    public interface Code{
        String ACCOUNTCREATE = "AccountCreate";
        String ACCOUNTVERIFIED = "AccountVerified";
        String RAPPELRDV = "RappelRdv";
        String CONTACT = "MailContact";
    }

    private String message;
    private String subject;
    
    /**
     * Cosntructeur par defaut d'un mail type
     * @param code Le code identifiant du message automatique
     */
    private MailType(String message, String subject){
        this.subject = subject;
        this.message = message;
    }

    /**
     * Le message formaté en html
     * @return The text and format of the message
     * @since 29/01/2021
     */
    public String message(){
        return this.message;
    }

    /**
     * Obtient le sujet du message sous la forme d'un string
     * @return Le sujet du message
     */
    public String subject(){
        return this.subject;
    }
}
