package by.andervyd.dom.create;


import by.andervyd.introdaction.dataprovider.DataProvider;
import by.andervyd.introdaction.model.Customer;

import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringWriter;
import java.util.List;

public class Main {

	public static void main(String[] args)
			throws ParserConfigurationException, TransformerException {

		List<Customer> data = DataProvider.getData(DataProvider.SMALL);

		DOMCreator creator = new DOMCreator();
		Document document = creator.createXMLDoc(data);

		outputToString(document);

	}

	private static void outputToString(Document document)
			throws TransformerException {

		DOMSource source = new DOMSource(document);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);

		Transformer transformer = getTransformer();
		transformer.transform(source, result);
		String xmlString = writer.toString();

		System.out.println(xmlString);

		outputAsFile(document, "./src/main/resources/data/customers_copy.xml");
	}

	private static void outputAsFile(Document document, String fileName)
			throws TransformerException {

		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new File(fileName));
		getTransformer().transform(source, result);
	}

	private static Transformer getTransformer()
			throws TransformerConfigurationException {

		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();

		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

		return transformer;
	}

// old call:

//		System.out.println(document.toString());
//
//		Node root = document.getFirstChild();
//		System.out.println(root.getNodeName());
//
//		NodeList nodes = root.getChildNodes();
//		for(int i = 0; i < nodes.getLength(); i++) {
//			Node child = nodes.item(i);
//			System.out.println(child.getNodeName());
//			System.out.println(child.getTextContent());
//		}

}
