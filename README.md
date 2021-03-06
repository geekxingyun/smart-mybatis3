# 1. 为什么创建SmartMyBatis3？
大家好,我是星云, 相信大家应该都知道的是[MyBatis3](http://www.mybatis.org/mybatis-3/zh/index.html) 框架是一款优秀的持久层框架，但是我觉得它并不完美。

为什么这么说呢？ 请大家跟我一起来看MyBatis3 现存的几个痛点

## 1.1 痛点一：MyBatis3 使用XML实体类和数据库表字段映射

使用过MyBatis3 的应该都知道,像下面这样，在实际开发中，实体类和数据库表字段一般并不相同，需要一种映射。

为了实现这种映射，我们经常需要定义这样一个resultMap 标签

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.xingyun.sample.model.UserInfoMapper">
  <resultMap type="com.xingyun.sample.model.UserInfo" id="UserInfoResultMap">
    <id column="USER_ID" property="userId"/>
    <result column="USER_NAME" property="userName"/>
    <result column="USER_PASSWORD" property="userPassword"/>
    <result column="USER_AGE" property="userAge"/>
  </resultMap>
  
  <select id="findAllUserInfo" resultMap="UserInfoResultMap">
  </select>
</mapper>
```
一般来说没什么问题，但是如果实体类字段特别多的话自己写起来就很麻烦。

那么有没有更简便的方法呢？

如果你愿意使用当前类库，那么你只需要添加如下代码
```
        //打印MyBatis3 XML实体类映射Map
        IDBColumnXMLHandler idbColumnXMLHandler= SmartMyBatis3App.getDBColumnXMLHandler();
        System.out.println("----打印MyBatis3 XML实体类映射Map开始----");
        idbColumnXMLHandler.printDocument(UserInfo.class);
        System.out.println("----打印MyBatis3 XML实体类映射Map结束----");
```
然后就可以输出我们想要的结果：
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.xingyun.sample.model.UserInfoMapper">
  <resultMap type="com.xingyun.sample.model.UserInfo" id="UserInfoResultMap">
    <id column="USER_ID" property="userId"/>
    <result column="USER_NAME" property="userName"/>
    <result column="USER_PASSWORD" property="userPassword"/>
    <result column="USER_AGE" property="userAge"/>
  </resultMap>
</mapper>
```
## 1.2 痛点二：注解方式返回实体类与数据库字段映射

如果你使用的不是XML配置方式，而是通过注解方式的的话，那么我们可能需要这么做。
```
    @Select(value = "SELECT * FROM t_user_info")
    @Results({
	@Result(property ="userId",column ="USER_ID",javaType=Integer.class),
	@Result(property ="userName",column ="USER_NAME",javaType=String.class),
	@Result(property ="userPassword",column ="USER_PASSWORD",javaType=String.class),
	@Result(property ="userAge",column ="USER_AGE",javaType=Integer.class),
    })
    JobInfo findUserInfo();
```

如果字段特别多，那么手写将会很累。

但是如果使用当前的类库那么只需要编写如下代码即可自动生成：
```
 //打印MyBatis3 注解实体类映射Map
        iDBColumnAnnotationHandler= SmartMyBatis3App.getDBColumnAnnotationHandler();
        String result=iDBColumnAnnotationHandler.autoCreateMappingModel(UserInfo.class);
        System.out.println("----打印MyBatis3 注解实体类映射Map开始----");
        System.out.println(result);
        System.out.println("----打印MyBatis3 注解实体类映射Map结束---");
```
上述操作的前提需要在实体类中添加一些注解
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
# 2. 如何集成

 #### 下载依赖

下载地址：https://github.com/geekxingyun/SmartMybatis3/tree/master/lib 

 #### 添加依赖

##### 方式一：安装到本地仓库（推荐）
-  双击 install-smart-mybatis3-1.0-RELEASE.bat 
 - 或者在*.jar 同级目录下执行maven 安装jar到本地仓库命令
```
mvn install:install-file -DgroupId=com.xingyun -DartifactId=smart-mybatis3 -Dversion=1.0-RELEASE -Dpackaging=jar -Dfile=smart-mybatis3-1.0-RELEASE.jar
```
pom.xml中添加依赖
```
<dependency>
            <groupId>com.xingyun</groupId>
            <artifactId>smart-mybatis3</artifactId>
            <version>1.0-RELEASE</version>
</dependency>
<dependency> 
      <groupId>org.dom4j</groupId>
      <artifactId>dom4j</artifactId> 
      <version>2.1.1</version> 
</dependency>
```
##### 方式二：直接将jar 添加到项目中

# 3. 更多详情

关于如何集成以及项目实现思路，[Java之注解的魔法](https://xingyun.blog.csdn.net/article/details/94833245)

# 4. 关于我

> 交流即分享，分享才能进步！不对之处，还请各位前辈多多指教。  by 星云

---

# 联系我

> QQ：2864438285　
> 
> Email：fairy_xingyun@hotmail.com   

# 赞助支持

<h2>微信赞赏二维码</h2>
<p><img src="https://img2018.cnblogs.com/blog/622489/201812/622489-20181215164147325-217176189.png" alt="" width="303" height="282"></p>
