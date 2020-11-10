package by.andervyd.jdom.read;

import by.andervyd.introdaction.model.Customer;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class JDOMReader {

	@SuppressWarnings("unused")
	private static final String XMLDATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	public List<Customer> getDataFromXML(String fileName)
			throws DataConversionException, ParseException {

		List<Customer> data = new ArrayList<>();

		File file = new File(fileName);
		SAXBuilder builder = new SAXBuilder();
		Document document = null;

		try {
			document = builder.build(file);
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
			return null;
		}

		Element root = document.getRootElement();
		List<Element> custElement = root.getChildren("customer");

		for(Element customerElement : custElement) {
			Customer customer = new Customer();
			data.add(customer);

			int id = Integer.parseInt(customerElement.getAttributeValue(Customer.ID));

			Attribute attribute = customerElement.getAttribute(Customer.ID);
			customer.setId(attribute.getIntValue());

			customer.setName(customerElement.getChildText(Customer.NAME));
			customer.setPhone(customerElement.getChildText(Customer.PHONE));
			customer.setAbout(customerElement.getChildText(Customer.ABOUT));

			customer.setAge(Integer.parseInt(customerElement.getChildText(Customer.AGE)));

			customer.setBalance(new BigDecimal(customerElement.getChildText(Customer.BALANCE)));

			customer.setActive(Boolean.parseBoolean(customerElement.getChildText(Customer.ACTIVE)));

			DateFormat dateFormat = new SimpleDateFormat(XMLDATEFORMAT);
			customer.setJoined(dateFormat.parse(customerElement.getChildText(Customer.JOINED)));
		}

		return data;
	}
}