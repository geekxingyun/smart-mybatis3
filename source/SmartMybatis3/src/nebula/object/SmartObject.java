package nebula.object;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import test.model.CustomerBean;

/**
 * public static String getObjectName(Object object); public static String
 * getObjectSimpleName(Object object); public static List
 * <String> getObjectFieldList(Object object);
 */
public class SmartObject {


    /**
     * @dercription Get Model Name with Package
     * @result test.model.Customer
     * */

    public static String getClassName(Class<?> object) {
        return object.getName();
    }

    /**
     * @dercription Get Model Name without Package
     * @result Customer
     * */
    public static String getClassNameWithOutPackage(Class<?> object) {
        return object.getSimpleName();
    }

    /**
     * @dercription Get Model field attribute name List
     * @result List<String>
     * */
    public static List<String> getObjectFieldList(Class<?> object) {

        List<String> objectFiledList = new ArrayList<String>();

        String[] objectGetClassToStringFormat = object.toString().split(" ");

        @SuppressWarnings("rawtypes")
        Class<?> classInfo = null;
        try {
            classInfo = Class.forName(objectGetClassToStringFormat[1]);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // get class filed List
        // import java.lang.reflect.Field;
        Field[] fieldList = classInfo.getDeclaredFields();

        for (int i = 0; i < fieldList.length; i++) {
            // remove serialVersionUID
            if (!fieldList[i].getName().equals("serialVersionUID")) {
                objectFiledList.add(fieldList[i].getName());
            }
        }
        return objectFiledList;
    }


    /**
     * @dercription Get Object Map (included is field attribute name and field attribute type)
     * object must be like this:
     * CustomerBean customerBean=new CustomerBean();
     * object =customerBean;
     * @result Map<String,Oject> ---> field attribute,field type
     * */
    public static Map<String,Object> getObjectFieldMap(Class<?> object){
        Map<String,Object> objectFieldTypeMap = new HashMap<String, Object>();

        String[] objectGetClassToStringFormat = object.toString().split(" ");

        // get class name
        @SuppressWarnings("rawtypes")
        Class<?> classInfo = null;
        try {
            classInfo = Class.forName(objectGetClassToStringFormat[1]);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // get class filed List
        // import java.lang.reflect.Field;
        Field[] fieldList = classInfo.getDeclaredFields();

        for (int i = 0; i < fieldList.length; i++) {
            // remove serialVersionUID
            if (!fieldList[i].getName().equals("serialVersionUID")) {
                objectFieldTypeMap.put(fieldList[i].getName(),fieldList[i].getType());
            }
        }
        return objectFieldTypeMap;
    }

    /**
     * @dercription Get Object Map (included is field attribute name and field attribute type)
     * @result Map<String,Oject> ---> field attribute,field type
     * */
    public static List<String> getInterfaceMethodNameList(Class<?> object) {

        List<String> objectInterfaceMethodList = new ArrayList<String>();

        String[] objectInterfaceList = object.toString().split(" ");

        @SuppressWarnings("rawtypes")
        Class<?> classInfo = null;
        try {
            classInfo = Class.forName(objectInterfaceList[1]);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Method[] methodList = classInfo.getDeclaredMethods();
        for (int i = 0; i < methodList.length; i++) {
            objectInterfaceMethodList.add(methodList[i].getName());
        }
        return objectInterfaceMethodList;
    }

    // get interface return type name
    public static List<String> getInterfaceReturnTypeNameList(Class<?> object) {

        List<String> objectInterfaceMethodList = new ArrayList<String>();

        String[] objectInterfaceList = object.toString().split(" ");

        @SuppressWarnings("rawtypes")
        Class<?> classInfo = null;
        try {
            classInfo = Class.forName(objectInterfaceList[1]);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Method[] methodList = classInfo.getDeclaredMethods();
        for (int i = 0; i < methodList.length; i++) {
            objectInterfaceMethodList.add(methodList[i].getReturnType().getName());
        }
        return objectInterfaceMethodList;
    }

    // get interface return type simple name without package
    public static List<String> getInterfaceReturnTypeSimpleNameList(Class<?> object) {

        List<String> objectInterfaceMethodList = new ArrayList<String>();

        String[] objectInterfaceList = object.toString().split(" ");

        @SuppressWarnings("rawtypes")
        Class<?> classInfo = null;
        try {
            classInfo = Class.forName(objectInterfaceList[1]);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Method[] methodList = classInfo.getDeclaredMethods();
        for (int i = 0; i < methodList.length; i++) {
            objectInterfaceMethodList.add(methodList[i].getReturnType().getSimpleName());
        }
        return objectInterfaceMethodList;
    }
}
