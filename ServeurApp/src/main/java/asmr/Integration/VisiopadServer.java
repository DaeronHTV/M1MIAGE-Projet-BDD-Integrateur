package asmr.Integration;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import asmr.Integration.mail.MailService;
import asmr.visioPadAPI.*;
/**
 * Classe principale qui initialise les différentes instances nécessaires et
 * lance le serveur
 * 
 * @author Mohammad Oubari
 */
public class VisiopadServer {
    private Server server;
    private Thread mail;

    void start() throws Exception {
        int maxThreads = 100;
        int minThreads = 10;
        int idleTimeout = 120;

        QueuedThreadPool threadPool = new QueuedThreadPool(maxThreads, minThreads, idleTimeout);
        server = new Server(threadPool);
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8090);
        server.setConnectors(new Connector[] { connector });
        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);
        servletHandler.addServletWithMapping(BlockingServlet.class, "/status");
        servletHandler.addServletWithMapping(EnregistrerRDVServlet.class, "/api/enregistrerRdv");
        servletHandler.addServletWithMapping(GetCreneauPersonnelServlet.class, "/api/creneauxPersonnel");
        servletHandler.addServletWithMapping(GetRDVContactServlet.class, "/api/rdvContactServlet");
        servletHandler.addServletWithMapping(GetResidentsContactServlet.class, "/api/residentsContact");
        servletHandler.addServletWithMapping(GetResidentsPersonnelServlet.class, "/api/residentsPersonnel");
        servletHandler.addServletWithMapping(CreerPersonnelServlet.class, "/api/creerPersonnel");
        servletHandler.addServletWithMapping(CreerContactServlet.class, "/api/creerContact");
        servletHandler.addServletWithMapping(ContactMailServlet.class, "/api/mail/contact");
        servletHandler.addServletWithMapping(CreateMailServlet.class, "/api/mail/create");
        servletHandler.addServletWithMapping(CreerCreneauServlet.class, "/api/creerCreneau");
        servletHandler.addServletWithMapping(GetCreneauRDVServlet.class, "/api/getCreneauRDV");
        servletHandler.addServletWithMapping(ValiderRDVServlet.class, "/api/validerRendezVous");
        servletHandler.addServletWithMapping(AnnulerRDVServlet.class, "/api/annulerRendezVous");
        servletHandler.addServletWithMapping(GetResidentServlet.class, "/api/resident");
        server.start();
        startMail();
    }

    void startMail() {
        MailService mService = MailService.getInstance();
        mService.setWaitTime(10000);
        mail = new Thread(mService);
        mail.setDaemon(true);
        mail.start();
    }

    void stop() throws Exception {
        System.out.println("Server stop");
        server.stop();
    }

    public static void main(String[] args) throws Exception {
        VisiopadServer server = new VisiopadServer();
         server.start();
    }

}
