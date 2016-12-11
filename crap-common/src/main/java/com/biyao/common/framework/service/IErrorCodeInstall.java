package com.biyao.common.framework.service;

import java.util.Map;

/**
 * @author Lijiannan
 * @version 1.0
 * @description
 * @time 2016/7/26 16:17
 */
public interface IErrorCodeInstall {

    /**
     * 该方法用以收集所有错误码
     * @return 自己工程的所有（errorCode：Msg)
     */
    Map<String,String> installErrorCodeMap();
}
