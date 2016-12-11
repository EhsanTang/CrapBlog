package cn.crap.common.exception;

import cn.crap.common.constants.enums.ByErrorCode;

/**
 * 该类为以该maven包为基础实现的业务异常类。<br/>
 * 当需要抛出业务承载异常时，请抛出此类异常。<br/>
 * 属性：							   <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;{@link #errorCode} : 异常状态码。					<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;{@link #data} : 数据，有时即便抛出异常也需返回某些数据。	<br/>
 * <br/>
 * 构造方法中默认不添加 {@link #data}，如需添加请调用{@link #setData(Object)}
 * 
 * @see cn.crap.common.framework.controller.BaseController#expHandler(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, Throwable)   根据异常生成返回报文
 * @author Lijiannan
 * @version 1.0
 * @description 必要异常，用来承载业务异常
 * @time 2016/7/25 11:29
 */
public class ByException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 异常 错误码
	 */
	private String errorCode;
	
	/**
	 * 时所携带的数据，可为为空
	 */
	private Object data;

	/**
	 * Constructor<br/>
	 * 该构造方法等同于 {@linkplain #ByException(String,String) ByException} {@code (errorCode.code, null)}
	 * @param errorCode
	 */
	public ByException(ByErrorCode errorCode){
	    this(errorCode.code,null);
	}
    	 
    /**
     * Constructor <br/>
     * 该构造方法等同于 {@linkplain #ByException(String,String) ByException} {@code (errorCode, null)}
     * @param errorCode
     */
    public ByException(String errorCode){
        this(errorCode,null);
    }
   
    /**
     * Constructor<br/>
     * 该构造方法等同于 {@linkplain #ByException(Throwable, String, String) ByException} {@code (ex, errorCode, null)}
     * @param ex
     * @param errorCode
     */
    public ByException(Throwable ex, String errorCode){
    	this(ex, errorCode, null);
    }
    
    /**
     * Constructor<br/>
     * 该构造方法等同于 {@linkplain #ByException(Throwable, String, String) ByException} {@code (ex, errorCode.code, null)}
     * @param ex
     * @param errorCode
     */
    public ByException(Throwable ex, ByErrorCode errorCode){
    	this(ex, errorCode.code,null);
    }

    
    /**
     * Constructor
     * @param ex 		源异常
     * @param errorCode 错误码
     * @param errMsg    异常原因
     */
    public ByException(Throwable ex, String errorCode, String errMsg){
    	super(errMsg,ex);
        this.errorCode = errorCode;
    }
    
    
    /**
     * Constructor
     * @param errorCode 错误码
     * @param errMsg	异常原因
     */
    public ByException(String errorCode, String errMsg){
    	super(errMsg);
        this.errorCode = errorCode;
    }
    
    
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

  
}
