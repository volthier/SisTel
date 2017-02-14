package br.gov.cultura.DitelAdm.ws;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.SAXReader;

public class SeiRequestHandler extends BasicHandler{
	
	public void invoke(MessageContext context) throws AxisFault{
		try {
			SOAPMessage message = context.getMessage();
			SOAPPart part = message.getSOAPPart();
			
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new StringReader(part.getEnvelope().toString()));
			
			treeWalk(doc.getRootElement());
			
			DocumentSource ds = new DocumentSource(doc);
			part.setContent(ds);
			message.saveChanges();
			System.out.println(context.getMessage().getSOAPPart().getEnvelope().toString());
		} catch (SOAPException e) {
			e.printStackTrace();
			throw new AxisFault(e.getMessage(),e);
		} catch(Exception e) {
			e.printStackTrace();
			throw new AxisFault(e.getMessage(),e);
		}
	}
	
	private void treeWalk(Element element){
		List<Element> remove = new ArrayList<Element>();
		for(int i=0; i < element.nodeCount(); i++){
			Node node = element.node(i);
			if(node instanceof Element){
				treeWalk((Element) node);
			}
			
			if(node.getStringValue() == "" &&
					((Element) node).attributeValue("arrayType") == null){
				remove.add((Element) node);
			}
		}
		
		remove.forEach( (e) -> e.detach());
	}

}
