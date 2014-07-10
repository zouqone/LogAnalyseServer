/**
 * 
 */
package cn.log.tool.util;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author zouqone
 * date 2014年6月20日   上午1:17:12
 *
 */
public class XmlHelp {

	/**
	 * 
	 */
	public XmlHelp() {
		// TODO Auto-generated constructor stub
	}

	public static Document ireportEx(String real){
		File inputXml = new File(real);
		SAXReader saxReader = new SAXReader();
		Document document = null;
		Element root = null;
		try {
			document = saxReader.read(inputXml);
			root = document.getRootElement();
			borswe(root);
			System.out.println(root.asXML());
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}
	
	public static void borswe(Element e){
		if(e!=null){
			//System.out.println(e.getName()+":"+e.getText());
			String height = e.attributeValue("height");
			if(height!=null){
				e.setAttributeValue("height", extendSize(height,1.5));
				//System.out.println(e.asXML());
			}
			String pageWidth = e.attributeValue("pageWidth");
			if(pageWidth!=null){
				e.setAttributeValue("pageWidth", extendSize(pageWidth,1.5));
				//System.out.println(e.asXML());
			}
			
			String width = e.attributeValue("width");
			if(width!=null&&"reportElement".equals(e.getName())){
				//e.setAttributeValue("width", extendSize(width,1.1));
				//System.out.println(e.asXML());
			}
			
			for (Iterator<Element> j = e.elementIterator();j.hasNext();) {
				Element node = j.next();
				borswe(node);
			}
		}
	}
	
	public static String extendSize(String size,double d){
		Integer n = Integer.parseInt(size);
		n = (int) (n*d);
		size = n.toString();
		return size;
	}
}
