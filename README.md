# SmartMybatis3
Write less code to generate a lot of code,Make Mybatis3 become more Smarter and more intelligent.

+++++++++++++++++++++++++++++++++

What will happen?


if you want to create a table ,plase do this:

SmartConstant smartConstant4=new SmartConstant(); 

smartConstant4.smartModelObject=CustomerBean.class; 

smartConstant4.t_table_name="t_customer";//table name 

smartConstant4.primarykeyName="customerId";//primary key name 

smartConstant4.showConsoleSwitch=true;//default is true,switch display on Console or not display 

smartConstant4.showFileSwitch=true;//default is true,switch display in a File or not display 

SmartMybatis3.autoCreatTableSQL(smartConstant4);




