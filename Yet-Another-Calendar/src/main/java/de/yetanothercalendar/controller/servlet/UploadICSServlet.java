package de.yetanothercalendar.controller.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import de.yetanothercalendar.model.dao.EventDAO;
import de.yetanothercalendar.model.dao.impl.EventDAOImpl;
import de.yetanothercalendar.model.database.Event;
import de.yetanothercalendar.model.database.User;
import de.yetanothercalendar.model.database.helper.DatabaseConnectionManager;
import de.yetanothercalendar.model.iCal.ICalendarImporter;

public class UploadICSServlet extends HttpServlet {

	private EventDAO dao;

	public UploadICSServlet() {
		dao = new EventDAOImpl(new DatabaseConnectionManager());
		;
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// response.setContentType("text/html");
		// PrintWriter out = response.getWriter();
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			boolean isMultipartContent = ServletFileUpload
					.isMultipartContent(request);
			if (!isMultipartContent) {
				throw new RuntimeException("DAs ist kein file upload");
			}
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				List<FileItem> fields = upload.parseRequest(request);
				Iterator<FileItem> it = fields.iterator();
				if (!it.hasNext()) {
					throw new RuntimeException("Kein File item");
				}
				while (it.hasNext()) {
					FileItem fileItem = it.next();
					boolean isFormField = fileItem.isFormField();
					if (!isFormField) {
						InputStream inputStream = fileItem.getInputStream();
						ICalendarImporter importer = new ICalendarImporter();
						try {
							Calendar importToIcal4J = importer
									.importToIcal4J(inputStream);
							List<Event> parseIcal4JToEventList = importer
									.parseIcal4JToEventList(importToIcal4J,
											user);
							for (Event event : parseIcal4JToEventList) {
								System.out.println("Event imported: "
										+ event.toString());
								dao.createEvents(event);
							}
						} catch (ParserException e) {
							e.printStackTrace();
						}
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		} else {
			throw new RuntimeException("No User logged in!");
		}
	}
}
