package de.yetanothercalendar.controller.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.CharSet;

import de.yetanothercalendar.model.dao.UserDAO;
import de.yetanothercalendar.model.dao.impl.UserDAOImpl;
import de.yetanothercalendar.model.database.User;
import de.yetanothercalendar.model.database.helper.DatabaseConnectionManager;

/**
 * Controller Klasse für {@link de.yetanothercalendar.model.database.User}.
 */
public class UserServlet extends HttpServlet {
	Calendar c = GregorianCalendar.getInstance();

	private UserDAO dao;

	public UserServlet() {
		dao = new UserDAOImpl(new DatabaseConnectionManager());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = (String) req.getParameter("action");
		HttpSession session = req.getSession();
		if (action.toLowerCase().equals("register")) {
			// TODO create user and add it into database and session
			String email = (String) req.getParameter("email");
			String forename = (String) req.getParameter("forename");
			String lastname = (String) req.getParameter("lastname");
			String password = (String) req.getParameter("password");
			System.out.println("DATA: " + email + forename + lastname + password);
			if (email != null && forename != null && lastname != null
					&& password != null) {
				// TODO check if hashing works correct
				User user = new User(email, forename, lastname,
						hashPassword(password));
				dao.createUser(user);
				session.setAttribute("user", user);
				resp.sendRedirect("calendarservlet?view=yearview&selectedyear=" + c.get(Calendar.YEAR) + "&selectedmonth=" + c.get(Calendar.MONTH) + "&selectedweek=" + c.get(Calendar.WEEK_OF_YEAR) + "&selectedday=" + c.get(Calendar.DAY_OF_MONTH));
			} else {
				// TODO Beim Fehlschlagen des Registrierens wäre ein Redirect
				// auf die Registrierungsseite mit Parameter
				// errordescription="Beschreibung" super.
				throw new RuntimeException(
						"No valid parameters for registering User");
			}
		} else if (action.toLowerCase().equals("login")) {
			// TODO Beim Fehlschlagen des Logins wäre ein Redirect auf die
			// Loginseite mit Parameter errordescription="Beschreibung" super.
			String email = (String) req.getParameter("email");
			String password = (String) req.getParameter("password");
			if (email != null && password != null) {
				// TODO check if hashing works correct
				if (dao.checkUser(email, hashPassword(password))) {
					User user = dao.returnUser(email);
					session.setAttribute("user", user);
					resp.sendRedirect("calendarservlet?view=yearview&selectedyear=" + c.get(Calendar.YEAR) + "&selectedmonth=" + c.get(Calendar.MONTH) + "&selectedweek=" + c.get(Calendar.WEEK_OF_YEAR) + "&selectedday=" + c.get(Calendar.DAY_OF_MONTH));
				} else {
					throw new RuntimeException(
							"No valid credentials for loggin in the User");
				}
			} else {
				throw new RuntimeException(
						"No valid parameters for loggin in the  User");
			}
		} else if (action.toLowerCase().equals("logout")) {
			session.removeAttribute("user");
			resp.sendRedirect("");
		} else {
			throw new RuntimeException("No valid action in User Servlet");
		}
	}

	private String hashPassword(String password) {
		return DigestUtils.shaHex(password);
	}
}
