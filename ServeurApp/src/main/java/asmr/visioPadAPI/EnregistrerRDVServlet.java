package asmr.visioPadAPI;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import asmr.dataAccess.CreneauVisioDAO;
import asmr.dataAccess.DAOFactory;
import asmr.dataAccess.InviteDAO;
import asmr.dataAccess.PersonneDAO;
import asmr.dataAccess.RendezVousDAO;
import asmr.dataAccess.SqlDAO;
import asmr.dataAccess.UtilisateurDAO;
import asmr.visiopad.CreneauVisio;
import asmr.visiopad.Invite;
import asmr.visiopad.InviteRendezVous;
import asmr.visiopad.Personne;
import asmr.visiopad.RendezVous;
import asmr.visiopad.Utilisateur;
import asmr.visiopad.enumeration.EtatRDV;
import asmr.visiopad.enumeration.StatusRDV;

public class EnregistrerRDVServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_OK);
		response.setHeader("Access-Control-Allow-Origin", "*");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			String contactMail = request.getParameter("contactMail");
			UtilisateurDAO userDAO = new UtilisateurDAO();
			Utilisateur user = userDAO.getByEmail(contactMail);
			String contactId = user.getIdPersonne();
			String invitesJson = request.getParameter("invites");
			JSONParser parser = new JSONParser();
			JSONArray invites = (JSONArray) parser.parse(invitesJson);
			String idPersonnel = request.getParameter("idPersonnel");
			System.out.println("Personnel associé : " + idPersonnel);
			String idCreneau = request.getParameter("idCreneau");
			CreneauVisioDAO crDAO = new CreneauVisioDAO();
			CreneauVisio cr = crDAO.read(idCreneau);
			String idResident = request.getParameter("idResident");

			RendezVous rdv = new RendezVous.RendezVousBuilder(null, idResident, idCreneau, idPersonnel, contactId)
			.dateCreneau(cr.getDateCreneau())
			.etatRendezVous(EtatRDV.PROGRAMME)
			.statutRendezVous(StatusRDV.NORMAL)
			.avisNbEtoile(0).commentaire(null).heureDebutRDV(null).heureFinRDV(null).idTablette(null).build();

			RendezVousDAO rdvDAO = new RendezVousDAO();
			rdvDAO.create(rdv);
			cr.setDisponisble(0);
			crDAO.update(cr);
			rdv = rdvDAO.getByCreneau(idCreneau);

			if(invites.size() > 0 ) {
				for(int i=0; i<invites.size(); i++) {
					JSONObject invite = (JSONObject) invites.get(i);
					PersonneDAO perDAO = new PersonneDAO();
					Personne personne = new Personne(null, invite.get("prenom").toString(), invite.get("nom").toString());
					String idPersonne = perDAO.createPersonneAutoGUID(personne);
					Invite inv = new Invite(idPersonne, invite.get("prenom").toString(), invite.get("nom").toString(), invite.get("email").toString());
					InviteDAO invDAO = new InviteDAO();
					invDAO.create(inv);
					SqlDAO<InviteRendezVous> invRdvDAO = DAOFactory.getInviteRendezVousDAO();
					InviteRendezVous invRdv = new InviteRendezVous(idPersonne, rdv.getIdRendezVous());
					invRdvDAO.create(invRdv);
				}
			}
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().print(true);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().println(e.toString());
		}
	}   
}
