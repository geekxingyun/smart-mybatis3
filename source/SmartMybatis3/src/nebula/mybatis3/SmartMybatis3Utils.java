package nebula.mybatis3;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import nebula.object.SmartObject;

/**
 * 自动生成 *Mapper.xml
 * 
 * dependencies: NebulaObjectUtils.java
 * 
 */
public class SmartMybatis3Utils {

	// base interface name
	private static String interfaceName;//
	private static List<String> interfaceMethodList;
	private static String insertSQLStatementId;
	private static String insertSQLStatement;
	private static String deleteSQLStatementId;
	private static String deleteSQLStatement;
	private static String updateSQLStatementId;
	private static String updateSQLStatement;

	// base Model info
	private static String modelNameWithPackage = null;// test.model.CustomerBean
	private static String modelSimpleNameWithoutPackage = null;// CustomerBean
	private static String modelSimpleNameFirstCharLowercaseWithoutPackage = null;// customerBean
	private static String modelSimgpleNameWitoutPackageAndSuffix = null;// Customer

	private static List<String> modelFieldList = null;

	// some file define
	private static String mybatis3MapperFileName = null;// CustomerMapper.xml
	private static String mybatis3ResultListId = null;// customerResultListMap

	// table info
	private static String tableName = null;// table name
	private static String PrimarykeyName = null; // Primary key Name

	private static void loadSmartConfigure(Class<?> model, Class<?> interfaceNameArg, String tableNameArg,
			String PrimarykeyNameArg) {
		if (model == null) {
			System.err.println("model can not be null");
			return;
		}

		// test.model.CustomerBean
		modelNameWithPackage = SmartObject.getClassName(model);
		// CustomerBean
		modelSimpleNameWithoutPackage = SmartObject.getClassNameWithOutPackage(model);
		// customerBean
		modelSimpleNameFirstCharLowercaseWithoutPackage = modelSimpleNameWithoutPackage.substring(0, 1).toLowerCase()
				+ modelSimpleNameWithoutPackage.substring(1);
		// Customer
		modelSimgpleNameWitoutPackageAndSuffix = modelSimpleNameWithoutPackage.substring(0,
				modelSimpleNameWithoutPackage.length() - 4);
		// CustomerMapper.xml
		mybatis3MapperFileName = modelSimgpleNameWitoutPackageAndSuffix + "Mapper.xml";
		// customerResultListMap
		mybatis3ResultListId = modelSimpleNameFirstCharLowercaseWithoutPackage + "ResultListMap";
		modelFieldList = SmartObject.getObjectFieldList(model);

		// interface info
		interfaceName = SmartObject.getClassName(interfaceNameArg);
		interfaceMethodList = SmartObject.getInterfaceMethodNameList(interfaceNameArg);

		// table info
		tableName = tableNameArg;
		PrimarykeyName = PrimarykeyNameArg;
		insertSQLStatement = SmartMybatis3Utils.CreateInsertMyBatisSQLStatementByNewObject();
		deleteSQLStatement = createDeleteMyBatisSQLStatementById();
		updateSQLStatement = "UPDATE " + tableName + " ";
		// sort method
		for (String method : interfaceMethodList) {
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

	public static void checkSmartConfigurePrint(Class<?> model, Class<?> interfaceNameArg, String tableName,
			String PrimarykeyName) {

		loadSmartConfigure(model, interfaceNameArg, tableName, PrimarykeyName);

		System.out.println(modelNameWithPackage);
		System.out.println(modelSimpleNameWithoutPackage);
		System.out.println(modelSimpleNameFirstCharLowercaseWithoutPackage);
		System.out.println(modelSimgpleNameWitoutPackageAndSuffix);
		System.out.println(mybatis3MapperFileName);
		System.out.println(mybatis3ResultListId);
	}

	// create Mybatis Mapper xml File
	public static void autoCreateMyBatis3MapperFile(Class<?> model, Class<?> interfaceNameArg, String tableNameArg,
			String PrimarykeyNameArg) throws IOException {

		loadSmartConfigure(model, interfaceNameArg, tableNameArg,PrimarykeyNameArg);

		// creat XML document
		Document mybatisMapperXMLFile = autoCreateMyBatis3MapperDocument(model, interfaceNameArg);

		// write to console
		XMLWriter writerConsole = new XMLWriter(new FileWriter(mybatis3MapperFileName));
		// write to file
		XMLWriter writerFile = new XMLWriter(new FileWriter(mybatis3MapperFileName));

		// set write data
		FileOutputStream fileOutputStream = new FileOutputStream(mybatis3MapperFileName);
		// format setting
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		writerConsole = new XMLWriter(System.out, format);
		writerFile = new XMLWriter(fileOutputStream, format);

		// write data
		writerConsole.write(mybatisMapperXMLFile);
		writerFile.write(mybatisMapperXMLFile);
		writerConsole.close();
		writerFile.close();
	}

	// create mybatis xml document
	private static Document autoCreateMyBatis3MapperDocument(Class<?> model, Class<?> interfaceNameArg) {
		// create document
		Document document = DocumentHelper.createDocument();
		document.clearContent();
		// create xml title
		document.addDocType("mapper", "-//mybatis.org//DTD Mapper 3.0//EN",
				"http://mybatis.org/dtd/mybatis-3-mapper.dtd");

		// namespace
		Element root_mapper = document.addElement("mapper").addAttribute("namespace", interfaceName);

		Element resultMap = root_mapper.addElement("resultMap").addAttribute("type", modelNameWithPackage)
				.addAttribute("id", mybatis3ResultListId);

		resultMap.addElement("id").addAttribute("property", PrimarykeyName).addAttribute("column", PrimarykeyName);

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

				if (modelFieldList.get(i).equals(PrimarykeyName)) {
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
			update.addText(" WHERE " + PrimarykeyName + "=#{" + PrimarykeyName + "}");
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
	public static String CreateInsertMyBatisSQLStatementByNewObject() {
		String result = "";
		String frontResult = "";
		String behindResult = "";
		final StringBuilder sb = new StringBuilder();
		final StringBuilder sb2 = new StringBuilder();
		sb.append("INSERT INTO " + tableName + "(");
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
	public static String createDeleteMyBatisSQLStatementById() {
		return "DELETE FROM " + tableName + " WHERE " + PrimarykeyName + "=#{" + PrimarykeyName + "}";
	}

}