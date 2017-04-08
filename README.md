# SmartMybatis3
Write less code to generate a lot of code,Make Mybatis3 become more Smarter and more intelligent.

+++++++++++++++++++++++++++++++++

What will happen?

only one line code ,and you can get the mybatis3 *mapper.xml

auto create CustomerMapper.xml file,this is a test code:

SmartMybatis3Utils.autoCreateMyBatis3MapperFile(CustomerBean.class,CustomerIDao.class,"t_customer","customerId");	

+++++++++++++++++++++++++++++++++

Quickly Start:

step one:

added the two jar package SmartMybatis3-1.1.0.jar and dom4j-1.6.1.jar to your project .

step two:

Create an entity class with Bean,it must end with bean，like CustomerBean.

step three:

Create a IDao Interface,like this：

public interface CustomerIDao {
	
	void addCustomer(CustomerBean customerBean);
	
	void deleteCustomerById(Long customerId);
	
	CustomerBean updateCustomer(CustomerBean customerBean);
	
}

Tips: 
your method must be start with add* delete* update* .

Result:
you will auto create a CustomerMapper.xml file,it is only included three method,add* ,delete*, update*.

Main Method:
public static void autoCreateMyBatis3MapperFile(Class<?> model, Class<?> interfaceNameArg, String tableNameArg,String PrimarykeyNameArg);

