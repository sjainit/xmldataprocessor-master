
package com.companyname.JmsActiveMQ.ConsumerActiveMQ;

import org.json.JSONException;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.companyname.JmsActiveMQ.MongoService.MongoServiceImpl;

@Component
public class ConsumerActiveMQ {
	@Autowired
	MongoServiceImpl mongoservice;

	@JmsListener(destination = "Xml-file-queue")
	public void listener(String msg) throws JSONException {

		mongoservice.saveXmlfiles(XML.toJSONObject(msg));
		System.out.println("Received Message : " + msg);
	}
}
