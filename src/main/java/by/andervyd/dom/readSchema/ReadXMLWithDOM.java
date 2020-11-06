package by.andervyd.dom.readSchema;

import by.andervyd.introdaction.dataprovider.DataProvider;
import by.andervyd.introdaction.model.Customer;

import java.util.List;


public class ReadXMLWithDOM {

	public static void main(String[] args) {

		DOMReader reader = new DOMReader();
		
		List<Customer> data = reader.getDataFromXML(DataProvider.DATADIR + "customers.xml");
		System.out.println("There are " + data.size() + " records");
		
		for (Customer customer : data) {
			System.out.println(customer);
		}
	}
}
