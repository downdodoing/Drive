package com.cqut.entity;

import java.io.Serializable;

public class Coach implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String schoolId;
	private String header;
	private String sex;
	private String loginAccount;
	// 账号状态
	private String status;

	public Coach(String id, String name, String schoolId, String header,
			String sex, String loginAccount, String status) {
		super();
		this.id = id;
		this.name = name;
		this.schoolId = schoolId;
		this.header = header;
		this.sex = sex;
		this.loginAccount = loginAccount;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Coach [id=" + id + ", name=" + name + ", schoolId=" + schoolId
				+ ", header=" + header + ", sex=" + sex + ", loginAccount="
				+ loginAccount + ", status=" + status + "]";
	}

}
