package de.yetanothercalendar.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.yetanothercalendar.model.dao.UserDAO;
import de.yetanothercalendar.model.dao.impl.UserDAOImpl;
import de.yetanothercalendar.model.database.User;
import de.yetanothercalendar.model.database.helper.DatabaseConnectionManager;

/**
 * Controller Klasse für {@link de.yetanothercalendar.model.database.User}.
 */
public class UserServlet extends HttpServlet {

	private UserDAO dao;

	public UserServlet() {
		dao = new UserDAOImpl(new DatabaseConnectionManager());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = (String) req.getAttribute("action");
		HttpSession session = req.getSession();
		if (action.toLowerCase().equals("register")) {
		} else if (action.toLowerCase().equals("login")) {
			// ..TODO
			User user = null;
			session.setAttribute("user", user);
		} else if (action.toLowerCase().equals("logout")) {
			session.removeAttribute("user");
		} else {
			throw new RuntimeException("No valid action in User Servlet");
		}
	}
}
