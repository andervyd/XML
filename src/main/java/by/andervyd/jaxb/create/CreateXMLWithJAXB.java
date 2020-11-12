package by.andervyd.jaxb.create;

import by.andervyd.jaxb.dataprovider.DataProvider;
import by.andervyd.jaxb.model.Customer;
import by.andervyd.jaxb.model.Customers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.StringWriter;
import java.util.List;


public class CreateXMLWithJAXB {

	public static void main(String[] args) {

		List<Customer> data = DataProvider.getData(DataProvider.MEDIUM);

		Customers customers = new Customers();
		customers.setCustomers(data);

		try {

			JAXBContext context = JAXBContext.newInstance(Customers.class);
			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter stringWriter = new StringWriter();
			marshaller.marshal(customers, stringWriter);

			File file = new File("./src/main/resources/data/customers_copy_jaxb.xml");
			marshaller.marshal(customers, file);

			System.out.println(stringWriter.toString());

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
}
