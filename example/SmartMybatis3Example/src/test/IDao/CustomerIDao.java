package test.IDao;

import java.util.List;

import test.model.CustomerBean;

public interface CustomerIDao{

        void insertCustomer(CustomerBean customerBean);

        void deleteCustomer(Long customerBeanId);

        void updateCustomerById(CustomerBean customerBean);

        CustomerBean selectCustomerById(Long customerBeanId);

        List<CustomerBean> selectAllCustomerList();

}