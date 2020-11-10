package by.andervyd.jdom.create;

import by.andervyd.introdaction.dataprovider.DataProvider;
import by.andervyd.introdaction.model.Customer;
import org.jdom2.Document;
import org.jdom2.Element;

import java.util.List;


public class CreateXMLWithJDOM {

	public static void main(String[] args) {

		List<Customer> data = DataProvider.getData(DataProvider.SMALL);

		JDOMCreator creator = new JDOMCreator();
		Document document = creator.createXMLDocument(data);
		List<Element> list = document.getRootElement().getChildren();
		System.out.println("retrieved " + list.size());





	}

}
