# SmartMybatis3
Write less code to generate a lot of code,Make Mybatis3 become more Smarter and more intelligent.

What will happen?

only need six line, and you can do this:

auto create a IDaoInterface,

auto create a IServiceInterface,

auto createa a *Mapper.xml

auto create a  mysql table sql statement.

if you want to know how to use it ,please visit  https://github.com/geekxingyun/SmartMybatis3/wiki/Getting-Started

定义实体类
```
import com.xingyun.annotations.model.DBColumn;
import com.xingyun.annotations.model.DBConstraints;
import com.xingyun.annotations.model.DBTable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@DBTable(name = "t_user_info")
@Component
public class UserInfo {
    //数据库字段是主键，必须唯一,不可为空
    //列的名称，类型，长度
    @DBConstraints(primaryKey = true,unique = true,allowNull = false)
    @DBColumn(name = "USER_ID",type = "bigint",length = 20,description="用户主键")
    private Integer userId;

    //用户名称
    @DBConstraints(unique = true,allowNull = false)
    @DBColumn(name = "USER_NAME",description="用户名称")
    private String userName;

    //用户密码
    //默认约束 等价于
//  @DBConstraints(primaryKey = false,unique = false,allowNull = true)
    @DBConstraints
    @DBColumn(name = "USER_PASSWORD",description="用户密码")
    private String userPassword;

    //默认约束 等价于
//  @DBConstraints(primaryKey = false,unique = false,allowNull = true)
    @DBConstraints
    @DBColumn(name = "USER_AGE",type = "int",length = 20)
    private Integer userAge;
}
```
调用方式：

```
import com.xingyun.SmartMyBatis3App;
import com.xingyun.annotations.processor.interfaces.IDBColumnAnnotationHandler;
import com.xingyun.annotations.processor.interfaces.IDBColumnXMLHandler;
import com.xingyun.annotations.processor.interfaces.IDBTableXMLHandler;
import com.xingyun.annotations.processor.interfaces.ISmartObjectHandler;

import java.util.List;

public class UserInfoTest {

    public static void main(String[] args) {
        UserInfoTest userInfoTest=new UserInfoTest();
        userInfoTest.test();
    }

    IDBTableXMLHandler iDBTableXMLHandler;

    IDBColumnXMLHandler idbColumnXMLHandler;

    IDBColumnAnnotationHandler iDBColumnAnnotationHandler;

    ISmartObjectHandler iSmartObjectHandler;

    private void test(){
        //打印MyBatis3 映射表名
        iDBTableXMLHandler= SmartMyBatis3App.getDBTableXMLHandler();
        String dbTableName=iDBTableXMLHandler.getDBTableName(UserInfo.class);
        System.out.println("----打印MyBatis3 映射表名开始----");
        System.out.println(dbTableName);
        System.out.println("----打印MyBatis3 映射表名结束----");

        //打印MyBatis3 XML实体类映射Map
        idbColumnXMLHandler= SmartMyBatis3App.getDBColumnXMLHandler();
        System.out.println("----打印MyBatis3 XML实体类映射Map开始----");
        idbColumnXMLHandler.printDocument(UserInfo.class);
        System.out.println("----打印MyBatis3 XML实体类映射Map结束----");

        //打印MyBatis3 注解实体类映射Map
        iDBColumnAnnotationHandler= SmartMyBatis3App.getDBColumnAnnotationHandler();
        String result=iDBColumnAnnotationHandler.autoCreateMappingModel(UserInfo.class);
        System.out.println("----打印MyBatis3 注解实体类映射Map开始----");
        System.out.println(result);
        System.out.println("----打印MyBatis3 注解实体类映射Map结束---");

        //打印UserInfo 对象字段集合
        iSmartObjectHandler=SmartMyBatis3App.getSmartObjectHandler();
        List<String> fieldNameList=iSmartObjectHandler.getFieldNameList(UserInfo.class);
        System.out.println("----打印UserInfo 对象字段集合开始---");
        for (String fieldName:fieldNameList
             ) {
            System.out.println(fieldName);
        }
        System.out.println("----打印UserInfo 对象字段集合结束---");

        //打印 带包名的字段类型集合
        List<String> fieldTypeList=iSmartObjectHandler.getFieldTypeList(UserInfo.class);
        System.out.println("----打印 带包名的字段类型集合开始---");
        for (String fieldType:fieldTypeList
        ) {
            System.out.println(fieldType);
        }
        System.out.println("----打印 带包名的字段类型集合结束---");

        //打印 不带包名的字段类型集合
        List<String> fieldSimpleTypeList=iSmartObjectHandler.getFieldSimpleTypeList(UserInfo.class);
        System.out.println("----打印 不带包名的字段类型集合开始---");
        for (String fieldSimpleType:fieldSimpleTypeList
        ) {
            System.out.println(fieldSimpleType);
        }
        System.out.println("----打印 不带包名的字段类型集合结束---");

        //打印 注解映射表名的字段类型集合
        List<String> dbColumnNameList=iSmartObjectHandler.getDBColumnList(UserInfo.class);
        System.out.println("----打印 注解映射表名的字段类型集合开始---");
        for (String dbColumnName:dbColumnNameList
        ) {
            System.out.println(dbColumnName);
        }
        System.out.println("----打印 注解映射表名的字段类型集合结束---");
    }
}
```
