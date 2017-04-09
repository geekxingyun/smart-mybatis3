package test.IDao;

import java.util.List;

import test.model.CustomerBean;

public interface CustomerIDao{

        void insertCustomer(CustomerBean customerBean);

        void deleteCustomer(Long customerId);

        void updateCustomerById(CustomerBean customerBean);

        CustomerBean selectCustomerById(Long customerId);

        List<CustomerBean> selectAllCustomerList();

}