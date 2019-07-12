package com.companyname.JmsActiveMQ.MongoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class MongoDAOImpl implements MongoDAO {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void save(Object databaseModel) {

		mongoTemplate.save(databaseModel, "consumedDB");

	}

}
