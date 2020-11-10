package by.andervyd.jdom.read;

import by.andervyd.introdaction.dataprovider.DataProvider;
import by.andervyd.introdaction.model.Customer;
import org.jdom2.DataConversionException;

import java.text.ParseException;
import java.util.List;


public class ReadXMLWithJDOM {

	public static void main(String[] args)
			throws DataConversionException, ParseException {

		JDOMReader reader = new JDOMReader();
		List<Customer> data = reader.getDataFromXML(DataProvider.DATADIR + "customers.xml");

		System.out.println("Number of customers: " + data.size());

		for(Customer customer : data) {
			System.out.println(customer);
		}
	}
}