package com.companyname.ManageCustomerData.data.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.io.FileUtils;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import com.companyname.ManageCustomerData.MongoModel.DatabaseModel;
import com.companyname.ManageCustomerData.MongoService.MongoService;
import com.companyname.ManageCustomerData.crudXml.crudXml;
import com.companyname.ManageCustomerData.data.service.TestDataService;
import com.companyname.ManageCustomerData.domain.KeyValue;

@RestController
public class DataController {
	File filetemp;
	int temp = 0;
	@Autowired
	private TestDataService testDataService;

	@Autowired
	private MongoService mongoservice;
	private FileOutputStream outputStream;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(path = "/getxml")
	public Map<String, KeyValue> getJson(@RequestParam("file") MultipartFile File) throws Exception {
		temp = 0;

		Map<String, KeyValue> response = null;

		String filename = File.getOriginalFilename();
		filetemp = TestDataService.convert(File);
		response = testDataService.getJson(File);
		System.out.println(response);
		String data = FileUtils.readFileToString(filetemp, "UTF-8");
		DatabaseModel databaseModel = new DatabaseModel();
		databaseModel.setFilename(filename);
		databaseModel.setJson(XML.toJSONObject(data));
		mongoservice.createXmlfiles(databaseModel);
		File newFile = new File(new File("").getAbsolutePath().concat("\\src\\main\\resources\\") + filename);

		try {
			InputStream inputStream = File.getInputStream();

			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			outputStream = new FileOutputStream(newFile);
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(response);
		return response;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(path = "/update")
	public void updateController(@RequestBody ArrayList<String> response)
			throws XPathExpressionException, TransformerFactoryConfigurationError, SAXException, IOException,
			ParserConfigurationException, TransformerException {

		crudXml.Update(response.get(0), response.get(1), filetemp, temp++);

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(path = "/delete")
	public void deleteController(@RequestBody String response) throws TransformerFactoryConfigurationError, Exception

	{
		crudXml.Delete(response, filetemp, temp++);

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(path = "/generate")
	public void generateController(@RequestBody String filename) throws TransformerFactoryConfigurationError, Exception

	{
		File file = new File(new File("").getAbsolutePath().concat("\\src\\main\\resources\\" + filename + ".xml"));
		File file1 = new File("C:\\ProjectM1\\Updated_Xml.xml");
		FileReader fr = new FileReader(file1);
		BufferedReader br = new BufferedReader(fr);
		FileWriter fw = new FileWriter(file, true);
		String s;

		while ((s = br.readLine()) != null) { // read a line
			fw.write(s); // write to output file
			fw.flush();
		}
		br.close();
		fw.close();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(path = "/saveChild")
	public void addChildController(@RequestBody ArrayList<String> response)
			throws TransformerFactoryConfigurationError, Exception

	{
		String tagName = response.get(1);
		String tagValue = response.get(2);
		String xPath = response.get(0);
		crudXml.saveChild(xPath, tagName, tagValue, temp++, filetemp);

	}

	@CrossOrigin(origins = "http://localhost:4200")

	@PostMapping(path = "/saveObject")

	public void addObjectController(@RequestBody ArrayList<String> response)

			throws XPathExpressionException, SAXException, IOException, ParserConfigurationException,

			TransformerFactoryConfigurationError, TransformerException {
		String xPath = response.get(0);
		response.remove(0);

		crudXml.saveObj(response, filetemp, temp++, xPath);
	}

	@PostMapping(path = "/getFile")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Object> getFile(@RequestBody String filename) {

		return new ResponseEntity<Object>(mongoservice.getFile(filename), HttpStatus.OK);

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(path = "/filepath")
	public Map<String, KeyValue> getJson(@RequestBody String filepath)
			throws TransformerFactoryConfigurationError, Exception

	{
		temp = 0;
		filepath= new File("").getAbsolutePath().concat(filepath);
		Path path = Paths.get(filePathFormatter(filepath));
		File file = path.toFile();
		filetemp = file;
		return testDataService.getJson1(file);

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path = "/list")
	public Map<String, String> listFilesAndFolders() {

		Map<String, String> referenceData = new LinkedHashMap<String, String>();
		String directoryName = new File("").getAbsolutePath().concat("\\src\\main\\resources\\");
		File directory = new File(directoryName);

		LinkedList<String> arr = new LinkedList<String>();
		File[] fList = directory.listFiles();
		for (File file : fList) {
			arr.add(file.getName());
			referenceData.put("\\src\\main\\resources\\" + file.getName(),
					file.getName());
		}
		return referenceData;

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(path = "/deletehome")
	public void deletefile(@RequestBody String filepath) throws TransformerFactoryConfigurationError, Exception

	{
		filepath= new File("").getAbsolutePath().concat(filepath);
		File file = new File(filepath);
		file.delete();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(path = "/view")
	public String viewfile(@RequestBody String filepath) throws TransformerFactoryConfigurationError, Exception

	{
		
		filepath= new File("").getAbsolutePath().concat(filepath);
		File file = new File(filepath);
		return FileUtils.readFileToString(file);
	}

	public String filePathFormatter(String filepath) {
		return filepath.replace('\\', '/');
	}

}
