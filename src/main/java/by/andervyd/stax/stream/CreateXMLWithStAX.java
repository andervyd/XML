package by.andervyd.stax.stream;

import by.andervyd.introdaction.dataprovider.DataProvider;
import by.andervyd.introdaction.model.Customer;
import by.andervyd.introdaction.utilities.Stopwatch;

import java.io.IOException;
import java.util.List;

import javax.xml.stream.XMLStreamException;


public class CreateXMLWithStAX {

	public static void main(String[] args)
			throws XMLStreamException, IOException {

		List<Customer> data = DataProvider.getData(DataProvider.SMALL);

		Stopwatch watch = new Stopwatch().start("Create XML with StAX");

		StAXStreamCreator creator = new StAXStreamCreator();
		creator.createDocument(data, "./src/main/resources/data/customers_copy_stax.xml");

		watch.stop();
	}
}
