package com.biyao.common.bean.result;

import java.io.Serializable;

/**
 * 该类定义以该maven包为基础实现的接口在异常时的返回报文的一部分；
 * @see com.biyao.common.bean.result.ByJsonResult 查看完整报文结构
 * @author Lijiannan
 * @version 1.0
 * @description 异常内容
 * @time 2016/7/25 11:29
 */
public class ByResultErrorMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8519677473220335082L;
	
	/**
	 * 错误码<br/>
	 * {@linkplain com.biyao.common.constants.enums.ByErrorCode 可使用公共定义的错误码}	<br/>
	 * 也可以使用自定义错误码	<br/>
	 */
	private String code;
	
	/**
	 * 异常文案 因{@link #code}不同而不同。
	 */
	private String message;
	
	/**
	 * 部分接口可能需要返回错误原因
	 */
	private Object errordata;
	
	

	public ByResultErrorMessage(String code, String message, Object errordata){
		this.code = code;
		this.message = message;
		this.errordata = errordata;
	}

	public ByResultErrorMessage(String code, String message){
		this(code, message, null);
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the errordata
	 */
	public Object getErrordata() {
		return errordata;
	}

	/**
	 * @param errordata the errordata to set
	 */
	public void setErrordata(Object errordata) {
		this.errordata = errordata;
	}

	
}