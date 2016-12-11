package cn.crap.common.constants.enums;

/**
 * 常用异常错误码
 * @author Lijiannan
 * @version 1.0
 * @description
 * @time 2016/7/25 11:37
 */
public enum ByErrorCode{
    /**
     * 系统未知错误000001
     */
    ERROR_UNKNOWN("000001","系统未知错误"),

    /**
     * 系统访问过于频繁000005
     */
    ERROR_VISIT_TOOMUCH("000005","系统访问过于频繁"),
    
    /**
     * 参数不完整200002
     */
    ERROR_PARAM_INCOMPLETE("200002","参数不完整"),

    /**
     * 授权失败200000
     */
    ERROR_INVALID_USER("200003","授权失败");
    
    
    
    /**
     * 状态码
     */
    public final String code;
    /**
     * 含义
     */
    public final String msg;

    ByErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
