package cn.crap.blog.web.auth;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.crap.common.constants.enums.ByErrorCode;
import cn.crap.common.exception.ByException;
import cn.crap.common.utils.Config;


/**
 * 对登录状态进行拦截验证
 *
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private Config config;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			return true;
		}
		AuthPassport myAnnotation = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);
		if (myAnnotation == null) {
			return true;
		}

		if (myAnnotation.validate() == true) {
			if (request.getParameter("key") != null) {
				String secretKey = request.getParameter("key");

				// 解析key
				String[] keyStrings = secretKey.split("_");
				if (keyStrings.length != 2) {
					throw new ByException(ByErrorCode.ERROR_INVALID_USER);
				}
				if (secretKey.equals(config.getSecretKeys(secretKey.split("_")[0]))) {
					return true;
				} else {
					throw new ByException(ByErrorCode.ERROR_INVALID_USER);
				}
			}else{
				throw new ByException(ByErrorCode.ERROR_INVALID_USER);
			}
		}
		return true;
	}

	/**
	 * 获取请求头
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	private HashMap<String, String> getRequestHeaders(HttpServletRequest request) {
		HashMap<String, String> requestHeaders = new HashMap<String, String>();
		Enumeration<String> headerNames = request.getHeaderNames();

		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			String headerValue = request.getHeader(headerName);
			requestHeaders.put(headerName, headerValue);
		}
		return requestHeaders;
	}

	/**
	 * 获取请求
	 * 
	 * @return
	 */
	public HashMap<String, String> getRequestParams(HttpServletRequest request) {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		Enumeration<String> paramNames = request.getParameterNames();

		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			String paramValue = request.getParameter(paramName);
			requestParams.put(paramName, paramValue);
		}
		return requestParams;
	}

}