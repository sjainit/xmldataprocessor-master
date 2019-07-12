package com.companyname.ManageCustomerData.MongoService;

import com.companyname.ManageCustomerData.MongoModel.DatabaseModel;

public interface MongoService {

	Object getFile(String fileName);

	void createXmlfiles(DatabaseModel databaseModel);

}
