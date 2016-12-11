/** 
  * Copyright @ 2016  shuibian Co. Ltd. 
  * All right reserved. 
  * @author: Lijiannan 
  * date: 	 2016年7月18日下午5:33:46 
  */
package cn.crap.common.utils.beancopy;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;

import cn.crap.common.utils.beancopy.annotation.AnnoPropertyDescriptor;



/**
 * @description bean 属性管理器
 * @author		Lijiannan
 * @time   		2016年7月18日下午5:33:46
 * @version 	1.0
 */
public class ByPropertyDescriptorManager {

	private static ConcurrentHashMap<Class<?>, Reference<AnnoPropertyDescriptor[]>> descriptorCache = new ConcurrentHashMap<Class<?>, Reference<AnnoPropertyDescriptor[]>>();
	
	/**
	 * @Description 根据class获得 class单个属性描述
	 * @param class0
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	public static AnnoPropertyDescriptor getPropertyDescriptor(Class<?> class0, String name) throws Exception {
		AnnoPropertyDescriptor[] descriptorList = getPropertyDescriptors(class0);
		if(descriptorList!=null){
			for(AnnoPropertyDescriptor annoPropertyDescriptor : descriptorList){
				if(name.equals(annoPropertyDescriptor.getPropertyDescriptor().getName())){
					return annoPropertyDescriptor;
				}
			}
		}
		return null;
	}

	/**
	 * @Description 根据class获得class属性描述
	 * @param targetClass
	 * @return
	 * @throws Exception 
	 */
	public static AnnoPropertyDescriptor[] getPropertyDescriptors(Class<?> targetClass) throws Exception {
		Reference<AnnoPropertyDescriptor[]> descriptorsReference = descriptorCache.get(targetClass);
		AnnoPropertyDescriptor[] descriptorList;
		if(descriptorsReference == null || descriptorsReference.get() == null){
			descriptorList = getDescriptorList(targetClass);
			descriptorsReference = new SoftReference<AnnoPropertyDescriptor[]>(descriptorList);
			descriptorCache.put(targetClass, descriptorsReference);
		}else{
			descriptorList = descriptorsReference.get();
		}
		return descriptorList;
	}

	/**
	 * @Description 根据class获取 AnnoPropertyDescriptor
	 * @param targetClass
	 * @return
	 * @throws IntrospectionException 
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	private static AnnoPropertyDescriptor[] getDescriptorList(Class<?> targetClass) throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(targetClass);
		PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
		AnnoPropertyDescriptor[] annoPropertyDescriptors = new AnnoPropertyDescriptor[descriptors.length];
		for(int i=0 ; i<descriptors.length ; i++){
			annoPropertyDescriptors[i] = new AnnoPropertyDescriptor(targetClass,descriptors[i]);
		}
		return annoPropertyDescriptors;
	}
}
