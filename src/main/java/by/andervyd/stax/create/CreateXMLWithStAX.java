package by.andervyd.stax.create;

import by.andervyd.introdaction.dataprovider.DataProvider;
import by.andervyd.introdaction.model.Customer;

import java.util.List;

import javax.xml.stream.XMLStreamException;


public class CreateXMLWithStAX {

	public static void main(String[] args) throws XMLStreamException {

		List<Customer> data = DataProvider.getData(DataProvider.MEDIUM);
		
		StAXStreamCreator creator = new StAXStreamCreator();
		creator.createDocument(data, "./src/main/resources/data/customers_copy_stax.xml");
		
	}
}
