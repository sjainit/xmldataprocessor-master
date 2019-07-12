package com.companyname.JmsActiveMQ.MongoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.companyname.JmsActiveMQ.MongoDAO.MongoDAO;

@Service
public class MongoServiceImpl implements MongoService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private MongoDAO mongoDAO;

	@Override
	public void saveXmlfiles(Object consumedFile) {

		mongoDAO.save(consumedFile);

	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
