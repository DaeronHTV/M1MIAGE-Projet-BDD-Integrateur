package asmr.Integration.mail;

import java.util.ArrayList;
import java.util.Properties;
import javax.mail.internet.MimeMessage;
import asmr.Routeur;
import asmr.dataUtilities.*;
import asmr.dataUtilities.log.LogHelper;
import asmr.Integration.mail.mailUtilities.MailType;
import asmr.Integration.mail.mailUtilities.MailSortant;
import asmr.annotation.Service;

/**
 * Service permettant 
 * @author Avanzino Aurélien
 * @implNote Implemente l'interface Runnable afin de permettre
 * à ce dernier de tourner en parallèle du reste du serveur
 * @since 27/01/2021
 */
@Service(name = "mailService")
public class MailService implements Runnable {
    private MailSortant mailS;
    private ArrayList<Properties> listeMail;
    private static volatile MailService instance = null;
    private int waitTime;

    /**
     * Constructeur par défaut du service
     * Les différents transport vont récupérer dans les
     * fichiers de config les données nécessaires à leur 
     * initialisation
     */
    public MailService() {
        this.mailS = new MailSortant();
        this.listeMail = new ArrayList<Properties>();
        this.waitTime = 5000;
    }

    public final static MailService getInstance(){
        if(MailService.instance == null){
            synchronized(MailService.class){
                if(MailService.instance == null){
                    instance = new MailService();
                }
            }
        }
        return instance;
    }

    public void setWaitTime(int millisecond){
        this.waitTime = millisecond;
    }


    /**
     * Envoie le message à la personne indiqué dans le MimeMessage
     * @param message Le message à envoyer
     */
    public void send(MimeMessage message) { mailS.sendMessage(message); }

    /**
     * Initialise les instances des différents transport mail
     */
    public void initTransport() {mailS.initTransport();}

    /**
     * Connecte l'ensemble des transport mail et renvoie un
     * boolean indiquant l'état des connexions
     * @return True si tous les transport sont bien connectés
     */
    public boolean connectTransport() {return mailS.connect();}

    /**
     * Ferme l'ensemble des transport mail et renvoie un boolean
     * indiquant l'état des fermetures
     * @return True si tous les transport sont bien fermés
     */
    public boolean closeTransport() {return mailS.close();}

    /**
     * {@inheritDoc}
     * @implNote Regarde dans la liste si des mails
     * sont à envoyer et repasse en boucle toutes les 5 secondes
     * pour vérifier si la liste n'a pas été de nouveau rempli
     */
    @Override
    public void run() {
        LogHelper.info("Initialisation du service Mail...");
        initTransport();connectTransport();
        while (true) {
            while (listeMail.size() > 0) {
                Properties prop = listeMail.get(0);
                MimeMessage message = mailS.createMessage(prop);
                send(message);
                listeMail.remove(0);
                LogHelper.info("Envoie reussi du mail " + prop.getProperty("asmr.mail.type"));
            }
            try { Thread.sleep(waitTime); } 
            catch (InterruptedException e) {LogHelper.error("Erreur lors du sommeil du Thread", e);}
        }
    }

    /**
     * Ajoute dans le liste des mails à envoyer, un mail de confirmation
     * de création de compte
     * @param adresse L'adresse de la personne dont le compte est vérifié
     * @param name Le nom de la personne dont le compte est vérifié
     */
    public void addAccountCreate(String adresse, String name) {
        Properties prop = new Properties();
        prop.put("asmr.mail.type", MailType.Code.ACCOUNTCREATE);
        prop.put("asmr.mail.sender", "");
        prop.put("asmr.mail.sendernName", "VisioPad");
        prop.put("asmr.mail.to", adresse);
        prop.put("asmr.mail.subject", MailType.ACCOUNTCREATE.subject());
        prop.put("asmr.mail.message", MailType.ACCOUNTCREATE.message());
        LogHelper.info("Ajout d'un mail dans la liste : " + MailType.Code.ACCOUNTCREATE);
        listeMail.add(prop);
    }

    /**
     * Ajoute dans la liste des mails à envoyer, un mail de validation 
     * du compte récemment créé
     * @param adresse L'adresse de la personne dont le compte est vérifié
     * @param name Le nom de la personne dont le compte est vérifié
     */
    public void addAccountVerified(String adresse, String name) {
        Properties prop = new Properties();
        prop.put("asmr.mail.type", MailType.Code.ACCOUNTVERIFIED);
        prop.put("asmr.mail.sender", "");
        prop.put("asmr.mail.sendernName", "VisioPad");
        prop.put("asmr.mail.to", adresse);
        prop.put("asmr.mail.subject", MailType.ACCOUNTVERIFIED.subject());
        prop.put("asmr.mail.message", MailType.ACCOUNTVERIFIED.message());
        LogHelper.info("Ajout d'un mail dans la liste : " + MailType.Code.ACCOUNTVERIFIED);
        listeMail.add(prop);    
    }

    /**
     * Ajoute dans la liste des mails à envoyer un mail de contact
     * @param adresse L'adresse de la personne ayant rédiger le message
     * @param name Le nom de la personne ayant rédiger le message
     * @param uSubject Le sujet du message
     * @param uMessage Le message de la personne
     * @param date la date d'ecriture du message
     */
    public void addContact(String adresse, String name, String uSubject, String uMessage, String date) {
        Properties prop = new Properties();
        String message = MailType.CONTACT.message();
        message += "<h4>Un message a été envoyé le " + date + " par " + name + "</h4>";
        message += "<p>" + uSubject + "</p>";
        message += "<p>" + uMessage + "</p>";
        message += "</section></main></body>";
        prop.put("asmr.mail.type", MailType.Code.CONTACT);
        prop.put("asmr.mail.sender", adresse);
        prop.put("asmr.mail.senderName", name);
        prop.put("asmr.mail.to", "visiopad@outlook.com");
        prop.put("asmr.mail.subject", MailType.CONTACT.subject());
        prop.put("asmr.mail.message", message);
        LogHelper.info("Ajout d'un mail dans la liste : " + MailType.Code.CONTACT);
        listeMail.add(prop);
    }

    /**
     * Ajoute dans la liste des mails, un mail de rappel
     * de rendez-vous à la personne indiquer dans les paramètres
     * @param adresse l'adresse du receveur
     * @param name le nom de receveur
     * @param date La date de la visioconférence
     * @param link Lien de la visioConférence
     */
    public void addRappelRDV(String adresse, String name, String date, String link) {
        Properties prop = new Properties();
        String part1 = MailType.RAPPELRDV.message();
        String part2 = FileTools.fileToString(Routeur.RendezVousPartial2_msg);
        part1 += "<li>Date : " + date + "</li>";
        part1 += "<li>Lien de la réunion : " + link + "</li>";
        prop.put("asmr.mail.type", MailType.Code.RAPPELRDV);
        prop.put("asmr.mail.sender", "visiopad@outlook.com");
        prop.put("asmr.mail.to", adresse);
        prop.put("asmr.mail.subject", MailType.RAPPELRDV.subject());
        prop.put("asmr.mail.message", part1 + part2);
        LogHelper.info("Ajout d'un mail dans la liste : " + MailType.Code.RAPPELRDV);
        listeMail.add(prop);
    }

    /*public static void main(String[] args) {
        MailService Mservice = MailService.getInstance();
        Mservice.setWaitTime(10000);

        Thread test = new Thread(Mservice);
        test.start();
        Mservice.addAccountCreate("aurelien.avanzino@etu.univ-grenoble-alpes.fr", "aurelien");
        Mservice.addAccountVerified("aurelien.avanzino@etu.univ-grenoble-alpes.fr", "aurelien");
        Mservice.addContact("aurelien.avanzino@etu.univ-grenoble-alpes.fr", "aurelien",
                "Probleme de mise en place des rendez-vous", "bonjour", "31/01/2021");
        Mservice.addRappelRDV("aurelien.avanzino@etu.univ-grenoble-alpes.fr", "aurelien", "01/01/2021 13h30",
                "https://visiopad.com");
        try {test.join();} 
        catch (InterruptedException e) {e.printStackTrace();}
    }*/
}
