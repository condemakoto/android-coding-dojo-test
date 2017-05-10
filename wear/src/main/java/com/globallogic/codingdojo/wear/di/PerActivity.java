package com.globallogic.codingdojo.wear.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author Julio Kun
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
