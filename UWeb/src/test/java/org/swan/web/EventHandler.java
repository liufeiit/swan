package org.swan.web;

import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.event.EventCartridge;
import org.apache.velocity.app.event.IncludeEventHandler;
import org.apache.velocity.app.event.InvalidReferenceEventHandler;
import org.apache.velocity.app.event.MethodExceptionEventHandler;
import org.apache.velocity.app.event.NullSetEventHandler;
import org.apache.velocity.app.event.ReferenceInsertionEventHandler;
import org.apache.velocity.context.Context;
import org.apache.velocity.util.introspection.Info;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年3月18日 下午5:13:16
 */
public class EventHandler implements 
	ReferenceInsertionEventHandler, 
	IncludeEventHandler, 
	InvalidReferenceEventHandler,
	MethodExceptionEventHandler,
	NullSetEventHandler {
	public static void main(String args[]) {
		new EventHandler();
	}

	public EventHandler() {
		Velocity.init();
		VelocityContext context = new VelocityContext();
		context.put("name", "Velocity");
		EventCartridge ec = new EventCartridge();
		// 注册事件处理器
		ec.addEventHandler(this);
		// 将注册的事件处理器与context容器进行绑定
		ec.attachToContext(context);
		try {
			System.out.println("");
			System.out.println("Velocity事件处理样例");
			System.out.println("============================");
			System.out.println("");
			String s = "打印name:$name, #include('/home/lf/jdk.txt')\n, #parse('/home/lf/jdk.txt')";
			StringWriter w = new StringWriter();
			Velocity.evaluate(context, w, "mystring", s);
			System.out.println("渲染结果 : ");
			System.out.println("   " + w.toString());
			System.out.println("");
			s = "模板文件 中引用后台未定义的对象$floobie , $nullvalue";
			w = new StringWriter();
			Velocity.evaluate(context, w, "mystring", s);
			System.out.println("空引用测试: ");
			System.out.println("   " + w.toString());
		} catch (Exception e) {
			System.out.println("Exception : " + e);
		}
	}

	@Override
	public String includeEvent(String includeResourcePath, String currentResourcePath, String directiveName) {
		System.out.println("IncludeEventHandler");
		System.out.println("includeResourcePath : " + includeResourcePath);
		System.out.println("currentResourcePath : " + currentResourcePath);
		System.out.println("directiveName : " + directiveName);
//		return "notfound.vm";
		return null;
	}

	// 模板渲染时在将后台对象插入模板引用之前调用
	public Object referenceInsert(String reference, Object value) {
		System.out.println("ReferenceInsertionEventHandler");
		System.out.println("reference : " + reference);
		String s = null;
		if (value != null) {
			s = "渲染之前 " + value.toString() + "渲染完成";
		} else {
			if (reference.substring(1).equals("floobie")) {
				s = "<no floobie value>";
			}
		}
		// 将value提交给模板中引用value的地方使用
		return s;
	}

	@Override
	public boolean shouldLogOnNullSet(String lhs, String rhs) {
		System.out.println("NullSetEventHandler");
		System.out.println("lhs : " + lhs);
		System.out.println("rhs : " + rhs);
		return true;
	}

	@Override
	public Object methodException(Class clazz, String method, Exception e) throws Exception {
		System.out.println("MethodExceptionEventHandler");
		System.out.println("clazz : " + clazz);
		System.out.println("method : " + method);
		e.printStackTrace();
		return null;
	}

	@Override
	public Object invalidGetMethod(Context context, String reference, Object object, String property, Info info) {
		System.out.println("InvalidReferenceEventHandler.invalidGetMethod");
		System.out.println("context : " + context);
		System.out.println("reference : " + reference);
		System.out.println("object : " + object);
		System.out.println("property : " + property);
		System.out.println("info : " + info);
		return null;
	}

	@Override
	public boolean invalidSetMethod(Context context, String leftreference, String rightreference, Info info) {
		System.out.println("InvalidReferenceEventHandler.invalidSetMethod");
		System.out.println("context : " + context);
		System.out.println("leftreference : " + leftreference);
		System.out.println("rightreference : " + rightreference);
		System.out.println("info : " + info);
		return false;
	}

	@Override
	public Object invalidMethod(Context context, String reference, Object object, String method, Info info) {
		System.out.println("InvalidReferenceEventHandler.invalidMethod");
		System.out.println("context : " + context);
		System.out.println("reference : " + reference);
		System.out.println("object : " + object);
		System.out.println("info : " + info);
		return null;
	}
}