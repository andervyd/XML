package by.andervyd.jdom.adddata;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import by.andervyd.introdaction.dataprovider.DataProvider;
import by.andervyd.introdaction.model.Customer;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class CreateXMLWithJDOM {

	public static void main(String[] args) throws IOException {

		List<Customer> data = DataProvider.getData(DataProvider.MEDIUM);

		JDOMCreator creator = new JDOMCreator();
		Document document = creator.createXMLDocument(data);
		List<Element> list = document.getRootElement().getChildren();
		System.out.println("retrieved " + list.size());

		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		String xmlString = outputter.outputString(document);
		System.out.println(xmlString);

		FileWriter writer = new FileWriter(new File("./src/main/resources/data/customers_copy_jdom.xml"));
		outputter.output(document, writer);
	}

}
