package test.IDao;

import test.model.CustomerBean;

public interface CustomerIDao {
	
	void addCustomer(CustomerBean customerBean);
	
	void deleteCustomerById(Long customerId);
	
	CustomerBean updateCustomer(CustomerBean customerBean);
	
}
