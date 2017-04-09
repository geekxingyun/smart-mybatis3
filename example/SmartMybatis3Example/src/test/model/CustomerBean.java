package test.model;

import java.io.Serializable;

public class CustomerBean implements Serializable{

	/**
	 *   Model
	 */
	private static final long serialVersionUID = -6894246401229699827L;
	public Long customerId;// 用户Id required auto Create
	public String customerAccount;// 用户名 用户帐号 手机号 required
	public String customerPassword;// 用户密码 required
	public String customerNikeName;// 用户昵称
	public String customerToken;// 验证身份Token 由账号和随机数MD5加密生成
	public String customerPhoto;// 用户头像
	public Integer customerSex;// 用户性别 0 保密 1 男 2女
	public Long customerScore;// 用户积分
	public Long customerLevelId;// 用户级别Id
	public String customerRealName;// 用户真实姓名
	public Integer customerAge;// 用户年龄
	public Long customerRegisterTime;// 用户注册时间 auto create

}
