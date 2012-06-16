package de.yetanothercalendar.model.view;

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

public abstract class CalenderView extends View {

	public Document dXml;

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
	public CalenderView(Year pYear, String pPathOfXsl) {
		// XML erstellen
		Element eRoot = new Element("calender"); // Wurzelelement
		dXml = new Document(eRoot); // Dokument mit vorgegebener Wurzel
		dXml.setDocType(new DocType("calender", "resources/calender.dtd"));
		// Stylesheet hinzufügen
		HashMap<String, String> mapStylesheet = new HashMap<String, String>(2);
		mapStylesheet.put("type", "text/xsl");
		mapStylesheet.put("href", pPathOfXsl);
		ProcessingInstruction piStylesheet = new ProcessingInstruction(
				"xml-stylesheet", mapStylesheet);
		dXml.getContent().add(1, piStylesheet);

		// XML-Dokumentstruktur erstellen
		YearViewHelper yView = new YearViewHelper(pYear);
		Element eYear = yView.getYearElement();

		// Alles in der Richtigen Reihenfolge anfügen
		eRoot.addContent(eYear);
	}

	public void printXml(Document pDoc, String pOutputPath) {
		// XML tatsächlich auf Festplatte abbilden.
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		FileOutputStream output;
		try {
			File rFile = new File(pOutputPath);
			output = new FileOutputStream(rFile);
			outputter.output(pDoc, output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createtestXML() {
		Element root = new Element("Calender");
		Document doc = new Document(root);

		root.addContent(
				new Element("Year").setAttribute("wert", "2012").addContent(
						new Element("Month").setText("August")))
				.addContent(new Comment("Das ist ein Kommentar"))
				.addContent(new Element("Month").setAttribute("Wert", "8"));
		printXml(doc, "/resources/testCreate.xml");
	}
}
