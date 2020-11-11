package by.andervyd.stax.eventreader;

import by.andervyd.introdaction.dataprovider.DataProvider;
import by.andervyd.introdaction.model.Customer;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.List;

public class ReadXMLWithStAXEvents {

	public static void main(String[] args)
			throws FileNotFoundException, XMLStreamException {

		StAXEventReader reader = new StAXEventReader();
		List<Customer> data = reader.getDataFromXML(DataProvider.DATADIR + "customers.xml");
		for (Customer customer : data) {
			System.out.println(customer);
		}
		System.out.println("Data returned: " + data.size() + " customers");
	}

}
