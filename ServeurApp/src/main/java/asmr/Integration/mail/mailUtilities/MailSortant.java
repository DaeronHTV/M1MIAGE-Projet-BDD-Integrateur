package asmr.Integration.mail.mailUtilities;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import asmr.dataUtilities.log.LogHelper;

public final class MailSortant extends MailCommun {
    protected Transport transport;

    /**{@inheritDoc}**/
    public MailSortant() {super();}

    /**{@inheritDoc}**/
    public MailSortant(String path, String connexionpath) {super(path, connexionpath);}

    /**
     * Initialise le transport avec l'instance de session créer
     */
    public Transport initTransport(){
        try
        {
            LogHelper.info("Création du transport smtp...");
            initSession();
            transport = session.getTransport("smtp");
        }
        catch(Exception e){ LogHelper.error("Erreur lors de l'initialisation du transport !", e); }
        return transport;
    }

    /**{@inheritDoc}**/
    @Override
    public boolean close(){
        boolean result = false;
        try 
        {
            transport.close();
            result = true;
        } 
        catch (MessagingException e) { LogHelper.error("Erreur lors de la fermeture du transport !", e); }
        return result;
    }

    /**{@inheritDoc}**/
    @Override
    public boolean connect(){
        boolean result = false;
        try 
        {
            transport.connect(UserConnexion.getProperty("mail.user"), UserConnexion.getProperty("mail.pwd"));
            result = true;
            LogHelper.info("Connexion établie !");
        } 
        catch (MessagingException e) { LogHelper.error("Erreur lors de la connection du transport !", e); }
        return result;
    }

    /**
     * Détermine si le transport est bien connecté
     * @return True si le transport est connecté
     */
    public boolean transportConnected(){ return transport != null ? transport.isConnected() : false; }

    /**
     * Envoie le message donnée en paramètre
     */
    public void sendMessage(MimeMessage message) {
        try { Transport.send(message);} 
        catch (MessagingException e) { LogHelper.warning("Erreur lors de l'envoie du message !", e); }
    }

    /**
     * Créer le MimeMessage qui sera envoyé par mail via les
     * properties donnée en paramètres
     * @param properties Les propriétés à intégrées dans le MimeMessage
     * @return Le message à envoyer
     */
    public MimeMessage createMessage(Properties properties){
        MimeMessage message = new MimeMessage(session);
        try
        {
            InternetAddress from = new InternetAddress(UserConnexion.getProperty("mail.user"), properties.getProperty("asmr.mail.senderName"));
            InternetAddress[] to = InternetAddress.parse(properties.getProperty("asmr.mail.to"));
            message.setFrom(from); 
            message.setRecipients(RecipientType.TO, to);
            message.setHeader("Content-Type", "text/html; charset=UTF-8");
            message.setSubject(properties.getProperty("asmr.mail.subject"), "UTF-8");
            message.setContent(properties.getProperty("asmr.mail.message"), "text/html; charset=UTF-8");
        }
        catch(Exception e)
        {
            LogHelper.warning("Erreur lors de la création du message !", e);
            message = null;
        }
        return message;
    }
}
