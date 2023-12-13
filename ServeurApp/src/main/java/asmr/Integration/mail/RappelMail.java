package asmr.Integration.mail;

import asmr.annotation.Service;

/**
 * Classe permettant la gestion des envoies de mails
 * de rappel aux différents utilisateurs
 * Possède un lien indirect avec la base de données
 * @implSpec Respecte le design pattern Singleton
 * @implNote Implémente Runnable pour pouvoir être lancé
 * en parallèle du serveur et service Mail
 * @author Avanzino Aurélien
 */
@Service(name = "RappelMailService")
public class RappelMail implements Runnable{
    private static volatile RappelMail instance = null;
    private int waitTime;

    private RappelMail(){

    }

    public static final RappelMail getInstance(){
        if(RappelMail.instance == null){
            synchronized(RappelMail.class){
                if(RappelMail.instance == null){
                    instance = new RappelMail();
                }
            }
        }
        return instance;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
    }
    
}
