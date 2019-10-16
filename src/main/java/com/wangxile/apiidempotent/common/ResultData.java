package com.wangxile.apiidempotent.common;


import java.io.Serializable;

import static com.wangxile.apiidempotent.common.ErrorCode.DEFAULT_ERROR;


/**
 * @author: Xiongxz
 * @Date: 2018/7/10 21:56
 * @Description: 封装返回结果集
 */
public class ResultData<T> implements Serializable {
    private static final long serialVersionUID = 7000760666908532411L;

    public static final String INSERT_SUCCESS = "保存成功";
    public static final String UPDATE_SUCCESS = "修改成功";
    public static final String DELETE_SUCCESS = "删除成功";

    private Integer resultCode;

    private String resultMsg;

    private T result;

    public T getResult() {
        return result;
    }

    public void setResult(final T result) {
        this.result = result;
    }

    public ResultData() {

    }

    /**
     * 构造函数
     *
     * @param resultCode 响应码
     */
    public ResultData(final int resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * 构造函数
     *
     * @param resultCode 响应码
     * @param resultMsg  响应消息
     */
    public ResultData(final int resultCode, final String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    /**
     * 构造函数
     *
     * @param resultCode 响应码
     * @param resultMsg  响应消息
     * @param result     响应数据
     */
    public ResultData(final int resultCode, final String resultMsg, final T result) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.result = result;
    }

    /**
     * 成功
     *
     * @return 成功响应
     */
    public static ResultData success() {
        return new ResultData(ErrorCode.SUCCESS.getCode(), "success");
    }

    /**
     * 成功
     *
     * @param result 响应数据
     * @return 成功响应
     */
    public static ResultData success(final Object result) {
        return new ResultData(ErrorCode.SUCCESS.getCode(), "success", result);
    }

    /**
     * 标准错误响应
     *
     * @param errorCode 响应码【枚举】
     * @return 错误响应
     */
    public static ResultData error(final ErrorCode errorCode) {
        return new ResultData(errorCode.getCode(), errorCode.getMessage());
    }

    /**
     * 标准错误响应
     *
     * @return 错误响应
     */
    public static ResultData error(Object result) {
        return new ResultData(ErrorCode.IO_EXCEPTION.getCode(), ErrorCode.IO_EXCEPTION.getMessage(), result);
    }

    /**
     * 默认错误响应
     *
     * @param message 响应消息
     * @return 错误响应
     */
    public static ResultData error(final String message) {
        return new ResultData(DEFAULT_ERROR.getCode(), message);
    }


    /**
     * 半自定义错误响应
     *
     * @param errorCode    响应码【枚举】
     * @param errorMessage 响应消息
     * @return 错误响应
     */
    public static ResultData error(final ErrorCode errorCode, final String errorMessage) {
        return new ResultData(errorCode.getCode(), errorMessage);
    }

    /**
     * 自定义错误响应
     *
     * @param errorCode 响应码
     * @param message   响应消息
     * @return 错误响应
     */
    public static ResultData error(final int errorCode, final String message) {
        return new ResultData(errorCode, message);
    }


    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(final String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public int getResultCode() {
        return resultCode;
    }
}
