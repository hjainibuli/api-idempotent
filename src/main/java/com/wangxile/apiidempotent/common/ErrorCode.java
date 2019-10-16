package com.wangxile.apiidempotent.common;


import java.io.Serializable;

/**
 * 错误码定义规范
 * <p>
 * 默认请求成功编码为0
 * 根据业务编码进行设置确认，具体枚举值参考 BusinessCode 中的编码进行开发
 * 编码组成如下
 * 1-2位表示业务的编码，
 * 后面3位指代具体的业务编码
 *
 * @author maohh
 * @params 静态常量
 */
public class ErrorCode implements Serializable {
    /**
     * 数据请求成功
     */
    public static final ErrorCode SUCCESS = new ErrorCode(BusinessCode.SUCCESS, "请求成功!");
    /**
     * 数据上传有误
     */
    public static final ErrorCode PARAMETER_ERROR = new ErrorCode(BusinessCode.SYSTEM, "参数不能为空!");

    /**
     * 数据上传有误
     */
    public static final ErrorCode DATA_UPLOAD_ERROR = new ErrorCode(BusinessCode.SYSTEM, "数据上传有误!");
    /**
     * 权限的相关控制
     */
    public static final ErrorCode PASSWORD_ERROR = new ErrorCode(BusinessCode.PERMISSIONS, 1, "密码错误!");
    public static final ErrorCode UN_AUTHORIZATION = new ErrorCode(BusinessCode.PERMISSIONS, 2, "没有权限访问该url!");
    /**
     * 系统的相关控制
     */
    public static final ErrorCode INVALID_PARAMETER = new ErrorCode(BusinessCode.SYSTEM, 1, "参数错误！");
    public static final ErrorCode MISS_PARAMETER = new ErrorCode(BusinessCode.SYSTEM, 2, "缺少参数!");
    public static final ErrorCode RESOURCE_NOT_FOUNT = new ErrorCode(BusinessCode.SYSTEM, 3, "请求的资源不存在或已删除!");
    public static final ErrorCode PERSISTENT_DATA_ERROR = new ErrorCode(BusinessCode.SYSTEM, 4, "数据有误!");
    public static final ErrorCode UPDATE_FAIL = new ErrorCode(BusinessCode.SYSTEM, 5, "修改数据失败!");
    public static final ErrorCode DATA_NOT_FOUND = new ErrorCode(BusinessCode.SYSTEM, 106, "数据未找到");

    /**
     * 默认的系统控制
     */
    public static final ErrorCode DEFAULT_ERROR = new ErrorCode(BusinessCode.DEFAULT, "请求异常!");
    /**
     * 系统默认异常处理
     */
    public static final ErrorCode SQL_EXCEPTION = new ErrorCode(BusinessCode.SYSTEM, 101, "SQL语法错误异常");
    public static final ErrorCode CLASS_CAST_EXCEPTION = new ErrorCode(BusinessCode.SYSTEM, 102, "类型强制异常");
    public static final ErrorCode ARR_EXCEPTION = new ErrorCode(BusinessCode.SYSTEM, 103, "数组下标越界异常");
    public static final ErrorCode FILE_NOT_FOUND_EXCEPTION = new ErrorCode(BusinessCode.SYSTEM, 104, "文件未找到异常");
    public static final ErrorCode IO_EXCEPTION = new ErrorCode(BusinessCode.SYSTEM, 105, "文件操作失败");
    public static final ErrorCode NULL_EXCEPTION = new ErrorCode(BusinessCode.SYSTEM, 106, "空指针异常");
    public static final ErrorCode TOKEN_INVALID = new ErrorCode(BusinessCode.SYSTEM, 107, "token无效");
    public static final ErrorCode INVALID_FORMAT_EXCEPTION = new ErrorCode(BusinessCode.SYSTEM, 108, "格式转换异常");
    public static final ErrorCode DUPLICATE_KEY_EXCEPTION = new ErrorCode(BusinessCode.SYSTEM, 109, "数据重复异常");
    public static final ErrorCode SYSTEM_ERROR = new ErrorCode(BusinessCode.SYSTEM, 110, "系统错误，请联系管理员！");
    /**
     * 错误编码
     */
    private final int code;

    /**
     * 错误信息
     */
    private final String message;

    /**
     * 根据业务编码生成错误信息
     *
     * @param businessCode 业务编码
     */
    public ErrorCode(final BusinessCode businessCode, final String message) {
        if (businessCode.getBusinessCode() == BusinessCode.SUCCESS.getBusinessCode()) {
            this.code = businessCode.getBusinessCode();
            this.message = message;
        } else {
            this.message = message;
            if (businessCode.getBusinessCode() != 1) {
                this.code = businessCode.getBusinessCode() * 1000;
            } else {
                this.code = businessCode.getBusinessCode();
            }
        }
    }

    /**
     * 根据业务编码生成错误信息
     *
     * @param businessCode 业务编码
     * @param trail        具体错误路径
     */
    public ErrorCode(final BusinessCode businessCode, final int trail) {
        this.message = null;
        this.code = initCode(businessCode, trail);
    }

    /**
     * 根据业务编码生成错误信息
     *
     * @param businessCode 业务编码
     * @param trail        错误路径
     * @param message      错误详细信息
     */
    public ErrorCode(final BusinessCode businessCode, final int trail, final String message) {
        this.message = message;
        this.code = initCode(businessCode, trail);
    }

    /**
     * 在解析接口返回的json数据时， 如果返回错误码， 需要用到， 其余情况下不要使用
     *
     * @param businessCode 错误代码
     * @param message      错误信息
     */
    public ErrorCode(final int businessCode, final String message) {
        this.code = businessCode;
        this.message = message;
    }

    /**
     * 构造业务编码
     *
     * @param businessCode 业务编码
     * @param identity     错误编码
     * @return
     */
    private Integer initCode(final BusinessCode businessCode, final int identity) {
        return Integer.valueOf(businessCode.getBusinessCode());
    }

    /**
     * 错误编码
     *
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     * 错误信息
     *
     * @return
     */
    public String getMessage() {
        return message;
    }
}
