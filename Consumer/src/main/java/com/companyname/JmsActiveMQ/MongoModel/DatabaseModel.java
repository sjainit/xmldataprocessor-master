package com.companyname.JmsActiveMQ.MongoModel;

import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "consumedDB")
public class DatabaseModel {

	@Id
	private Object json;

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