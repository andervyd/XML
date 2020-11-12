package by.andervyd.jaxb.read;

import by.andervyd.jaxb.model.Customer;
import by.andervyd.jaxb.model.Customers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class ReadXMLWithJAXB {
    public static void main(String[] args)
        throws JAXBException{

        JAXBContext context = JAXBContext.newInstance(Customers.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        File file = new File("./src/main/resources/data/customers_copy.xml");

        Customers customers = (Customers) unmarshaller.unmarshal(file);

        List<Customer> data = customers.getCustomers();

        for(Customer customer : data) {
            System.out.println(customer);
        }

    }
}
