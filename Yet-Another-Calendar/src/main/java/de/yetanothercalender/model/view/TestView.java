package de.yetanothercalender.model.view;

public class TestView extends ParentView {

	public TestView() {
		createXML();
		System.out.println("XML created");
		readXML();
	}

	public static void main(String[] args) {
		new TestView();
	}

}
