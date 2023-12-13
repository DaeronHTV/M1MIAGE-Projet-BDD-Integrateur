package asmr.Integration.mail.mailUtilities;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import asmr.Routeur;
import asmr.dataUtilities.XMLTools;
import asmr.dataUtilities.log.LogHelper;

/**
 * Classe commune à l'ensemble des transports mails
 * Contient l'ensemble des fonctions communes
 * @author Avanzino Aurélien
 */
public abstract class MailCommun {
    private Properties properties;
    protected Properties UserConnexion;
    protected Session session;

    /**
     * Constructor par defaut du transport
     * @see MailCommun#MailCommun()
     */
    public MailCommun() {this(Routeur.MailConfig, Routeur.AccountConnexion);}

    /**
     * Constructor prenant en paramètre des données de connexion personnalisé
     * @see MailCommun#MailCommun(String, String)
     */
    public MailCommun(String path, String connexionpath) {
        try 
        {
            properties = (Properties) XMLTools.decodeFromFile(path);
            UserConnexion = (Properties)XMLTools.decodeFromFile(connexionpath);
        } 
        catch (IOException ioe) {LogHelper.error("Erreur lors de la récupération des paramètres de connexion !", ioe);}
    }

    /**
     * L'ensemble des propriétés de connexion du transport mail
     * @return Les propriétés de connexion
     */
    public Properties getProperties() {return properties;}

    /**
     * Retourne l'instance de session utilisé par le
     * transport mail
     * @implSpec La session est commune pour tous les transports
     * @return la session de connexion
     */
    public Session getSession(){return session;}

    /**
     * Initialise la session de connexion qui sera utilisé
     * par les transports
     * @implSpec La session est commune pour tous les transports
     * @return La session de connexion initialisé
     */
    public Session initSession(){
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(UserConnexion.getProperty("mail.user"), UserConnexion.getProperty("mail.pwd"));
            }
        };
        session = Session.getInstance(properties, auth);
        return session;
    }

    /**
     * Connecte le transport à l'instance de Session
     * @return True si la connexion a été établie
     */
    public abstract boolean connect();
    
    /**
     * Déconnecte le transport à l'instance de Session 
     * @return True la connexion est bien fermée
     */
    public abstract boolean close();
}
