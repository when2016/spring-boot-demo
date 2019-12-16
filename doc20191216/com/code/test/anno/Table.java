package com.code.test.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author wanghongen
 * @date 8/24/18 5:58 PM
 */
@Target(ElementType.ANNOTATION_TYPE)
public @interface Table {

  public String tableName() default "className";
}
