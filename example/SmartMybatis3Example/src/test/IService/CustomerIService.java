package test.IService;

import java.util.List;

import test.model.CustomerBean;

public interface CustomerIService{

       void addCustomer(CustomerBean customerBean);

       void removeCustomer(Long customerBeanId);

       void modifyCustomerById(CustomerBean customerBean);

       CustomerBean findCustomerById(Long customerId);

       List<CustomerBean> findAllCustomerBeanList();

}