package asmr.visioPadAPI;

import javax.servlet.http.HttpServlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import asmr.dataAccess.CreneauVisioDAO;
import asmr.dataAccess.RendezVousDAO;
import asmr.dataUtilities.log.LogHelper;
import asmr.visiopad.CreneauVisio;
import asmr.visiopad.RendezVous;
import asmr.visiopad.enumeration.EtatRDV;
import asmr.visiopad.enumeration.StatusRDV;

public class AnnulerRDVServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
		response.setStatus(HttpServletResponse.SC_OK);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			String idCreneau = request.getParameter("idCreneau");
            CreneauVisioDAO crDAO = new CreneauVisioDAO();
            CreneauVisio cr = crDAO.read(idCreneau);
            RendezVousDAO rDAO = new RendezVousDAO();
            RendezVous rd = new RendezVous();
            rd = rDAO.getByCreneau(cr.getIdCreneau());
            rd.setStatutRendezVous(StatusRDV.NORMAL);
            rd.setEtatRendezVous(EtatRDV.ANNULE);
            rDAO.update(rd);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().print(true);
		} catch (Exception e) {
			LogHelper.error(e);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().println(e.toString());
		}
    }  
}
