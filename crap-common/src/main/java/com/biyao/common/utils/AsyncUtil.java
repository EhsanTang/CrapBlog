/** 
 * Copyright @ 2016  shuibian Co. Ltd. 
 * All right reserved. 
 * @author: Lijiannan 
 * date: 	 2016年9月9日上午11:44:00 
 */
package com.biyao.common.utils;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

import org.apache.log4j.Logger;

/**
 * @description 该工具用来异步调用方法
 * 该工具提供两个方法可实现异步调用：<br/>
 *   {@linkplain #asyncInvoke(ExecutorService,Object,String,Object[]) asyncInvoke} {@code (ExecutorService,  Object, String, Object[])}<br/>
 *   {@linkplain #asyncInvoke(ExecutorService,Object,Method,Object[]) asyncInvoke} {@code (ExecutorService,  Object, Method, Object[])}
 *   
 * @author Lijiannan
 * @time 2016年9月9日上午11:44:00
 * @version 1.0
 */
public class AsyncUtil {

	private static Logger log = Logger.getLogger(AsyncUtil.class);
	

	
	/**
	 * @Description  异步调用方法；
	 * <p><b>注：  无法掉用以接口或抽象类为参数声明，实际调用参数为其实现类的函数;对此类函数请调用{@linkplain #asyncInvoke(ExecutorService,Object,Method,Object[]) asyncInvoke} {@code (ExecutorService,  Object, Method, Object[])}</b></p>
	 * 例如：对于方法  Dao.select(List ids)<br/>使用本方法异步调用AsyncUtil.asyncInvoke(es,obj,"select",new Object[]{new Arraylist()})时,会调用失败
	 * 
	 * @param es 异步调用调用的线程池 非空
	 * @param callObj 异步调用的实例 非空
	 * @param methodName 异步调用的方法 非空
	 * @param params 异步调用的参数 非空
	 */
	public static void asyncInvoke(ExecutorService es,  Object callObj, String methodName, Object[] params) {
		try {
			@SuppressWarnings("rawtypes")
			Class[] paramClasses = new Class[params.length];

			for (int i = 0; i < params.length; i++) {
				paramClasses[i] = params[i].getClass();
			}

			Method method = callObj.getClass().getMethod(methodName, paramClasses);
			es.submit(new AsyncInvokeCall(callObj, method, params));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Invoke method  "+ methodName + " Failed", e);
		}
	}

	
	/**
	 * @Description  异步调用方法 ;当无法通过{@linkplain #asyncInvoke(ExecutorService,Object,String,Object[]) asyncInvoke} {@code (ExecutorService,  Object, String, Object[])}
	 * 实现异步调用时,可使用此方法。<br/>
	 * @param es 异步调用调用的线程池 非空
	 * @param callObj 异步调用的实例 非空
	 * @param methodName 异步调用的方法 非空
	 * @param params 异步调用的参数 非空
	 */
	public static void asyncInvoke(ExecutorService es, Object callObj, Method method, Object[] params) {
		try {
			es.submit(new AsyncInvokeCall(callObj, method, params));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Invoke method  "+ method.getName() + " Failed", e);
		}
	}

	private static class AsyncInvokeCall implements Callable<Object> {
		private Object callObj;

		private Method method;

		private Object[] params;

		/**
		 * Constructor
		 */
		public AsyncInvokeCall(Object callObj, Method method, Object[] params) {
			this.callObj = callObj;
			this.method = method;
			this.params = params;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.concurrent.Callable#call()
		 */
		@Override
		public Object call() throws Exception {
			try {
				method.invoke(callObj, params);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("call  ShopcarAsync  Mysql Failed", e);
			}
			return null;
		}

	}

}
