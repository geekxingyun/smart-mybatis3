# SmartMybatis3
Write less code to generate a lot of code,Make Mybatis3 become more Smarter and more intelligent.

+++++++++++++++++++++++++++++++++

What will happen?

        //auto create a IDao file
        SmartConstant smartConstant=new SmartConstant();
        smartConstant.smartModelObject=CustomerBean.class;
        smartConstant.IDaoPackageName="test.IDao";
        smartConstant.showConsoleSwitch=true;//default is true
        smartConstant.showFileSwitch=true;//default is true
        SmartMybatis3.autoCreateObjectIDao(smartConstant);
        
        //auto create a IService file
        SmartConstant smartConstant0=new SmartConstant();
        smartConstant0.smartModelObject=CustomerBean.class;
        smartConstant0.ISERVICE_PACKAGE_NAME="test.IService";
        smartConstant0.showConsoleSwitch=true;//default is true
        smartConstant0.showFileSwitch=true;//default is true
        SmartMybatis3.autoCreateObjectIService(smartConstant0);

        //check all configure
        SmartConstant smartConstant1=new SmartConstant();
        smartConstant1.smartModelObject=CustomerBean.class;    
        smartConstant1.smartIDaoObject=CustomerIDao.class;        
        smartConstant1.IDaoPackageName="test.IDao";
        smartConstant1.ISERVICE_PACKAGE_NAME="test.IService";
        smartConstant1.t_table_name="t_customer";//table name
        smartConstant1.primarykeyName="customerId";//primary key name
        smartConstant1.showConsoleSwitch=true;//default is true
        smartConstant1.showFileSwitch=true;//default is true
        SmartMybatis3.checkSmartConfigurePrint(smartConstant1);
        
        //auto create create table SQL Statement
        SmartConstant smartConstant4=new SmartConstant();
        smartConstant4.smartModelObject=CustomerBean.class;
        smartConstant4.t_table_name="t_customer";//table name
        smartConstant4.primarykeyName="customerId";//primary key name
        smartConstant4.showConsoleSwitch=true;//default is true,switch display on Console or  not display
        smartConstant4.showFileSwitch=true;//default is true,switch display in a  File or  not display
        SmartMybatis3.autoCreatTableSQL(smartConstant4);
        
        //auto create *Mapper.xml
        SmartConstant smartConstant3=new SmartConstant();
        smartConstant3.smartModelObject=CustomerBean.class;
        smartConstant3.smartIDaoObject=CustomerIDao.class;
        smartConstant3.t_table_name="t_customer";
        smartConstant3.primarykeyName="customerId";
        smartConstant3.showConsoleSwitch=true;//default is true
        smartConstant3.showFileSwitch=true;//default is true
        SmartMybatis3.autoCreateMyBatis3MapperFile(smartConstant3);	



