package cn.crap.common.framework.service.impl;

import cn.crap.common.constants.Constants;
import cn.crap.common.framework.service.IErrorCodeInstall;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Lijiannan
 * @version 1.0
 * @description
 * @time 2016/7/26 16:13
 */
@Component
public class InitService {

   
	/**
	 * @Description 根据不同方式收集错误码和错误信息
	 * @param errCodeInstalls 个业务模块的错误码组装类
	 */
	@Autowired(required = false)
	public void initErrorCode(IErrorCodeInstall[] errCodeInstalls) {
		for (IErrorCodeInstall errorCodeInstall : errCodeInstalls) {
			// 基础类中定义的错误码不能覆盖项目中对应的错误码
			if (errorCodeInstall instanceof CommonErrorCodeInstall) {
				Map<String, String> baseErrors = errorCodeInstall.installErrorCodeMap();
				for (Map.Entry<String, String> baseEntry : baseErrors.entrySet()) {
					Constants.errorCodeMap.putIfAbsent(baseEntry.getKey(), baseEntry.getValue());
				}
			} else {
				Constants.errorCodeMap.putAll(errorCodeInstall.installErrorCodeMap());
			}
		}
	}
    
   
}
