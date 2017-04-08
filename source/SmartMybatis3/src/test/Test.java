package test;

import java.io.IOException;

import nebula.mybatis3.SmartMybatis3Utils;
import test.IDao.CustomerIDao;
import test.model.CustomerBean;

public class Test {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub	
		SmartMybatis3Utils.autoCreateMyBatis3MapperFile(CustomerBean.class,CustomerIDao.class,"t_customer","customerId");	
	}
}
