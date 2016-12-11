/** 
 * Copyright @ 2016  shuibian Co. Ltd. 
 * All right reserved. 
 * @author: Lijiannan 
 * date: 	 2016年7月18日下午4:19:02 
 */
package com.biyao.common.utils.beancopy.annotation;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

/**
 * @description
 * @author Lijiannan
 * @time 2016年7月18日下午4:19:02
 * @version 1.0
 */
public class AnnoPropertyDescriptor {

	private ReName renameAnno;
	private PropertyDescriptor propertyDescriptor;

	/**
	 * @return the propertyDescriptor
	 */
	public PropertyDescriptor getPropertyDescriptor() {
		return propertyDescriptor;
	}

	/**
	 * @return the renameAnno
	 */
	public ReName getRenameAnno() {
		return renameAnno;
	}

	/**
	 * Constructor
	 * 
	 * @param bean
	 * @throws IntrospectionException
	 */
	public AnnoPropertyDescriptor(Class<?> bean, PropertyDescriptor p) throws IntrospectionException, NoSuchFieldException, SecurityException {
		propertyDescriptor = p;
		if (!"class".equals(p.getName())) {
			Field field = bean.getDeclaredField(p.getName());
			if (field != null) {
				renameAnno = field.getAnnotation(ReName.class);
			}
		}
	}

}
