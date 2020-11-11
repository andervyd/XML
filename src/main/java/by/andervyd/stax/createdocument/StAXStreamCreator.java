package by.andervyd.stax.createdocument;

import by.andervyd.introdaction.model.Customer;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.StringWriter;
import java.util.List;


public class StAXStreamCreator {

	@SuppressWarnings("unused")
	private static final String XMLDATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	public void createDocument(List<Customer> data, String filename)
			throws XMLStreamException {

		XMLOutputFactory factory = XMLOutputFactory.newInstance();

		StringWriter stringWriter = new StringWriter();
		XMLStreamWriter writer = factory.createXMLStreamWriter(stringWriter);

		writer.writeStartDocument();
		writer.writeStartElement("customers");

		writer.writeEndElement();
		writer.writeEndDocument();

		writer.flush();
		writer.close();

		System.out.println(stringWriter.toString());

	}
}
