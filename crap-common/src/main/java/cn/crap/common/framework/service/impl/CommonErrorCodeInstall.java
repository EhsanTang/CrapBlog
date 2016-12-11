package cn.crap.common.framework.service.impl;

import cn.crap.common.constants.enums.ByErrorCode;
import cn.crap.common.framework.service.IErrorCodeInstall;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lijiannan
 * @version 1.0
 * @description
 * @time 2016/7/26 16:36
 */
@Service
public class CommonErrorCodeInstall implements IErrorCodeInstall{

    public Map<String, String> installErrorCodeMap() {
        Map<String , String> errorCodeMap = new HashMap<String, String>();
            for(ByErrorCode code : ByErrorCode.values()){
                errorCodeMap.put(code.code, code.msg);
            }
       return errorCodeMap;
    }
}
