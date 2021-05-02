package by.epam.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.web.controller.command.Command;
import by.epam.web.controller.command.FactoryCommand;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FactoryCommand factory = new FactoryCommand();

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public Controller() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse responce)
			throws ServletException, IOException {
		Command command = factory.getCommand(request.getParameter("command"));
		command.execute(request, responce);
	}
}