package com.companyname.ManageCustomerData.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import javax.jms.Queue;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class producer {

	@Autowired
	private Queue queue;

	@Autowired
	private JmsTemplate jmsTemplate;

	@CrossOrigin(origins = "http://localhost:4200")

	@PostMapping("/pushToQueue")
	public void convertToJSON(@RequestParam("file") MultipartFile myFile) throws IOException {
		{
			File XmlFile;
			XmlFile = convert(myFile);
			String data = FileUtils.readFileToString(XmlFile, "UTF-8");
			jmsTemplate.convertAndSend(queue, data);
		}
	}

	public static File convert(MultipartFile file) throws IOException {
		File convertedFile = new File(file.getOriginalFilename());
		convertedFile.createNewFile();
		FileOutputStream Xmlfos = new FileOutputStream(convertedFile);
		Xmlfos.write(file.getBytes());
		Xmlfos.close();
		return convertedFile;
	}
}
