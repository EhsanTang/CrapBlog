package com.biyao.common.framework.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.biyao.common.framework.service.IErrorCodeInstall;

/**
 * @author Lijiannan
 * @version 1.0
 * @description
 * @time 2016/7/26 16:36
 */
@Component
public class FileErrorCodeInstall implements IErrorCodeInstall{

	private Map<String, String> errorCode;
	
    public Map<String, String> installErrorCodeMap() {
    	if(errorCode==null){
    		return new HashMap<String, String>();
    	}
       return errorCode;
    }

	public Map<String, String> getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Map<String, String> errorCode) {
		this.errorCode = errorCode;
	}
    
}
