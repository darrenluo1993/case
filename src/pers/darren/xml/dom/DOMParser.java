package pers.darren.xml.dom;

import static org.w3c.dom.Node.TEXT_NODE;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * DOM方式解析xml文件
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Feb 25, 2021 5:13:20 PM
 */
public class DOMParser {

    public static void main(final String[] args) throws Exception {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document document = builder.parse(DOMParser.class.getResourceAsStream("/test.xml"));
        final NodeList propertiesList = document.getElementsByTagName("properties");
        for (int i = 0; i < propertiesList.getLength(); i++) {
            final Node properties = propertiesList.item(i);
            final NodeList childList = properties.getChildNodes();
            for (int j = 0; j < childList.getLength(); j++) {
                final Node child = childList.item(j);
                if (child.getNodeType() != TEXT_NODE) {
                    System.out.println(child.getNodeName() + "===" + child.getTextContent());
                }
            }
        }
    }
}