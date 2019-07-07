package com.xingyun.sample.model;

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
