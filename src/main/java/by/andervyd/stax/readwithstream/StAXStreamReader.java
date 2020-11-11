package by.andervyd.stax.readwithstream;

import by.andervyd.introdaction.model.Customer;
import javanet.staxutils.XMLStreamUtils;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class StAXStreamReader {

	@SuppressWarnings("unused")
	private static final String XMLDATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	public List<Customer> getDataFromXML(String fileName)
			throws FileNotFoundException, XMLStreamException {

		List<Customer> data = new ArrayList<>();

		InputStream inputStream = new FileInputStream(new File(fileName));
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(inputStream);

		int eventType = reader.next();
		System.out.println(XMLStreamUtils.getEventTypeName(eventType));

		while(reader.hasNext()) {
			eventType = reader.next();
			System.out.println(XMLStreamUtils.getEventTypeName(eventType));
		}

		return data;
	}
}
