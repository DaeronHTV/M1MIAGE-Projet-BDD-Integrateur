package asmr;

/**
 * Permet de définir les chemins constants des fichiers de configuration
 * et les fichiers html des mails automatiques
 * @author Avanzino Aurélien
 * @since 29/01/2021
 */
public final class Routeur {
    @Deprecated
    public static final String Contact_msg = "data/MailTypeHtml/Contact.html";
    @Deprecated
    public static final String MailSConfig = "data/config/MailSConnections.xml";

    public static final String MailConfig = "data/config/MailConnections.xml";
    public static final String BDDConfig = "data/config/ConnectioString.xml";
    public static final String AccountCreate_msg = "data/MailTypeHtml/ConfirmationInscription.html";
    public static final String AccountVerified_msg = "data/MailTypeHtml/CompteValide.html";
    public static final String ContactPartial_msg = "data/MailTypeHtml/partial/ContactPartial.html";
    public static final String AccountConnexion = "data/config/MailAccount.xml";
    public static final String RendezVousPartial1_msg="data/MailTypeHtml/partial/RendezVousPartial.html";
    public static final String RendezVousPartial2_msg="data/MailTypeHtml/partial/RendezVousPartial2.html";
}
