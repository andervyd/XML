package by.andervyd.dom.readSchema;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import by.andervyd.introdaction.model.Customer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class DOMReader {
	
	private static final String XMLDATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	private static final String NSURI = "http://www.example.org/customers";


	public List<Customer> getDataFromXML(String filename) {
		
		List<Customer> data = new ArrayList<>();

		File xmlFile = new File(filename);
		Document doc = null;
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(xmlFile);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		NodeList list = doc.getElementsByTagName("customer");
		System.out.println("Nodes found: " + list.getLength());
		
		for (int i = 0; i < list.getLength(); i++) {
			Customer customer = new Customer();
			data.add(customer);
			
			Element custElement = (Element) list.item(i);
			String idAsString = custElement.getAttribute(Customer.ID);
			customer.setId(Integer.parseInt(idAsString));

			customer.setName(getTextFromElement(custElement, Customer.NAME));
			customer.setAge(Integer.parseInt(getTextFromElement(custElement, Customer.AGE)));
			customer.setBalance(new BigDecimal(getTextFromElement(custElement, Customer.BALANCE)));
			customer.setAbout(getTextFromElement(custElement, Customer.ABOUT));
			customer.setActive(Boolean.parseBoolean(getTextFromElement(custElement, Customer.ACTIVE)));
			
			String joined = getTextFromElement(custElement, Customer.JOINED);
			DateFormat df = new SimpleDateFormat(XMLDATEFORMAT);
			try {
				customer.setJoined(df.parse(joined));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		
		return data;
		
	}

	private String getTextFromElement(Element custElement, String elementName) {
		Element node = (Element) custElement.getElementsByTagName(elementName).item(0);
		String content = node.getTextContent();
		return content;
	}

}
