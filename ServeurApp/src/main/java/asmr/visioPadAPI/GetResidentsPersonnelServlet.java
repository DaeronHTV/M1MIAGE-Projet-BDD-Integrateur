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
import java.text.SimpleDateFormat;

import asmr.dataAccess.ResidentDAO;
import asmr.dataAccess.PersonneDAO;
import asmr.dataAccess.PersonnelResidentDAO;
import asmr.dataAccess.UniteDAO;
import asmr.dataAccess.UtilisateurDAO;
import asmr.visiopad.Resident;
import asmr.visiopad.Personne;
import asmr.visiopad.PersonnelResident;
import asmr.visiopad.Unite;
import asmr.visiopad.Utilisateur;

public class GetResidentsPersonnelServlet extends HttpServlet {
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
			String contactMail = request.getParameter("contactMail");
			System.out.println("contactMail : " + contactMail);
			UtilisateurDAO userDAO = new UtilisateurDAO();
			Utilisateur user = userDAO.getByEmail(contactMail);
			PersonnelResidentDAO personnelResidentDAO = new PersonnelResidentDAO();
			ArrayList<PersonnelResident> persoRes = new ArrayList<PersonnelResident>();
			System.out.println("id : " +user.getIdPersonne());
			persoRes = personnelResidentDAO.getByPersonnel(user.getIdPersonne());
			System.out.println("persoRes : " + persoRes);
			ResidentDAO rDAO = new ResidentDAO();
			PersonneDAO pDAO = new PersonneDAO();
			for(int i=0; i<persoRes.size(); i++){
				Resident resident = rDAO.read(persoRes.get(i).getIdResident());
				Personne residentPersonne = pDAO.read(persoRes.get(i).getIdResident());
				residentDataJSON.add("prenom", residentPersonne.getPrenom());
				residentDataJSON.add("nom",residentPersonne.getNom());
				SimpleDateFormat formatter = new SimpleDateFormat("dd/M/yy");  
                String dateNaissance = formatter.format(resident.getDateNaissanceResident());  
				residentDataJSON.add("dateNaissance",dateNaissance);
				residentDataJSON.add("statut",resident.getStatutResident().toString());
				String idUnite = resident.getIdUnite();
				UniteDAO udao = new UniteDAO();
				Unite unite = udao.read(idUnite);
				residentDataJSON.add("uniteAff",unite.getNomUnite());
				residentDataJSON.add("idResident",persoRes.get(i).getIdResident());
				residentsGlobal.add(residentDataJSON.build());
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
