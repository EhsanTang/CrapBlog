package com.biyao.common.bean.result;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.biyao.common.constants.Constants;
import com.biyao.common.constants.enums.ByErrorCode;
import com.biyao.common.constants.enums.ByResultCode;

/**
 * 该类定义以该maven包为基础实现的接口的报文返回格式,无论正确返回还是异常返回						<br/>
 * 	<li>成功时返回报文结构：</li>							<br/>
 * 	{ 										<br/>
 * 	&nbsp;	"{@linkplain #success success}"				 :"是否成功" 				<br/>
 * 	&nbsp;	"{@linkplain #data data}"	   				 :"数据"					<br/>
 * 	&nbsp;	"{@linkplain #ByResultErrorMessage error}"	 :"null"				<br/>
 *  }
 * 	<li>异常时返回报文结构：</li>							<br/>
 * 	{ 										<br/>
 * 	&nbsp;	"{@linkplain #success success}":"是否成功" 				<br/>
 * 	&nbsp;	"{@linkplain #data data}"	 :"成功或失败时的数据"		<br/>
 * 	&nbsp;	"{@linkplain #ByResultErrorMessage error}"	 :{						<br/>
 * 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "{@linkplain ByResultErrorMessage#code code}" : "错误码"				<br/>
 * 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "{@linkplain ByResultErrorMessage#code message}" : "文案"				<br/>
 * 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "{@linkplain ByResultErrorMessage#errordata errordata}" : "错误信息"	<br/>
 * 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			  }						<br/>
 *  } 
 * @see ByResultErrorMessage 异常信息bean
 * @see com.biyao.common.framework.controller.BaseController#expHandler(HttpServletRequest, HttpServletResponse, Throwable)   根据异常处理返回报文
 * @see #ByJsonResult(ByResultCode, Object, String, Object) 完整构造函数
 * @author Lijiannan
 * @version 1.0
 * @description result
 * @time 2016/7/25 11:29
 */
public class ByJsonResult implements Serializable {
	private Logger logger = Logger.getLogger(ByJsonResult.class); // 日志类
	private static final long serialVersionUID = 7553249056983455065L;

	/**
	 * 是否成功 
	 * 枚举类型： {@linkplain  ByResultCode 接口是否成功状态码} 
	 */
	private Integer success;
	
	/**
	 * 数据
	 */
	private Object data;
	
	/**
	 * 异常内容，success为0时存在
	 * @see ByResultErrorMessage
	 */
	private ByResultErrorMessage error;

	/**
	 * 快捷设置1<p/>
	 * 该构造方法等同于 {@linkplain #ByJsonResult(ByResultCode,Object,String,Object) ByJsonResult} {@code (byResultCode, null, null, null)}
	 * @param byResultCode 结果状态码
	 */
	public ByJsonResult(ByResultCode byResultCode){
		this(byResultCode, null, null,null);
	}


	/**
	 * 快捷设置2<p/>
	 * 该构造方法等同于 {@linkplain #ByJsonResult(ByResultCode,Object,String,Object) ByJsonResult} {@code (byResultCode, data, null, null)}
	 * @param byResultCode 结果状态码
	 * @param data 结果信息
	 */
	public ByJsonResult(ByResultCode byResultCode, Object data){
		this(byResultCode, data, null,null);
	}

	/**
	 * 快捷设置3 <p/>
	 * 该构造方法等同于 {@linkplain #ByJsonResult(ByResultCode,Object,String,Object) ByJsonResult} {@code (byResultCode, null, errorCode, null)}
	 * @param byResultCode 结果状态码
	 * @param errorCode 错误码
	 */
	public ByJsonResult(String errorCode, ByResultCode byResultCode){
		this(byResultCode, null, errorCode, null);
	}

	/**
	 * 快捷设置4 <p/>
	 * 该构造方法等同于 {@linkplain #ByJsonResult(ByResultCode,Object,String,Object) ByJsonResult} {@code (byResultCode, null, errorCode,errorObj)}
	 * @param byResultCode 结果状态码
	 * @param errorCode 错误码
	 * @param errorObj 错误内容可空
	 */
	public ByJsonResult(String errorCode, Object errorObj, ByResultCode byResultCode){
		this(byResultCode, null, errorCode, errorObj);
	}


	/**
	 * 完整的结果信息<br/>
	 * 错误文案因错误码不同而现实，不已参数形式传递
	 * @param byResultCode 结果状态码
	 * @param byErrorCode  错误码
	 * @param data         正常返回信息
	 * @param errorData    错误信息
	 */
	public ByJsonResult(ByResultCode byResultCode, Object data, String byErrorCode,  Object errorData){
	

		//设置result状态
		this.success = byResultCode.code;

		//正常响应信息
		if( data != null ){
			this.data = data;
		}

		//异常
		if(!ByResultCode.SUCCESS.equals(byResultCode) && byErrorCode != null){

			//根据异常状态码获取异常返回报文信息
			String errMsg = Constants.errorCodeMap.get(byErrorCode);
			if(errMsg == null){
				logger.error("ErrOrCode :" + byErrorCode + "没有找到相应 msg ");
				errMsg = ByErrorCode.ERROR_UNKNOWN.msg;
			}

			//错误信息
			if(errorData == null){
				this.error = new ByResultErrorMessage(byErrorCode,errMsg);
			}else{
				this.error = new ByResultErrorMessage(byErrorCode,errMsg,errorData);
			}
		}
	}

	public Integer getSuccess() {
		return success;
	}

	public void setSuccess(Integer success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ByResultErrorMessage getError() {
		return error;
	}

	public void setError(ByResultErrorMessage error) {
		this.error = error;
	}

	
}
