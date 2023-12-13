package asmr.visioPadAPI;

import java.io.IOException;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import asmr.dataAccess.ContactResidentDAO;
import asmr.dataAccess.PersonneDAO;
import asmr.dataAccess.UtilisateurDAO;
import asmr.visiopad.ContactResident;
import asmr.visiopad.Personne;
import asmr.visiopad.Utilisateur;

public class GetResidentsContactServlet extends HttpServlet {
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
			JsonArrayBuilder residentsGlobal = Json.createArrayBuilder();
			JsonObjectBuilder residentDataJSON = Json.createObjectBuilder();
			JsonObjectBuilder residentJSON = Json.createObjectBuilder();
			String contactMail = request.getParameter("contactMail");
			System.out.println(contactMail);
			UtilisateurDAO userDAO = new UtilisateurDAO();
			Utilisateur user = userDAO.getByEmail(contactMail);
			String contactId = user.getIdPersonne();
			System.out.println(contactId);
			ContactResidentDAO contactResidentDAO = new ContactResidentDAO();
			ArrayList<ContactResident> contactRes = contactResidentDAO.getAllOfContact(contactId);
			Personne resident = new Personne();
			PersonneDAO personneDAO = new PersonneDAO();
			for(int i=0; i<contactRes.size(); i++){
				resident = personneDAO.read(contactRes.get(i).getIdResident());
				residentDataJSON.add("prenom", resident.getPrenom());
				residentDataJSON.add("nom",resident.getNom());
				residentDataJSON.add("idResident",resident.getIdPersonne());
				residentJSON.add("resident", residentDataJSON);
				residentsGlobal.add(residentJSON);
			}
			String residents = residentsGlobal.build().toString();
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().print(residents);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().println(e.toString());
		}
	}   
}
