package by.andervyd.simple.create;

import by.andervyd.simple.dataprovider.DataProvider;
import by.andervyd.simple.model.Customer;
import by.andervyd.simple.model.Customers;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.StringWriter;
import java.util.List;


public class CreateXMLWithSimple {
	public static void main(String[] args) {

		List<Customer> data = DataProvider.getData(DataProvider.MEDIUM);

		Customers customers = new Customers();
		customers.setCustomers(data);

		Serializer serializer = new Persister();
		StringWriter stringWriter = new StringWriter();
		try {
			serializer.write(customers, stringWriter);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println(stringWriter.toString());
	}
}
