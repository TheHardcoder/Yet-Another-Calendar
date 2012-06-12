package de.yetanothercalender.model.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.jdom.Comment;
import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.ProcessingInstruction;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import de.yetanothercalendar.model.calendar.Year;

public abstract class ParentView {

	private static File rFile;

	/**
	 * Erzeugt ein XML-Dokument aus einem "calender"-Objekt vom Typ Year.
	 * 
	 * @param pYear
	 *            - Vorlage vom Typ Year
	 * @return Eine Datei ( :File), welches die gespeicherte XML Datei
	 *         repräsentiert. Bei Bedarf kann auch direkt das XML-Dokument (
	 *         :Document) zurückgegeben werden (Absprache Fabian)
	 */
	@SuppressWarnings("unchecked")
	public static File createXML(Year pYear) {
		// XML erstellen
		Element eRoot = new Element("calender"); // Wurzelelement
		Document dXml = new Document(eRoot); // Dokument mit vorgegebener Wurzel
		dXml.setDocType(new DocType("calender", "resources/calender.dtd"));
		// Stylesheet hinzufügen
		HashMap<String, String> mapStylesheet = new HashMap<String, String>(2);
		mapStylesheet.put("type", "text/xsl");
		mapStylesheet.put("href", "resources/calender_yearview.xsl");
		ProcessingInstruction piStylesheet = new ProcessingInstruction(
				"xml-stylesheet", mapStylesheet);
		dXml.getContent().add(1, piStylesheet);

		// XML-Dokumentstruktur erstellen
		YearView yView = new YearView(pYear);
		Element eYear = yView.getYearElement();

		// Alles in der Richtigen Reihenfolge anfügen
		eRoot.addContent(eYear);

		// XML tatsächlich auf Festplatte abbilden.
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		FileOutputStream output;
		try {
			rFile = new File("resources/calender.xml");
			output = new FileOutputStream(rFile);
			outputter.output(dXml, output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Bei Bedarf kann auch das Document zurückgegeben werden
		// return dXml;
		return rFile;
	}

	public void createtestXML() {
		Element root = new Element("Calender");
		Document doc = new Document(root);

		root.addContent(
				new Element("Year").setAttribute("wert", "2012").addContent(
						new Element("Month").setText("August")))
				.addContent(new Comment("Das ist ein Kommentar"))
				.addContent(new Element("Month").setAttribute("Wert", "8"));

		// Auchtung noch eine obsolete Methode
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		FileOutputStream output;
		try {
			output = new FileOutputStream("testCreate.xml");
			outputter.output(doc, output);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void getXML() {
		// Rückgabetyp noch definieren
	}

	// public void readtestXML() {
	// Document doc = null;
	// File fIn = new File("TestLukas.xml");
	//
	// try {
	// SAXBuilder b = new SAXBuilder(true); // validierenden Parser nutzen
	// doc = b.build(fIn); // Zum Test die Testdatei
	// // einlesen
	// } catch (JDOMException j) {
	// j.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// // Auchtung noch eine obsolete Methode
	// XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
	// FileOutputStream output;
	// try {
	// output = new FileOutputStream("FileInOut.xml");
	// outputter.output(doc, output);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

}
