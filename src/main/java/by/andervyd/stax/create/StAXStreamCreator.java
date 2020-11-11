package by.andervyd.stax.create;

import by.andervyd.introdaction.model.Customer;

import java.io.StringWriter;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;


public class StAXStreamCreator {

	@SuppressWarnings("unused")
	private static final String XMLDATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	public void createDocument(List<Customer> data, String filename) throws XMLStreamException {
	
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		
		StringWriter sw = new StringWriter();
		XMLStreamWriter writer = factory.createXMLStreamWriter(sw);
		
		writer.writeStartDocument();
		writer.writeStartElement("customers");

		for(Customer customer : data) {
			createCustDocument(writer, customer);
		}

		writer.writeEndElement();
		writer.writeEndDocument();
		
		writer.flush();
		writer.close();
		
		System.out.println(sw.toString());
		
	}

	private void createCustDocument(XMLStreamWriter writer, Customer customer)
			throws XMLStreamException {

		writer.writeStartElement("customer");
		writer.writeAttribute(Customer.ID, Integer.toString(customer.getId()));

		writeDataElement(writer, customer.getName(), Customer.NAME);

		writer.writeEndElement();
	}

	private void writeDataElement(XMLStreamWriter writer, String values, String elementName)
			throws XMLStreamException {

		writer.writeStartElement(elementName);
		writer.writeCharacters(values);
		writer.writeEndElement();
	}
}
