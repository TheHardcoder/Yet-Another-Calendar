package de.yetanothercalendar.controller.servlet;

import java.io.IOException;

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
			// TODO create user and add it into database and session
			String email = (String) req.getAttribute("email");
			String forename = (String) req.getAttribute("forename");
			String lastname = (String) req.getAttribute("lastname");
			String password = (String) req.getAttribute("password");
			if (email != null && forename != null && lastname != null
					&& password != null) {
				// TODO check if hashing works correct
				User user = new User(email, forename, lastname,
						hashPassword(password));
				dao.createUser(user);
			} else {
				throw new RuntimeException(
						"No valid parameters for registering User");
			}
		} else if (action.toLowerCase().equals("login")) {
			// ..TODO
			String email = (String) req.getAttribute("email");
			String password = (String) req.getAttribute("password");
			if (email != null && password != null) {
				// TODO check if hashing works correct
				User user = dao
						.isUserDataCorrect(email, hashPassword(password));
				if (user != null) {
					session.setAttribute("user", user);
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
		} else {
			throw new RuntimeException("No valid action in User Servlet");
		}
	}

	private String hashPassword(String password) {
		return DigestUtils.shaHex(password);
	}
}
