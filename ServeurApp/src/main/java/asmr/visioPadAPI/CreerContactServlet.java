package asmr.visioPadAPI;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import asmr.Integration.mail.MailService;
import asmr.dataAccess.ContactDAO;
import asmr.dataAccess.ContactResidentDAO;
import asmr.dataAccess.EtablissementDAO;
import asmr.dataAccess.PersonneDAO;
import asmr.dataAccess.ResidentDAO;
import asmr.dataAccess.UniteDAO;
import asmr.dataAccess.UtilisateurDAO;
import asmr.dataUtilities.log.LogHelper;
import asmr.visiopad.Contact;
import asmr.visiopad.ContactResident;
import asmr.visiopad.Etablissement;
import asmr.visiopad.Personne;
import asmr.visiopad.Resident;
import asmr.visiopad.Unite;
import asmr.visiopad.Utilisateur;
import asmr.visiopad.enumeration.StatutResident;

public class CreerContactServlet extends HttpServlet {
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
			String adresseMail = request.getParameter("mail");
			System.out.println(adresseMail);
			String numeroTelephone = request.getParameter("telephone");
			System.out.println(numeroTelephone);
            String verified = request.getParameter("verified");
            int compteValide = (verified == "false") ? 0 : 1; 
			System.out.println(verified);

			PersonneDAO personneDAO = new PersonneDAO();
            Personne personne = new Personne(null, prenom, nom);
            String idPersonne = personneDAO.createPersonneAutoGUID(personne);
			System.out.println("Création Contact : " + idPersonne);
            UtilisateurDAO userDAO = new UtilisateurDAO();
            Utilisateur user = new Utilisateur(idPersonne, prenom, nom, numeroTelephone, adresseMail);
            userDAO.create(user);

			ContactDAO contactDAO = new ContactDAO();
            Contact contact = new Contact(idPersonne, prenom, nom, numeroTelephone, adresseMail, compteValide);
			Boolean created = contactDAO.create(contact);

			String residents = request.getParameter("resident");
			if(!residents.equals(null)){

				JSONParser parser = new JSONParser();
				System.out.println(residents);
				JSONObject residentsJSON = (JSONObject) parser.parse(residents);	
				String nomResident = residentsJSON.get("nom").toString();
				String prenomResident = residentsJSON.get("prenom").toString();
				String dateNaissance = residentsJSON.get("dateNaissance").toString();
				System.out.println(dateNaissance);
				Date dateNaissanceResident = Date.valueOf(dateNaissance);
				String etablissement = residentsJSON.get("etablissement").toString();
				EtablissementDAO eDAO = new EtablissementDAO();
				Etablissement etab = eDAO.getByName(etablissement);
	
				ResidentDAO resDAO = new ResidentDAO();
				PersonneDAO perDAO = new PersonneDAO();
				Boolean exist = false;
				UniteDAO uDAO = new UniteDAO();
				ArrayList<Unite> unites = uDAO.getAllByEtablissement(etab.getId());
				
				for(int i=0; i<unites.size(); i++) {
					Unite unite = unites.get(i);
					Resident resident = new Resident(null, prenomResident, nomResident, unite.getIdUnite(), dateNaissanceResident, StatutResident.DISPONIBLE, "");
					ArrayList<Resident> resList = resDAO.getResidentWithBirthday(resident);
					for (int j=0; j<resList.size(); j++) {
						Personne resPersonne= perDAO.read(resList.get(j).getIdPersonne());
						if(resPersonne.getNom().equals(nomResident) && resPersonne.getPrenom().equals(prenomResident) && resList.get(j).getIdUnite().equals(resident.getIdUnite())) {
							ContactResidentDAO crDAO = new ContactResidentDAO();
							ContactResident cr = new ContactResident(idPersonne, resPersonne.getIdPersonne());
							crDAO.create(cr);
							exist = true;
							break;
						}
					}
					if (exist.equals(true)){
						break;
					}
				}
				System.out.println("le résident existe : " + exist);
			}
			MailService mService = MailService.getInstance();
			mService.addAccountVerified(adresseMail, nom + " " + prenom);
			if (created == false) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.getWriter().print(false);
			} else {
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().print(true);
			}
		} catch (Exception e) {
			LogHelper.error(e);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().println(e.toString());
		}
	}   
}
