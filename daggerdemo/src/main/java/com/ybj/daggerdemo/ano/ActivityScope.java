package com.ybj.daggerdemo.ano;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by 杨阳洋 on 2017/12/31.
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface ActivityScope {
}
