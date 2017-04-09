package nebula.mybatis3;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import nebula.constant.SmartConstant;
import nebula.file.SmartFile;
import nebula.object.SmartObject;
import test.IDao.CustomerIDao;
import test.model.CustomerBean;

/**
 * 自动生成 *Mapper.xml
 * 
 * dependencies: SmartObject.java
 * 
 */
public class SmartMybatis3 {

	// base interface name
	private static String interfaceIDaoName;//test.CustomerIDao
	private static List<String> interfaceIDaoMethodList;
	private static String insertSQLStatementId;
	private static String insertSQLStatement;
	private static String deleteSQLStatementId;
	private static String deleteSQLStatement;
	private static String updateSQLStatementId;
	private static String updateSQLStatement;

	private static Map<String, Object> fieldAttributeTypeMap=null;

	private static Boolean showConsoleSwitch=true;
	private static Boolean showFileSwitch=true;

	// base Model info
	private static String modelNameWithPackage = null;// test.model.CustomerBean
	private static String modelSimpleNameWithoutPackage = null;// CustomerBean
	private static String modelSimpleNameFirstCharLowercaseWithoutPackage = null;// customerBean
	private static String modelSimgpleNameWitoutPackageAndSuffix = null;// Customer
	private static String modelSimpleNameFirstCharLowercaseWithoutPackageAndSuffix=null;//customer

	private static List<String> modelFieldList = null;

	// some file define
	private static String mybatis3MapperFileName = null;// CustomerMapper.xml
	private static String mybatis3ResultListId = null;// customerResultListMap

	// table info
	private static String t_table_name = null;// table name
	private static String primarykeyName = null; // Primary key Name

	private static Class<?> smartModelObject=null;
	private static Class<?> smartIDaoObject=null;
	
	private static String ISERVICE_PACKAGE_NAME=null;
	private static String IDaoPackageName=null;

	private static void loadSmartConfigure(SmartConstant smartConstant) {
		if (smartConstant.smartModelObject == null) {
			System.err.println("model must should be set and it can not be null");
			return;
		}
		/***
		 * common Model settings
		 * */
		if(smartConstant.smartModelObject!=null){
			smartModelObject=smartConstant.smartModelObject;
			// test.model.CustomerBean
			modelNameWithPackage = SmartObject.getClassName(smartModelObject);
			// CustomerBean
			modelSimpleNameWithoutPackage = SmartObject.getClassNameWithOutPackage(smartModelObject);
			// customerBean
			modelSimpleNameFirstCharLowercaseWithoutPackage = modelSimpleNameWithoutPackage.substring(0, 1).toLowerCase()
					+ modelSimpleNameWithoutPackage.substring(1);
			// Customer
			modelSimgpleNameWitoutPackageAndSuffix = modelSimpleNameWithoutPackage.substring(0,
					modelSimpleNameWithoutPackage.length() - 4);
			//customer
			modelSimpleNameFirstCharLowercaseWithoutPackageAndSuffix = modelSimgpleNameWitoutPackageAndSuffix.substring(0, 1).toLowerCase()
					+ modelSimgpleNameWitoutPackageAndSuffix.substring(1);
			
			// CustomerMapper.xml
			mybatis3MapperFileName = modelSimgpleNameWitoutPackageAndSuffix + "Mapper.xml";
			// customerResultListMap
			mybatis3ResultListId = modelSimpleNameFirstCharLowercaseWithoutPackage + "ResultListMap";
			modelFieldList = SmartObject.getObjectFieldList(smartModelObject);
		}

		/**
		 * */
		// interface IDao info
		if(smartConstant.smartIDaoObject!=null){
			smartIDaoObject=smartConstant.smartIDaoObject;
			interfaceIDaoName = SmartObject.getClassName(smartIDaoObject);
			interfaceIDaoMethodList = SmartObject.getInterfaceMethodNameList(smartIDaoObject);
			// sort method
			for (String method : interfaceIDaoMethodList) {
				if (method.startsWith("insert")) {
					insertSQLStatementId = method;
				}
				if (method.startsWith("delete")) {
					deleteSQLStatementId = method;
				}
				if (method.startsWith("update")) {
					updateSQLStatementId = method;
				}
			}
		}

		// table info
		if(smartConstant.t_table_name!=null){
			t_table_name = smartConstant.t_table_name;
			updateSQLStatement = "UPDATE " + t_table_name + " ";
		}
		insertSQLStatement = SmartMybatis3.CreateInsertMyBatisSQLStatementByNewObject();
		deleteSQLStatement = createDeleteMyBatisSQLStatementById();

		if(smartConstant.primarykeyName!=null){
			primarykeyName = smartConstant.primarykeyName;
		}

		if(smartConstant.showConsoleSwitch!=null){
			showConsoleSwitch=smartConstant.showConsoleSwitch;
		}

		if(smartConstant.showFileSwitch!=null){
			showFileSwitch=smartConstant.showFileSwitch;
		}
		
		if(smartConstant.ISERVICE_PACKAGE_NAME!=null){
			ISERVICE_PACKAGE_NAME=smartConstant.ISERVICE_PACKAGE_NAME;
		}
		
		if(smartConstant.IDaoPackageName!=null){
			IDaoPackageName=smartConstant.IDaoPackageName;
		}

	}

	public static void checkSmartConfigurePrint(SmartConstant smartConstant) {
		loadSmartConfigure(smartConstant);
		if(showConsoleSwitch){
			System.out.println("smartModelObject-----------"+smartModelObject);
			System.out.println("smartIDaoObject-----------"+smartIDaoObject);
			System.out.println("IDaoPackageName-----------"+IDaoPackageName);
			System.out.println("ISERVICE_PACKAGE_NAME-----------"+ISERVICE_PACKAGE_NAME);
			System.out.println("showConsoleSwitch-----------"+showConsoleSwitch);
			System.out.println("showFileSwitch-----------"+showFileSwitch);
			System.out.println("t_table_name-----------"+t_table_name);
			System.out.println("primarykeyName-----------"+primarykeyName);


		}

	}

	// create Mybatis Mapper xml File
	public static void autoCreateMyBatis3MapperFile(SmartConstant smartConstant) throws IOException {

		loadSmartConfigure(smartConstant);

        
        if(smartModelObject==null){
        	System.err.println("model can't be null");
        	return ;
        }
        
        if(smartIDaoObject==null){
        	System.err.println("IDao can't be null");
        	return ;
        }
        if(t_table_name==null){
        	System.err.println("table name can't be null");
        	return ;
        }
        
        if(primarykeyName==null){
        	System.err.println("primarykeyName can't be null");
        	return ;
        }

		// creat XML document
		Document mybatisMapperXMLFile = autoCreateMyBatis3MapperDocument();

		// write to file
		XMLWriter writerFile = new XMLWriter(new FileWriter(mybatis3MapperFileName));

		// set write data
		FileOutputStream fileOutputStream = new FileOutputStream(mybatis3MapperFileName);

		// format setting
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");

		// write data
		if(showConsoleSwitch){
			// write to console
			XMLWriter writerConsole = new XMLWriter(new FileWriter(mybatis3MapperFileName));
			writerConsole = new XMLWriter(System.out, format);
			writerConsole.write(mybatisMapperXMLFile);
			writerConsole.close();
		}
		if(showFileSwitch){
			writerFile = new XMLWriter(fileOutputStream, format);
			writerFile.write(mybatisMapperXMLFile);
			writerFile.close();
		}
	}

	// create mybatis xml document
	private static Document autoCreateMyBatis3MapperDocument() {
		// create document
		Document document = DocumentHelper.createDocument();
		document.clearContent();
		// create xml title
		document.addDocType("mapper", "-//mybatis.org//DTD Mapper 3.0//EN",
				"http://mybatis.org/dtd/mybatis-3-mapper.dtd");

		// namespace
		Element root_mapper = document.addElement("mapper").addAttribute("namespace",interfaceIDaoName);

		Element resultMap = root_mapper.addElement("resultMap").addAttribute("type", modelNameWithPackage)
				.addAttribute("id", mybatis3ResultListId);

		resultMap.addElement("id").addAttribute("property", primarykeyName).addAttribute("column", primarykeyName);

		for (int i = 1; i < modelFieldList.size(); i++) {
			resultMap.addElement("result").addAttribute("property", modelFieldList.get(i)).addAttribute("column",
					modelFieldList.get(i));
		}

		if (insertSQLStatementId != null) {
			Element insert = root_mapper.addElement("insert").addAttribute("id", insertSQLStatementId)
					.addAttribute("parameterType", modelNameWithPackage);
			insert.addText(insertSQLStatement);
		}

		if (deleteSQLStatementId != null) {
			Element delete = root_mapper.addElement("delete").addAttribute("id", deleteSQLStatementId)
					.addAttribute("parameterType", "Long");
			delete.addText(deleteSQLStatement);
		}

		if (updateSQLStatementId != null) {
			Element update = root_mapper.addElement("update").addAttribute("id", updateSQLStatementId)
					.addAttribute("parameterType", modelNameWithPackage);
			update.addText(updateSQLStatement);
			// set
			Element set_Element = update.addElement("set");
			// if
			for (int i = 0; i < modelFieldList.size(); i++) {

				if (modelFieldList.get(i).equals(primarykeyName)) {
					continue;
				}
				Element if_Element = set_Element.addElement("if");
				String result = judgeDataType(modelFieldList.get(i));

				if_Element.addAttribute("test", result);
				if_Element.addText(modelFieldList.get(i));
				if_Element.addText("=#{");
				if_Element.addText(modelFieldList.get(i));
				if_Element.addText("}");
				if (i < modelFieldList.size() - 1) {
					if_Element.addText(",");
				}
			}
			update.addText(" WHERE " + primarykeyName + "=#{" + primarykeyName + "}");
		}
		return document;
	}

	// judge Data Type
	private static String judgeDataType(Object object) {

		final StringBuilder sb = new StringBuilder();

		if (object instanceof Integer) {
			sb.append(object + "!=null");
		} else if (object instanceof Long) {
			sb.append(object + "!=null");
		} else if (object instanceof String) {
			sb.append(object + "!=null");
		} else if (object instanceof Double) {
			sb.append(object + "!=null");
		} else if (object instanceof Boolean) {
			sb.append(object + "!=null");
		} else {
			sb.append(object + "!=null");
		}
		return sb.toString();
	}


	/*
	 * auto create Insert MyBatis SQL stament by new a object
	 */
	private static String CreateInsertMyBatisSQLStatementByNewObject() {
		String result = "";
		String frontResult = "";
		String behindResult = "";
		final StringBuilder sb = new StringBuilder();
		final StringBuilder sb2 = new StringBuilder();
		sb.append("INSERT INTO " + t_table_name + "(");
		sb2.append("VALUES(");
		for (int i = 0; i < modelFieldList.size(); i++) {
			sb.append(modelFieldList.get(i) + ",");
		}
		for (int i = 0; i < modelFieldList.size(); i++) {
			sb2.append("#{" + modelFieldList.get(i) + "},");
		}
		frontResult = sb.toString().substring(0, sb.toString().length() - 1) + ")";
		behindResult = sb2.toString().substring(0, sb2.toString().length() - 1) + ");";
		result = frontResult + behindResult;
		return result;
	}
	/* 
	 * auto create DELETE SQL Statement by ID
	 * */
	private static String createDeleteMyBatisSQLStatementById() {
		return "DELETE FROM " + t_table_name + " WHERE " + primarykeyName + "=#{" + primarykeyName + "}";
	}


	public static String autoCreatTableSQL(SmartConstant smartConstant) {

		loadSmartConfigure(smartConstant);

        
        if(smartModelObject==null){
        	System.err.println("model can't be null");
        	return "";
        }
        
        if(t_table_name==null){
        	System.err.println("table name can't be null");
        	return "";
        }
        
        if(primarykeyName==null){
        	System.err.println("primarykeyName can't be null");
        	return "";
        }
			
		fieldAttributeTypeMap = SmartObject.getObjectFieldMap(smartModelObject);

		StringBuilder sb = new StringBuilder();
		sb.append("DROP TABLE IF EXISTS `" + t_table_name + "`;");
		sb.append("\r\n");
		sb.append("CREATE TABLE `" + t_table_name + "` (");
		sb.append("\r\n");
		sb.append("`");
		sb.append(primarykeyName);
		sb.append("` bigint(20) NOT NULL AUTO_INCREMENT,");
		sb.append("\r\n");
		for (Map.Entry<String, Object> entry : fieldAttributeTypeMap.entrySet()) {
			if(entry.getKey().equals(primarykeyName)){
				continue;
			}
			switch (entry.getValue().toString()) {
				case "class java.lang.Long":
					sb.append("`");
					sb.append(entry.getKey());
					sb.append("` bigint(20) DEFAULT NULL,");
					sb.append("\r\n");
					break;
				case "class java.lang.String":
					sb.append("`");
					sb.append(entry.getKey());
					sb.append("` varchar(255) DEFAULT NULL,");
					sb.append("\r\n");
					break;
				case "class java.lang.Integer":
					sb.append("`");
					sb.append(entry.getKey());
					sb.append("` int(11) DEFAULT NULL,");
					sb.append("\r\n");
					break;
				case "class java.lang.Double":
					sb.append("`");
					sb.append(entry.getKey());
					sb.append("` double DEFAULT NULL,");
					sb.append("\r\n");
					break;
				case "class java.lang.Boolen":
					sb.append("`");
					sb.append(entry.getKey());
					sb.append("` tinyint(4) DEFAULT NULL,");
					sb.append("\r\n");
					break;
				default:
					break;
			}
		}
		sb.append("PRIMARY KEY (`" + primarykeyName + "`)");
		sb.append("\r\n");
		sb.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
		if(showConsoleSwitch){
	      System.out.println(sb.toString());
		}
		if(showFileSwitch){
			SmartFile.WriteStringToFile(modelSimpleNameWithoutPackage+".sql",sb.toString());
		}
		return sb.toString();
	}
	
	 /**
     * object: which you new object,like this: Object object=new Object();
     * showConsole: if you want it show on console,please set it is
     * true,otherwise set it is false auto create IDAO
     */
    public static Boolean autoCreateObjectIDao(SmartConstant smartConstant) {
    	
    	loadSmartConfigure(smartConstant);
    	
    	if(smartModelObject==null){
            System.err.println("model must be set,and it can't be null");
            return false;
        }

        //package info
        if(IDaoPackageName==null){
            System.err.println(" IDao Package can't be null");
            return false;
        }

        StringBuilder sb = new StringBuilder();

        sb.append("package "+IDaoPackageName+";");
        sb.append("\r\n");
        sb.append("\r\n");
        sb.append("import java.util.List;");
        sb.append("\r\n");
        sb.append("\r\n");
        sb.append("import " + modelNameWithPackage+";");
        sb.append("\r\n");
        sb.append("\r\n");

        sb.append("public interface " + modelSimgpleNameWitoutPackageAndSuffix + "IDao{");

        sb.append("\r\n");
        sb.append("\r\n");
        sb.append("        void" + " insert" + modelSimgpleNameWitoutPackageAndSuffix + "(" + modelSimpleNameWithoutPackage + " " + modelSimpleNameFirstCharLowercaseWithoutPackage + ");");
        sb.append("\r\n");
        sb.append("\r\n");

        sb.append(
                "        void" + " delete" + modelSimgpleNameWitoutPackageAndSuffix + "(Long " + modelSimpleNameFirstCharLowercaseWithoutPackageAndSuffix + "Id);");
        sb.append("\r\n");
        sb.append("\r\n");
        sb.append("        void" + " update" + modelSimgpleNameWitoutPackageAndSuffix + "ById" + "(" + modelSimpleNameWithoutPackage + " " + modelSimpleNameFirstCharLowercaseWithoutPackage
                + ");");
        sb.append("\r\n");
        sb.append("\r\n");

        sb.append("        " + modelSimpleNameWithoutPackage + " select" + modelSimgpleNameWitoutPackageAndSuffix + "ById(Long "
                + modelSimpleNameFirstCharLowercaseWithoutPackageAndSuffix + "Id);");
        sb.append("\r\n");
        sb.append("\r\n");
        sb.append("        List<" + modelSimpleNameWithoutPackage + "> selectAll" + modelSimgpleNameWitoutPackageAndSuffix + "List();");
        sb.append("\r\n");
        sb.append("\r\n");

        sb.append("}");

        if (showConsoleSwitch) {
            System.out.println(sb.toString());
        }

        //文件名称
        String fileName=modelSimgpleNameWitoutPackageAndSuffix + "IDao" + ".java";
        // 写入到文件中
        Boolean result = SmartFile.WriteStringToFile(fileName, sb.toString());
        return result;
    }
    

	/**
	 * object: which you new object,like this: Object object=new Object();
	 * showConsole: if you want it show on console,please set it is
	 * true,otherwise set it is false auto create IService
	 */
	public static Boolean autoCreateObjectIService(SmartConstant smartConstant) {
		
		Boolean result=false;
		
		loadSmartConfigure(smartConstant);
        
        if(smartModelObject==null){
        	System.err.println("model must be set,and it can't be null");
        	return false;
        }
        
        if(ISERVICE_PACKAGE_NAME==null){
        	System.err.println("IService package name can't be null");
        	return false;
        }
        
		StringBuilder sb = new StringBuilder();

		sb.append("package "+ISERVICE_PACKAGE_NAME+";");
		sb.append("\r\n");
		sb.append("\r\n");
		// import library
		sb.append("import java.util.List;");
		sb.append("\r\n");
		sb.append("\r\n");
		sb.append("import " + modelNameWithPackage+";");
		sb.append("\r\n");
		sb.append("\r\n");
		// create file
		sb.append("public interface " + modelSimgpleNameWitoutPackageAndSuffix + "IService{");

		sb.append("\r\n");
		sb.append("\r\n");
		sb.append("       void" + " add" + modelSimgpleNameWitoutPackageAndSuffix + "(" + modelSimpleNameWithoutPackage + " " + modelSimpleNameFirstCharLowercaseWithoutPackage + ");");
		sb.append("\r\n");
		sb.append("\r\n");

		sb.append("       void" + " remove" + modelSimgpleNameWitoutPackageAndSuffix + "(Long " + modelSimpleNameFirstCharLowercaseWithoutPackage + "Id);");
		sb.append("\r\n");
		sb.append("\r\n");
		sb.append("       void" + " modify" + modelSimgpleNameWitoutPackageAndSuffix + "ById" + "(" + modelSimpleNameWithoutPackage + " "
				+ modelSimpleNameFirstCharLowercaseWithoutPackage + ");");
		sb.append("\r\n");
		sb.append("\r\n");

		sb.append("       " + modelSimpleNameWithoutPackage + " find" + modelSimgpleNameWitoutPackageAndSuffix + "ById(Long "
				+ modelSimpleNameFirstCharLowercaseWithoutPackageAndSuffix + "Id);");
		sb.append("\r\n");
		sb.append("\r\n");
		sb.append("       List<" + modelSimpleNameWithoutPackage + "> findAll" + modelSimpleNameWithoutPackage + "List();");
		sb.append("\r\n");
		sb.append("\r\n");

		sb.append("}");

		if (showConsoleSwitch) {
			System.out.println(sb.toString());
			result=true;
		}
		if(showFileSwitch){
			// 写入到文件中
			result = SmartFile.WriteStringToFile(modelSimgpleNameWitoutPackageAndSuffix + "IService" + ".java", sb.toString());
		}
		return result;
	}

}