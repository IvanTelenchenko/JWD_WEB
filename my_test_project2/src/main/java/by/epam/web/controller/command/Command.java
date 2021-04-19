package by.epam.web.controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This interface gets the command name from the user request and
accomplish this.
 * 
 * */

public interface Command {
	
	/**
	 * This method executes the command
	 * @param requset {@link HttpServletRequest }
	 * @param response {@link HttpServletResponse }
	 * 
	 */
	
	void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
