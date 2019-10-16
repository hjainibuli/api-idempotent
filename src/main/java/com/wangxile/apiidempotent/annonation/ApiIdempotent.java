package com.wangxile.apiidempotent.annonation;

/**
 * @Author:wangqi
 * @Description:
 * @Date:Created in 2019/10/16
 * @Modified by: 在需要保证 接口幂等性 的Controller的方法上使用此注解
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiIdempotent {

}
