package by.andervyd.jdom.create;

import by.andervyd.introdaction.model.Customer;
import org.jdom2.Document;
import org.jdom2.Element;

import java.util.List;

public class JDOMCreator {

	@SuppressWarnings("unused")
	private static final String XMLDATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	public JDOMCreator() {

	}

	public Document createXMLDocument(List<Customer> data) {

		Document document = new Document();
		Element root = new Element("customers");
		document.addContent(root);

		for(Customer customer : data) {
			Element custElement = new Element("customer");
			root.addContent(custElement);
		}

		return document;
	}
	
}
