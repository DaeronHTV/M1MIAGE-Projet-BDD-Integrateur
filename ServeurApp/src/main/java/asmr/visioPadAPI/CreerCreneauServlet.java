package asmr.visioPadAPI;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import asmr.dataAccess.DAOFactory;
import asmr.dataAccess.SqlDAO;
import asmr.dataAccess.UtilisateurDAO;
import asmr.dataUtilities.log.LogHelper;
import asmr.visiopad.CreneauVisio;
import asmr.visiopad.Utilisateur;

public class CreerCreneauServlet extends HttpServlet{
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
            String mail = request.getParameter("mailPersonnel");
            String date = request.getParameter("dateCreneau");
			Date dateCreneau = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(date); 
			UtilisateurDAO userDAO = new UtilisateurDAO();
			System.out.println(mail);
			Utilisateur user = userDAO.getByEmail(mail);
			System.out.println(user.getAdresseMail());			
            SqlDAO<CreneauVisio> crDAO = DAOFactory.getCreneauVisioDAO();
			CreneauVisio crVisio = new CreneauVisio(null, user.getIdPersonne(), dateCreneau, 1, 30);
			Boolean created = crDAO.create(crVisio);
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
