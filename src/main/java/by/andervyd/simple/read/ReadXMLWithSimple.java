package by.andervyd.simple.read;


import by.andervyd.simple.model.Customer;
import by.andervyd.simple.model.Customers;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.transform.RegistryMatcher;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReadXMLWithSimple {
	
	public static final String XMLDATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	public static void main(String[] args)
			throws Exception {

		DateFormat dateFormat = new SimpleDateFormat(XMLDATEFORMAT);
		RegistryMatcher registryMatcher = new RegistryMatcher();
		registryMatcher.bind(Date.class, new DateFormatTransformer(dateFormat));

		Serializer serializer = new Persister(registryMatcher);

		File source = new File("./src/main/resources/data/customers_copy.xml");

		Customers customers = serializer.read(Customers.class, source);
		List<Customer> data = customers.getCustomers();

		for(Customer customer : data) {
			System.out.println(customer);
		}

	}
}
