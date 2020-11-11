package by.andervyd.stax.read;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import by.andervyd.introdaction.model.Customer;
import javanet.staxutils.XMLStreamUtils;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;


public class StAXStreamReader {

	@SuppressWarnings("unused")
	private static final String XMLDATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	public List<Customer> getDataFromXML(String filename)
			throws FileNotFoundException, XMLStreamException {

		List<Customer> data = new ArrayList<>();
		Customer customer = null;

		InputStream in = new FileInputStream(new File(filename));
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(in);
		
		int eventType = reader.next();
//		System.out.println(XMLStreamUtils.getEventTypeName(eventType));
		
		while (reader.hasNext()) {
			eventType = reader.next();
//			System.out.println(XMLStreamUtils.getEventTypeName(eventType));

			if(eventType == XMLEvent.START_ELEMENT) {
				String elementName = reader.getName().toString();

				switch(elementName) {
					case "customer" :
						customer = new Customer();
						data.add(customer);
						customer.setId(Integer.parseInt(reader.getAttributeValue("", Customer.ID)));
							break;

					case Customer.NAME :
						customer.setName(reader.getElementText());
							break;

					case Customer.JOINED :
						DateFormat dateFormat = new SimpleDateFormat(XMLDATEFORMAT);
						try {
							customer.setJoined(dateFormat.parse(reader.getElementText()));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						break;

					default :
							break;
				}
			}

		}
		
		return data;
	}
}
