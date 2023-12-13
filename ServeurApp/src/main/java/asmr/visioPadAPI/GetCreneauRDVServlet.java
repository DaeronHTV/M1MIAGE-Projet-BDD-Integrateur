package asmr.visioPadAPI;

import javax.servlet.http.HttpServlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import asmr.dataAccess.CreneauVisioDAO;
import asmr.dataAccess.UtilisateurDAO;
import asmr.visiopad.CreneauVisio;
import asmr.visiopad.Utilisateur;

public class GetCreneauRDVServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_OK);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		try {
            JsonArrayBuilder creneauxGlobal = Json.createArrayBuilder();
			JsonObjectBuilder creneauxJSON = Json.createObjectBuilder();
			JsonObjectBuilder creneauJSON = Json.createObjectBuilder();
			
			String mailPersonnel = request.getParameter("mailPersonnel");
			System.out.println("mail Perso : " + mailPersonnel);

			Utilisateur user = new Utilisateur();
			UtilisateurDAO utilisateurDao = new UtilisateurDAO();
			user = utilisateurDao.getByEmail(mailPersonnel);
			String idPersonnel = user.getIdPersonne();
			System.out.println("id Perso : " + idPersonnel);


            CreneauVisioDAO crVisio = new CreneauVisioDAO();
            CreneauVisio creneau = new CreneauVisio();
			ArrayList<CreneauVisio> crv = crVisio.getAllOfPersonnel(idPersonnel);
			System.out.println("crv : " + crv);


			for(int i=0; i<crv.size(); i++){
                creneau = crVisio.read(crv.get(i).getIdCreneau());
                creneauJSON.add("dureeCreneau",creneau.getDureeCreneau());
				creneauJSON.add("disponible",creneau.getDisponisble());
                creneauJSON.add("idPersonnel",creneau.getIdPersonnel());
                creneauJSON.add("idCreneau",creneau.getIdCreneau());
                SimpleDateFormat formatter1 = new SimpleDateFormat("dd-M-yyyy");  
                String creneauDate1 = formatter1.format(creneau.getDateCreneau());  
				creneauJSON.add("dateCreneau",creneauDate1);
				SimpleDateFormat formatter2 = new SimpleDateFormat("hh:mm:ss");  
                String creneauDate2 = formatter2.format(creneau.getDateCreneau());  
                creneauJSON.add("heureCreneau",creneauDate2);
                creneauxJSON.add("creneau",creneauJSON);
                creneauxGlobal.add(creneauxJSON);
			}
			String creneaux = creneauxGlobal.build().toString();
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().print(creneaux);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().println(e.toString());
		}
	}   
}
