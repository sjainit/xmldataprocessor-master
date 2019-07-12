package com.companyname.JmsActiveMQ.MongoDAO;

import org.springframework.stereotype.Repository;

@Repository
public interface MongoDAO {
	void save(Object databaseModelObject);


}
