package com.wangxile.apiidempotent.service;

import com.wangxile.apiidempotent.common.ResultData;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:wangqi
 * @Description:
 * @Date:Created in 2019/10/16
 * @Modified by:
 */
public interface TokenService {

     ResultData createToken();

     void checkToken(HttpServletRequest request);
}
