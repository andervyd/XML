package by.andervyd.stax.read;

import by.andervyd.introdaction.dataprovider.DataProvider;
import by.andervyd.introdaction.model.Customer;

import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.stream.XMLStreamException;


public class ReadXMLWithStAXStream {

	public static void main(String[] args)
			throws FileNotFoundException, XMLStreamException {

		StAXStreamReader reader = new StAXStreamReader();
		List<Customer> data = reader.getDataFromXML(DataProvider.DATADIR + "customers.xml");

		for (Customer customer : data) {
			System.out.println(customer);
		}

		System.out.println("Data returned: " + data.size() + " customers");
	}
}
