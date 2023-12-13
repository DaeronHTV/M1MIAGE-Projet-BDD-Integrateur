package asmr.visioPadAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import asmr.dataAccess.EtablissementDAO;
import asmr.dataAccess.PersonneDAO;
import asmr.dataAccess.ResidentDAO;
import asmr.dataAccess.UniteDAO;
import asmr.visiopad.Etablissement;
import asmr.visiopad.Personne;
import asmr.visiopad.Resident;
import asmr.visiopad.Unite;
import asmr.visiopad.enumeration.StatutResident;

public class GetResidentServlet extends HttpServlet {
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
			String prenom = request.getParameter("prenom");
            String nom = request.getParameter("nom");
            String dateNaissance = request.getParameter("dateNaissance");
            System.out.println(dateNaissance);
            Date dateNaissanceRes = Date.valueOf(dateNaissance);
            String etablissement = request.getParameter("etablissement");
            EtablissementDAO eDAO = new EtablissementDAO();
            Etablissement etab = eDAO.getByName(etablissement);
            ResidentDAO resDAO = new ResidentDAO();
            PersonneDAO perDAO = new PersonneDAO();
            
            Boolean exist = false;
            UniteDAO uDAO = new UniteDAO();
            ArrayList<Unite> unites = uDAO.getAllByEtablissement(etab.getId());
            for(int i=0; i<unites.size(); i++) {
                Unite unite = unites.get(i);
                Resident resident = new Resident(null, prenom, nom, unite.getIdUnite(), dateNaissanceRes, StatutResident.DISPONIBLE, "");
                ArrayList<Resident> resList = resDAO.getResidentWithBirthday(resident);
                for (int j=0; j<resList.size(); j++) {
                    Personne resPersonne= perDAO.read(resList.get(j).getIdPersonne());
                    if(resPersonne.getNom().equals(nom) && resPersonne.getPrenom().equals(prenom) && resList.get(j).getIdUnite().equals(resident.getIdUnite())) {
                        exist = true;
                        break;
                    }
                }
                if (exist.equals(true)){
                    break;
                }
            }
            System.out.println("le client existe : " + exist);
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().print(exist);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().println(e.toString());
		}
	}   
}
