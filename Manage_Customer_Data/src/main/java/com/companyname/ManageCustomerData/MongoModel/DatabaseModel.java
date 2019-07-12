package com.companyname.ManageCustomerData.MongoModel;

import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "XmlFile")
public class DatabaseModel {

	@Id
	private Object json;
	private String filename;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {

		this.filename = filename;
	}

	public Object getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Object" + getJson());
		return str.toString();
	}

}