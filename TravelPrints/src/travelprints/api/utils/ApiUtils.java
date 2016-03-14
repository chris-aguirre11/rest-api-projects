package travelprints.api.utils;

import java.io.StringWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import travelprints.persistence.dao.UserVisitsDao;
import travelprints.persistence.objects.City;

public class ApiUtils {
	
	public static String generateGoogleMapMarkersXML(String user) {
		
		Document doc = null;
		Element rootElement = null;
		StreamResult result = null;
		String output = null;
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			doc = docBuilder.newDocument();
			rootElement = doc.createElement("markers");
			doc.appendChild(rootElement);
			
			List<City> cities = UserVisitsDao.retrieveCityMapMarkersUserHasVisited(user);
			
			for(City c: cities) {
				// marker elements
				Element marker = doc.createElement("marker");
				rootElement.appendChild(marker);
				marker.setAttribute("lat", c.getLatitude());
				marker.setAttribute("lng", c.getLongitude());
			}
			
//			// Leaving this in case I want to write the content into an actual xml file
//			TransformerFactory transformerFactory = TransformerFactory.newInstance();
//			Transformer transformer = transformerFactory.newTransformer();
//			DOMSource source = new DOMSource(doc);
//			result = new StreamResult(new File("C:\\java-ee-dev\\file.xml"));
//			
//			// Output to console for testing
//			StreamResult result = new StreamResult(System.out);
//
//			transformer.transform(source, result);
			
	
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			output = writer.getBuffer().toString().replaceAll("\n|\r", "");
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		return output;
	}
}
