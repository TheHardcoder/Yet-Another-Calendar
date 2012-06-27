package de.yetanothercalendar.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller Klasse für Termin im-/export.
 */
public class TerminServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO action parameter (update oder create) lesen
		// TODO restlichen Parameter lesen
		// TODO event speichern bzw. updaten
		super.doPost(req, resp);
	}
}
