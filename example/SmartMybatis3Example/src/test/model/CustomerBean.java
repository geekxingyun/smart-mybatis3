package test.model;

import java.io.Serializable;

public class CustomerBean implements Serializable{

	/**
	 *   Model
	 */
	private static final long serialVersionUID = -6894246401229699827L;
	private Long customerId;// 用户Id required auto Create
	private String customerAccount;// 用户名 用户帐号 手机号 required
	private String customerPassword;// 用户密码 required
	private String customerNikeName;// 用户昵称
	private String customerToken;// 验证身份Token 由账号和随机数MD5加密生成
	private String customerPhoto;// 用户头像
	private Integer customerSex;// 用户性别 0 保密 1 男 2女
	private Long customerScore;// 用户积分
	private Long customerLevelId;// 用户级别Id
	private String customerRealName;// 用户真实姓名
	private Integer customerAge;// 用户年龄
	private Long customerRegisterTime;// 用户注册时间 auto create
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerAccount() {
		return customerAccount;
	}
	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	public String getCustomerNikeName() {
		return customerNikeName;
	}
	public void setCustomerNikeName(String customerNikeName) {
		this.customerNikeName = customerNikeName;
	}
	public String getCustomerToken() {
		return customerToken;
	}
	public void setCustomerToken(String customerToken) {
		this.customerToken = customerToken;
	}
	public String getCustomerPhoto() {
		return customerPhoto;
	}
	public void setCustomerPhoto(String customerPhoto) {
		this.customerPhoto = customerPhoto;
	}
	public Integer getCustomerSex() {
		return customerSex;
	}
	public void setCustomerSex(Integer customerSex) {
		this.customerSex = customerSex;
	}
	public Long getCustomerScore() {
		return customerScore;
	}
	public void setCustomerScore(Long customerScore) {
		this.customerScore = customerScore;
	}
	public Long getCustomerLevelId() {
		return customerLevelId;
	}
	public void setCustomerLevelId(Long customerLevelId) {
		this.customerLevelId = customerLevelId;
	}
	public String getCustomerRealName() {
		return customerRealName;
	}
	public void setCustomerRealName(String customerRealName) {
		this.customerRealName = customerRealName;
	}
	public Integer getCustomerAge() {
		return customerAge;
	}
	public void setCustomerAge(Integer customerAge) {
		this.customerAge = customerAge;
	}
	public Long getCustomerRegisterTime() {
		return customerRegisterTime;
	}
	public void setCustomerRegisterTime(Long customerRegisterTime) {
		this.customerRegisterTime = customerRegisterTime;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
