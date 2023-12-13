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
import asmr.dataAccess.ResidentDAO;
import asmr.visiopad.CreneauVisio;
import asmr.visiopad.Resident;

public class GetCreneauPersonnelServlet extends HttpServlet {
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
            JsonArrayBuilder creneauxGlobal = Json.createArrayBuilder();
			JsonObjectBuilder creneauxJSON = Json.createObjectBuilder();
            JsonObjectBuilder creneauJSON = Json.createObjectBuilder();
			String residentId = request.getParameter("idResident");
            ResidentDAO residentDAO = new ResidentDAO();
			Resident resident = residentDAO.read(residentId);
			String idPersonnel = resident.getIdPersonnel();
            CreneauVisioDAO crVisio = new CreneauVisioDAO();
			CreneauVisio creneau = new CreneauVisio();
			ArrayList<CreneauVisio> crv = crVisio.getAllOfPersonnel(idPersonnel);	
			for(int i=0; i<crv.size(); i++){
                creneau = crVisio.read(crv.get(i).getIdCreneau());
                creneauJSON.add("dureeCreneau",creneau.getDureeCreneau());
				creneauJSON.add("disponible",creneau.getDisponisble());
                creneauJSON.add("idPersonnel",creneau.getIdPersonnel());
                creneauJSON.add("idCreneau",creneau.getIdCreneau());
                SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");  
                String creneauDate = formatter.format(creneau.getDateCreneau());  
                creneauJSON.add("dateCreneau",creneauDate);
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
