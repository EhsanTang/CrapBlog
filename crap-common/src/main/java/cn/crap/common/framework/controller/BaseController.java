package cn.crap.common.framework.controller;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.crap.common.bean.result.ByJsonResult;
import cn.crap.common.constants.Constants;
import cn.crap.common.constants.enums.ByErrorCode;
import cn.crap.common.constants.enums.ByResultCode;
import cn.crap.common.exception.ByException;


/**
 * 该类定义以该maven包为基础实现的接口的基类，其中设定了几项基本操作：<br/>
 * <li> {@linkplain #expHandler(HttpServletRequest, HttpServletResponse, Throwable) 处理异常} </li>
 * <li> {@linkplain #getRequestHeaders(HttpServletRequest) 获取请求头信息} </li>
 * <li> {@linkplain #getRequestParams(HttpServletRequest) 获取请求参数} </li>
 * <br/><br/>
 * @author		Lijiannan
 * @time   		2016年9月30日下午5:42:56
 * @version 	1.0
 */
public abstract class BaseController {
    private Logger logger = Logger.getLogger("exceptionLog"); 

    /**
     * @Description 将系统捕获的异常转化为返回报文
     * @see cn.crap.common.bean.result.ByJsonResult 返回报文结构ByJsonResult
     * @see cn.crap.common.exception.ByException 	  业务异常ByException
     * @param request 请求
     * @param response 响应
     * @param ex 异常
     * @return ByJsonResult
     */
    @RequestMapping("/error.do")
    @ExceptionHandler
    @ResponseBody
    public ByJsonResult expHandler(HttpServletRequest request, HttpServletResponse response, Throwable ex) {

        HashMap<String, Object> log = new HashMap<String, Object>();
        log.put("ip", request.getRemoteAddr());
        log.put("method", request.getMethod());
        log.put("url", request.getRequestURL().toString());
        log.put("headers", getRequestHeaders(request));
        log.put("params", getRequestParams(request));
        if (ex instanceof ByException) {
            String errorCode = ((ByException) ex).getErrorCode();
            String errorInf = StringUtils.isEmpty(ex.getMessage()) ? null : ex.getMessage();
			Object data = ((ByException) ex).getData();
			
            log.put("error_code", errorCode);
            log.put("message", Constants.errorCodeMap.get(errorCode));
            logger.error(log, ex);
			
            return new ByJsonResult(ByResultCode.FAILED, data, errorCode, errorInf );
        } else {
            log.put("error_code", ByErrorCode.ERROR_UNKNOWN);
            log.put("message", ex.getMessage());
            logger.error(log, ex);
            return new ByJsonResult(ByErrorCode.ERROR_UNKNOWN.code, ex.getMessage(), ByResultCode.FAILED);
        }

    }


   
    /**
     * @Description 获取请求头信息
     * @param request
     * @return
     */
    protected HashMap<String, String> getRequestHeaders(HttpServletRequest request) {
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
     * @Description 获取请求参数
     * @param request
     * @return
     */
    protected HashMap<String, String> getRequestParams(HttpServletRequest request) {
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