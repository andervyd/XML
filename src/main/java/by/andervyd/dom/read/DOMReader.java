package by.andervyd.dom.read;

import by.andervyd.introdaction.model.Customer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class DOMReader {
	
	@SuppressWarnings("unused")
	private static final String XMLDATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	public List<Customer> getDataFromXML(String fileName) {
		
		List<Customer> data = new ArrayList<>();

		File xmlFile = new File(fileName);
		Document document = null;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {

			builder = factory.newDocumentBuilder();
			document = builder.parse(xmlFile);

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

//		Element root = document.getDocumentElement();
//		System.out.println(root.getNodeName());

		NodeList list = document.getElementsByTagName("customer");
		System.out.println("Nodes found: " + list.getLength());

		for (int i = 0; i < list.getLength(); i++) {
			Customer customer = new Customer();
			data.add(customer);

			Element custElement = (Element) list.item(i);
			String idAsElement = custElement.getAttribute(Customer.ID);
			customer.setId(Integer.parseInt(idAsElement));

			String content = getTextFromElement(custElement, Customer.NAME);
			customer.setName(content);

			String joined = getTextFromElement(custElement, Customer.JOINED);
			DateFormat format = new SimpleDateFormat(XMLDATEFORMAT);
			try {
				customer.setJoined(format.parse(joined));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return data;
		
	}

	private String getTextFromElement(Element custElement, String elementName) {
		Element node = (Element) custElement.getElementsByTagName(elementName).item(0);
		return node.getTextContent();
	}

}
