package cn.crap.common.constants.enums;

/**
 * @description 接口返回状态码
 * @author Lijiannan
 * @time 2016年7月12日上午11:49:48
 * @version 1.0
 */
public enum ByResultCode {
    /**
     * 成功
     */
    SUCCESS(1, "成功"),
    /**
     * 失败
     */
    FAILED(0, "失败");

    /**
     * 状态码
     */
    public final int code;
    /**
     * 含义
     */
    public final String msg;

    private ByResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}