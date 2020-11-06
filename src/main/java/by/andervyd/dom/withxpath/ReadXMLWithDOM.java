package by.andervyd.dom.withxpath;

import by.andervyd.introdaction.dataprovider.DataProvider;
import by.andervyd.introdaction.model.Customer;

import javax.xml.xpath.XPathExpressionException;
import java.util.List;



public class ReadXMLWithDOM {

	public static void main(String[] args)
			throws XPathExpressionException {

		DOMReader reader = new DOMReader();
		
		List<Customer> data = reader.getDataFromXML(DataProvider.DATADIR + "customers.xml",
				"//customer[age >= 65]");
		System.out.println("There are " + data.size() + " records");
		
		for (Customer customer : data) {
			System.out.println(customer);
		}
	}

}
