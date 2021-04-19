package by.epam.web.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.web.controller.command.Command;
import by.epam.web.controller.constant.ConstantPage;
import by.epam.web.controller.constant.ConstantParameter;
import by.epam.web.service.ServiceProvider;
import by.epam.web.service.ServiceUser;


/**
 *This class is a redirect access to the main page to search for cars with the user's date.
 * 
 */


public class MainPageWithDateUser implements Command{
	
	private static final Logger log = Logger.getLogger(MainPageWithDateUser.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<String> dateUser = new ArrayList<String>();
		HttpSession session = request.getSession();
		
		if(session == null) {
			response.sendRedirect("Controller?command=error");
			log.info("The session is null");
			return;
		}
		
		String dateStart = request.getParameter(ConstantParameter.DATESTART); 
		String dateEnd = request.getParameter(ConstantParameter.DATEEND);
		
		ServiceProvider provider = ServiceProvider.getInstance();
		ServiceUser serviceUser = provider.getServiceUser();
		
		dateUser = serviceUser.getUserDate(dateStart, dateEnd);
		
		session.setAttribute(ConstantParameter.DATEUSER,dateUser);
		
		response.sendRedirect(ConstantPage.GOTOMAINPAGE);
	}
}
