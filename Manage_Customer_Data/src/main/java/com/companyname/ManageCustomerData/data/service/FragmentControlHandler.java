package com.companyname.ManageCustomerData.data.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import com.companyname.ManageCustomerData.domain.KeyValue;

public class FragmentControlHandler extends DefaultHandler {

	static Map<String, KeyValue> Map;

	Map<String, Integer> elementNameCount = new HashMap<String, Integer>();

	String xPath = "/";

	XMLReader xmlReader;

	FragmentControlHandler parent;

	StringBuffer characters = new StringBuffer();

	public FragmentControlHandler(XMLReader xmlReader) {
		this.xmlReader = xmlReader;
	}

	private FragmentControlHandler(String xPath, XMLReader xmlReader, FragmentControlHandler parent) {
		this(xmlReader);
		this.xPath = xPath;
		this.parent = parent;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		Integer count = elementNameCount.get(qName);
		if (null == count) {
			count = 1;
		} else {
			count++;
		}
		elementNameCount.put(qName, count);
		String childXPath = xPath + "/" + qName + "[" + count + "]";

		KeyValue keyvalue = new KeyValue();
		keyvalue.setTagName(qName);
		keyvalue.setTagValue(null);
		Map.put(childXPath, keyvalue);

		FragmentControlHandler child = new FragmentControlHandler(childXPath, xmlReader, this);
		xmlReader.setContentHandler(child);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		String value = characters.toString().trim();
		if (value.length() > 0) {  //Not understood
			KeyValue keyvalue = new KeyValue();
			keyvalue.setTagName(qName);
			keyvalue.setTagValue(characters.toString());
			Map.put(xPath, keyvalue);

		}
		xmlReader.setContentHandler(parent);
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		characters.append(ch, start, length);
	}

	public static Map<String, KeyValue> passfile(File file) throws Exception {

		Map = new LinkedHashMap<>();
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		XMLReader xr = sp.getXMLReader();

		xr.setContentHandler(new FragmentControlHandler(xr));
		xr.parse(new InputSource(new FileInputStream(file)));
		return Map;
	}
	
	
}
