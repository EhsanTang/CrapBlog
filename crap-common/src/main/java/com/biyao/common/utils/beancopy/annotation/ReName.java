/** 
  * Copyright @ 2016  shuibian Co. Ltd. 
  * All right reserved. 
  * @author: Lijiannan 
  * date: 	 2016年7月16日上午11:41:10 
  */
package com.biyao.common.utils.beancopy.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description beancopy 时将src_bean中相应属性的值赋值到该field上
 * @author		Lijiannan
 * @time   		2016年7月16日上午11:41:10
 * @version 	1.0
 */
@Documented
@Inherited
@Target({ java.lang.annotation.ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ReName {
	public String value();
}
