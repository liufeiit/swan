package org.swan.web;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年3月26日 下午3:03:04
 */
public class Matrix {
	
	public static void main(String[] args) {
		ClassLoader classLoader = Matrix.class.getClassLoader();
		Service service1 = (Service) Proxy.newProxyInstance(classLoader, DefaultService.class.getInterfaces(), new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("proxy : " + proxy.getClass());
				System.out.println("method : " + method);
				System.out.println("args : " + Arrays.toString(args));
				return method.invoke(new DefaultService(), args) + "哈哈";
			}
		});
		System.out.println(service1.name("刘飞啊"));
		InvocationHandler handler = Proxy.getInvocationHandler(service1);
		Service service2 = (Service) Proxy.newProxyInstance(classLoader, DefaultService.class.getInterfaces(), handler);
		System.out.println(service2.name("kk"));
	}
	
	public static class DefaultService implements Service {
		public String name(String name) {
			return name + "liufei";
		}
	}
	
	public static interface Service {
		String name(String name);
	}
}