package asmr.visioPadAPI;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import asmr.Integration.mail.MailService;
import asmr.dataAccess.PersonneDAO;
import asmr.dataAccess.PersonnelDAO;
import asmr.dataAccess.UtilisateurDAO;
import asmr.visiopad.Personne;
import asmr.visiopad.Personnel;
import asmr.visiopad.Utilisateur;

public class CreerPersonnelServlet extends HttpServlet {
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
			String nom = request.getParameter("nom");
            System.out.println(nom);
			String prenom = request.getParameter("prenom");
            System.out.println(prenom);
			String adresseMail = request.getParameter("adresseMail");
			System.out.println(adresseMail);
			String numeroTelephone = request.getParameter("numeroTelephone");
			System.out.println(numeroTelephone);
			String fonction = request.getParameter("fonction");
            System.out.println(fonction);
			MailService mService = MailService.getInstance();
			mService.addAccountCreate(adresseMail, nom +" "+ prenom);

            PersonneDAO personneDAO = new PersonneDAO();
            Personne personne = new Personne(null, prenom, nom);
            String idPersonne = personneDAO.createPersonneAutoGUID(personne);

            UtilisateurDAO userDAO = new UtilisateurDAO();
            Utilisateur user = new Utilisateur(idPersonne, prenom, nom, numeroTelephone, adresseMail);
            userDAO.create(user);

            PersonnelDAO personnelDAO = new PersonnelDAO();
            Personnel personnel = new Personnel(idPersonne, prenom, nom, numeroTelephone, adresseMail, fonction);
            personnelDAO.create(personnel);

			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().print(true);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().println(e.toString());
		}
	}   
}
