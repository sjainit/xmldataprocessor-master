package com.companyname.ManageCustomerData.MongoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.companyname.ManageCustomerData.MongoModel.DatabaseModel;

@Service
public class MongoServiceImpl implements MongoService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void createXmlfiles(DatabaseModel databaseModel) {

		mongoTemplate.save(databaseModel, "XmlFiles");
	}

	@Override
	public Object getFile(String fileName) {

		Query query = new Query();

		String fname = "^" + fileName;
		query.addCriteria(Criteria.where("filename").regex(fname));
		List<DatabaseModel> files = mongoTemplate.find(query, DatabaseModel.class, "XmlFiles");
		return files;
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
}
