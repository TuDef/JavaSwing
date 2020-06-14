package xmlmodifier;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

public class XmlTest {

	public static void main(String[] args) 
	{
		String dir = "D:/Data/employee.xml";
		String[] id;
		String[] name;
		String[] salary;
		try
		{
			File file = new File(dir);
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			Document document = documentBuilder.parse(file);
			
//			String rootNodeName = document.getDocumentElement().getNodeName();
			
			NodeList nodeList = document.getElementsByTagName("employee");
			id = new String[nodeList.getLength()];
			name = new String[nodeList.getLength()];
			salary = new String[nodeList.getLength()];
			
			for(int i = 0; i < nodeList.getLength(); i++)
			{
				Node currentNode = nodeList.item(i);
				
				System.out.println(currentNode.getNodeName());
				Element element = (Element) currentNode;
				
				id[i] = element.getElementsByTagName("id").item(0).getTextContent();
				name[i] = element.getElementsByTagName("name").item(0).getTextContent();
				salary[i] = element.getElementsByTagName("salary").item(0).getTextContent();
				System.out.println("ID" + id[i]);
				System.out.println("NAME" + name[i]);
				System.out.println("SALARY" + salary[i]);
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
