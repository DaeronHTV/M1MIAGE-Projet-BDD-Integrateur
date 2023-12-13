package asmr.visioPadAPI;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import asmr.Integration.mail.MailService;
import asmr.dataUtilities.log.LogHelper;

public class CreateMailServlet extends HttpServlet{
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
			String name = request.getParameter("nom") + request.getParameter("prenom");
            String adresse = request.getParameter("mail");
            MailService ms = MailService.getInstance();
            ms.addAccountCreate(adresse, name);

			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().print("Le mail de contact a été ajouté à la liste...");
		} catch (Exception e) {
            LogHelper.error(e);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().println(e.toString());
		}
	}   
}
