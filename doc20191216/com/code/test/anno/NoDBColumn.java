package com.code.test.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author wanghongen
 * @date 8/24/18 6:01 PM
 */
@Target(ElementType.FIELD)
public @interface NoDBColumn {

}
