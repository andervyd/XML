package by.andervyd.sax.readxmlwithsax;

import by.andervyd.introdaction.dataprovider.DataProvider;

import java.util.List;

public class ReadXMLWithSAX {

	public static void main(String[] args) throws Exception {

//		String filename = DataProvider.DATADIR + "customers.xml";
		String filename = DataProvider.DATADIR + "ERcustomers.xml";
//		String filename = DataProvider.DATADIR + "NScustomers.xml";

//		SAXCustomerHandler saxHandler = new SAXCustomerHandler();
		SAXCustomerHandlerWithError saxHandler = new SAXCustomerHandlerWithError();
//		SAXCustomerHandlerWithSchema saxHandler = new SAXCustomerHandlerWithSchema();
		List<Customer> data = saxHandler.readDataFromXML(filename);
		System.out.println("Number of customers: " + data.size());
		
		for (Customer customer : data) {
			System.out.println(customer);
		}
	}
}/* Output:
			Number of customers: 1000
			1. Carly Gonzales joined March 5, 2005
			2. Taylor Moon joined May 25, 2000
			3. Lynda Bird joined September 28, 1990

			...

			998. Ada Dodson joined November 19, 2004
			999. Vargas Mccullough joined September 11, 2011
			1000. Ellen Morse joined October 10, 2002
*///:~
