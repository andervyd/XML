package by.andervyd.stax.createdocument;

import by.andervyd.introdaction.dataprovider.DataProvider;
import by.andervyd.introdaction.model.Customer;

import javax.xml.stream.XMLStreamException;
import java.util.List;



public class CreateXMLWithStAX {

	public static void main(String[] args)
			throws XMLStreamException {

		List<Customer> data = DataProvider.getData(DataProvider.SMALL);
		
		StAXStreamCreator creator = new StAXStreamCreator();
		creator.createDocument(data, "./src/main/resources/data/customers_copy_stax.xml");
		
	}
}
