package com.yys.szcp.entity;

import java.util.Date;


/**
 * <p>
 * 
 * </p>
 *
 * @author yys
 * @since 2020-02-02
 */
public class DbLog{

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 管理端用户名
     */
	private String adminName;
    /**
     * 操作
     */
	private String operation;
    /**
     * 执行方法
     */
	private String method;
    /**
     * 请求参数
     */
	private String params;
    /**
     * ip
     */
	private String ip;
    /**
     * 操作时间
     */
	private Date createTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


}
