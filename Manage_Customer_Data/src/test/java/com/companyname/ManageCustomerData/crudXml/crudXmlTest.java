package com.companyname.ManageCustomerData.crudXml;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.xml.sax.SAXException;

public class crudXmlTest {

	@Test
	public void test() throws XPathExpressionException, TransformerFactoryConfigurationError, SAXException, IOException, ParserConfigurationException, TransformerException {
		crudXml.Update("//Employees[1]/Employee[1]/firstname[1]", "Deepak", new File("C:/ProjectM1/Final/Employees.xml"), 0);
		assertEquals("The files differ!", 
			    FileUtils.readFileToString(new File("C:/ProjectM1/Updated_Xml.xml"),"utf-8"), 
			    FileUtils.readFileToString(new File("C:/ProjectM1/Check.xml"),"utf-8"));
	}

}
