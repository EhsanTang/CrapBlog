/** 
 * Copyright @ 2016  shuibian Co. Ltd. 
 * All right reserved. 
 * @author: Lijiannan 
 * date: 	 2016年7月16日下午12:01:38 
 */
package cn.crap.common.utils.beancopy;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import cn.crap.common.utils.beancopy.annotation.AnnoPropertyDescriptor;
import cn.crap.common.utils.beancopy.annotation.ReName;


/**
 * @description Unfinished
 * @author Lijiannan
 * @time 2016年7月16日下午12:01:38
 * @version 1.0
 */
public class BeanCopyUtil {

	public static void copyProperties(Object source, Object target) {
		copyProperties(source, target, (String[])null);
	}

	public static void copyProperties(Object source, Object target, String... ignoreProperties) {
		if (source == null || target == null) {
			throw new RuntimeException("source and target cann't be null");
		}
		Class<?> targetClass = target.getClass();
		try {
			AnnoPropertyDescriptor[] targetPds = ByPropertyDescriptorManager.getPropertyDescriptors(targetClass);
			List<String> ignoreList = (ignoreProperties != null) ? Arrays.asList(ignoreProperties) : null;
			for (AnnoPropertyDescriptor targetPd : targetPds) {
				String readName = targetPd.getPropertyDescriptor().getName();
				ReName reNameAnno = targetPd.getRenameAnno();
				if (reNameAnno != null && reNameAnno.value() != null) {
					readName = reNameAnno.value();
				}
				Method writeMethod = targetPd.getPropertyDescriptor().getWriteMethod();
				if (writeMethod != null && (ignoreProperties == null || (!ignoreList.contains(targetPd.getPropertyDescriptor().getName())))) {
					AnnoPropertyDescriptor sourcePd = ByPropertyDescriptorManager.getPropertyDescriptor(source.getClass(), readName);
					if (sourcePd != null) {
						Method readMethod = sourcePd.getPropertyDescriptor().getReadMethod();
						
						//  检查source.get()的返回类型和target.write()的参数类型是否一致

						if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
							readMethod.setAccessible(true);
						}
						Object value = readMethod.invoke(source);
						if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
							writeMethod.setAccessible(true);
						}
						writeMethod.invoke(target, value);

					}
				}
			}
		} catch (Throwable ex) {
			throw new RuntimeException("Could not copy property from source to target", ex);
		}
	}

}
