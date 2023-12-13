package asmr.visioPadAPI;

import javax.servlet.http.HttpServlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import asmr.dataAccess.InviteDAO;
import asmr.dataAccess.InviteRendezVousDAO;
import asmr.dataAccess.PersonneDAO;
import asmr.dataAccess.RendezVousDAO;
import asmr.dataAccess.ResidentDAO;
import asmr.dataAccess.UtilisateurDAO;
import asmr.visiopad.Invite;
import asmr.visiopad.InviteRendezVous;
import asmr.visiopad.Personne;
import asmr.visiopad.RendezVous;
import asmr.visiopad.Resident;
import asmr.visiopad.Utilisateur;

public class GetRDVContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            JsonArrayBuilder rdvGlobal = Json.createArrayBuilder();
            JsonObjectBuilder rdvJSON = Json.createObjectBuilder();

            Utilisateur user = new Utilisateur();
            String mailContact = request.getParameter("mailContact");
            UtilisateurDAO utilisateurDao = new UtilisateurDAO();
            user = utilisateurDao.getByEmail(mailContact);
            System.out.println("user : " + user.getAdresseMail());

            RendezVousDAO rDAO = new RendezVousDAO();
            ArrayList<RendezVous> lrdv = rDAO.getByContact(user.getIdPersonne());
            for(int i=0; i<lrdv.size(); i++){

                RendezVous rendezVous = rDAO.read(lrdv.get(i).getIdRendezVous());
                SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",Locale.ENGLISH);
                Date parsedDate = sdf.parse(new Date(rendezVous.getDateCreneau().getTime()).toString());
                Date parsedDate2 = sdf.parse(new Date(rendezVous.getDateCreneau().getTime()).toString());
                SimpleDateFormat print = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat print2 = new SimpleDateFormat("HH:mm");
                rdvJSON.add("dateRDV", print.format(parsedDate));
                rdvJSON.add("heureRDV", print2.format(parsedDate2));
				rdvJSON.add("etatRDV", rendezVous.getEtatRendezVous().toString());
                rdvJSON.add("statusRDV", rendezVous.getStatutRendezVous().toString());
                rdvJSON.add("comment", "" + rendezVous.getCommentaire());
                rdvJSON.add("avis",rendezVous.getAvisNbEtoile());

                ResidentDAO residentDAO = new ResidentDAO();
                Resident resident = residentDAO.read(rendezVous.getIdResident());
                PersonneDAO prResidentDAO = new PersonneDAO();
                Personne prResident = prResidentDAO.read(resident.getIdPersonne());
                rdvJSON.add("nomResident", prResident.getNom());
                rdvJSON.add("prenomResident", prResident.getPrenom());

                JsonArrayBuilder invitesGlobal = Json.createArrayBuilder();
                JsonObjectBuilder invitesJSON = Json.createObjectBuilder();
                JsonObjectBuilder inviteJSON = Json.createObjectBuilder();

                InviteRendezVousDAO inRdvDAO = new InviteRendezVousDAO();
                ArrayList<InviteRendezVous> lesInRdv = inRdvDAO.getByRendezVous(rendezVous.getIdRendezVous());
                for(int j=0; j<lesInRdv.size(); j++){
                    
                    InviteRendezVous inRv = lesInRdv.get(j);
                    PersonneDAO prInviteDAO = new PersonneDAO();
                    Personne personneInv = prInviteDAO.read(inRv.getIdInvite());
                    InviteDAO inviteDAO = new InviteDAO();
                    Invite userInvite = inviteDAO.read(inRv.getIdInvite());
                    inviteJSON.add("prenomInvite",personneInv.getPrenom());
                    inviteJSON.add("nomInvite", personneInv.getNom());
                    inviteJSON.add("emailInvite", userInvite.getAdresseMailInvite());
                    invitesJSON.add("invite",inviteJSON);
                    invitesGlobal.add(invitesJSON.build());
                }
                rdvJSON.add("Invites", invitesGlobal);
                rdvGlobal.add(rdvJSON.build());
            }

            String lesRendezVous = rdvGlobal.build().toString();
            System.out.println(lesRendezVous);
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().print(lesRendezVous);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().println(e.toString());
		}
	}   
}