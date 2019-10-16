package com.wangxile.apiidempotent.service.impl;

import com.wangxile.apiidempotent.common.ResultData;
import com.wangxile.apiidempotent.service.TokenService;
import com.wangxile.apiidempotent.util.JedisUtil;
import jdk.nashorn.internal.parser.Token;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:wangqi
 * @Description:
 * @Date:Created in 2019/10/16
 * @Modified by: 通过redis + token机制实现接口幂等性校验
 */
@Service
public class TokenServiceImpl implements TokenService {

    private static final String TOKEN_NAME = "token";

    private static final Integer EXPIRE_TIME_MINUTE = 3;

    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public ResultData createToken() {
        String str = "UUID";
        StringBuilder token = new StringBuilder();
        token.append(TOKEN_NAME).append(str);
        jedisUtil.set(token.toString(), token.toString(), EXPIRE_TIME_MINUTE);
        return ResultData.success(token.toString());
    }

    @Override
    public void checkToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_NAME);

        if (StringUtils.isBlank(token)) {
            // header中不存在token
            token = request.getParameter(TOKEN_NAME);
            if (StringUtils.isBlank(token)) {
                // parameter中也不存在token
                throw new IllegalArgumentException("参数错误");
            }
        }

        if (!jedisUtil.exists(token)) {
            throw new IllegalArgumentException("参数不合法或者是重复请求");
        }
        Long del = jedisUtil.del(token);
        if (del <= 0) {
            throw new IllegalArgumentException("删除失败");
        }
    }
}
