package com.ybj.mydagger2demo.ano;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by 杨阳洋 on 2017/12/31.
 */

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface Release {

}
