package com.wangxile.apiidempotent.common;

/**
 * 业务编码
 *
 * @author maohuanhuan
 */
public enum BusinessCode {

    /**
     * 请求成功
     */
    SUCCESS(0),
    /**
     * 系统错误
     */
    SYSTEM(1),
    /**
     * 检测错误
     */
    SAMPLE(2),
    /**
     * 报表错误
     */
    REPORT(3),
    /**
     * 权限错误
     */
    PERMISSIONS(4),
    /**
     * 监控监管错误
     */
    MONITORING(5),
    /**
     * 默认错误
     */
    DEFAULT(99);

    /**
     * 业务编码
     */
    private final int businessCode;

    /**
     * 业务编码
     *
     * @param businessCode
     */
    BusinessCode(final int businessCode) {
        this.businessCode = businessCode;
    }

    /**
     * 获取业务编码
     *
     * @return
     */
    public int getBusinessCode() {
        return businessCode;
    }

}
