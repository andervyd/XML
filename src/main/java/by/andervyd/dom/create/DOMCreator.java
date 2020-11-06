package by.andervyd.dom.create;

import by.andervyd.introdaction.model.Customer;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class DOMCreator {

	@SuppressWarnings("unused")
	private static final String XMLDATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	Document document = null;

	public DOMCreator() {
		// default
	}


	private Element addElement(Element parent, String elementName, String textValue) {
		Element childElement = document.createElement(elementName);
		childElement.setTextContent(textValue);
		parent.appendChild(childElement);
		return childElement;
	}

	public Document createXMLDoc(List<Customer> data)
			throws ParserConfigurationException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		document = builder.newDocument();

		Element root = document.createElement("customers");
		document.appendChild(root);

		for(Customer customer : data) {
			Element custElement = addElement(root, "customer", "");

			String idAsString = Integer.toString(customer.getId());
			custElement.setAttribute(Customer.ID, idAsString);

			addElement(custElement, Customer.NAME,    customer.getName());
			addElement(custElement, Customer.PHONE,   customer.getPhone());
			addElement(custElement, Customer.AGE,     Integer.toString(customer.getAge()));
			addElement(custElement, Customer.BALANCE, customer.getBalance().toString());
			addElement(custElement, Customer.ACTIVE,  Boolean.toString(customer.getActive()));

			Element about =	addElement(custElement, Customer.ABOUT,"");
			CDATASection cdata = document.createCDATASection(customer.getAbout());
			about.appendChild(cdata);

			DateFormat date = new SimpleDateFormat(XMLDATEFORMAT);
			addElement(custElement, Customer.JOINED,  date.format(customer.getJoined()));
		}
		return document;
	}
}
