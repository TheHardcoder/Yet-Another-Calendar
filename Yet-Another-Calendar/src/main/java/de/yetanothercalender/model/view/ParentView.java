package de.yetanothercalender.model.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom.Comment;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public abstract class ParentView {

	public void readXML() {
		Document doc = null;
		try {
			SAXBuilder b = new SAXBuilder(true); // validierenden Parser nutzen
			doc = b.build(new File("TestLukas.xml")); // Zum Test die Testdatei
														// einlesen
		} catch (JDOMException j) {
			j.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		XMLOutputter outputter = new XMLOutputter();
		FileOutputStream output;
		try {
			output = new FileOutputStream("FileInOut.xml");
			outputter.output(doc, output);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void createXML() {
		Element root = new Element("Calender"); // Wurzelelement erzeugen
		Document doc = new Document(root); // neues Dok. benötigt nur Wurzel

		root // Dokumentstruktur erzeugen
		.addContent(
				new Element("Year").setAttribute("wert", "2012").addContent(
						new Element("Month").setText("August")))
				.addContent(new Comment("Das ist ein Kommentar"))
				.addContent(new Element("Month").setAttribute("Wert", "8"));

		XMLOutputter outputter = new XMLOutputter();
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

}
