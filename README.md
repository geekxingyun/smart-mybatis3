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
    <id column="userId" property="USER_ID"/>
    <result column="userName" property="USER_NAME"/>
    <result column="userPassword" property="USER_PASSWORD"/>
    <result column="userAge" property="USER_AGE"/>
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
    <id column="userId" property="USER_ID"/>
    <result column="userName" property="USER_NAME"/>
    <result column="userPassword" property="USER_PASSWORD"/>
    <result column="userAge" property="USER_AGE"/>
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

# 2. 了解更多

关于如何集成已经项目实现思路，[移步Wiki](https://github.com/geekxingyun/SmartMybatis3/wiki) 

# 3. 关于我

> 交流即分享，分享才能进步！不对之处，还请各位前辈多多指教。  by 星云

---

# 关于我

[星云CSDN博客](https://blog.csdn.net/hadues)

[星云博客园](http://www.cnblogs.com/xingyunblog)

[星云阿里云栖博客](https://yq.aliyun.com/u/xingyunsky)

[星云GitHub](https://github.com/geekxingyun)

[星云GitHub Page](http://www.520geek.cn)

[星云微博](https://weibo.com/xingyunsky)

## 我的微信公众号

<p>
	<a href="https://img2018.cnblogs.com/blog/622489/201906/622489-20190629001321367-513723942.jpg">
	<img src="https://img2018.cnblogs.com/blog/622489/201906/622489-20190629001321367-513723942.jpg" width="253" height="340"></a>
<p>

# 免费加入我的知识星球

<p><a href="https://github.com/geekxingyun/SpringBootBestPracticesSample/blob/master/resources/images/my_world.png?raw=true"> <img src="https://github.com/geekxingyun/SpringBootBestPracticesSample/blob/master/resources/images/my_world.png?raw=true" width="253" height="340"><a><p>

# 联系我

> QQ：2864438285　
> 
> Email：fairy_xingyun@hotmail.com   

# 赞助支持

<h2>微信赞赏二维码</h2>
<p><img src="https://img2018.cnblogs.com/blog/622489/201812/622489-20181215164147325-217176189.png" alt="" width="303" height="282"></p>
<h2>支付宝赞赏二维码</h2>
<p><img src="https://img2018.cnblogs.com/blog/622489/201812/622489-20181215164420863-364321980.png" alt="" width="297" height="303"></p>
