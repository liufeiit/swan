package swan;

import java.util.List;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;

/**
 * http://developer.51cto.com/art/201007/208812_all.htm
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年1月28日 上午11:23:52
 */
public class JsEngine {

	public static void mainsas(String[] args) {
		ScriptEngineManager manager = new ScriptEngineManager();  
        List<ScriptEngineFactory> factories = manager.getEngineFactories();  
        for (ScriptEngineFactory f : factories) {  
            System.out.println(  
                    "\negine name:"+f.getEngineName()+  
                    ",\nengine version:"+f.getEngineVersion()+  
                    ",\nlanguage name:"+f.getLanguageName()+  
                    ",\nlanguage version:"+f.getLanguageVersion()+  
                    ",\nnames:"+f.getNames()+  
                    ",\nmime:"+f.getMimeTypes()+  
                    ",\nextension:"+f.getExtensions());  
        } 
	}

	public static void main5(String[] args) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		String script = "print ('hello script')";
		try {
			engine.eval(script);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
	
	public static void main11(String[] args) throws Exception { 
		String script=" println(greeting) "; 
		ScriptEngineManager manager = new ScriptEngineManager(); 
		ScriptEngine engine = manager.getEngineByName("javascript"); 
		//Attribute from ScriptEngineManager 
		manager.put("greeting", "Hello from ScriptEngineManager"); 
		engine.eval(script); //Attribute from ScriptEngine 
		engine.put("greeting", "Hello from ScriptEngine"); 
		engine.eval(script); //Attribute from eval method 
		ScriptContext context = new SimpleScriptContext(); 
		context.setAttribute("greeting", "Hello from eval method", ScriptContext.ENGINE_SCOPE); 
		engine.eval(script, context); 
	}

	public static void main(String[] args) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		try {
			ScriptContext context = new SimpleScriptContext();
			context.setAttribute("a", 4, ScriptContext.ENGINE_SCOPE);
			context.setAttribute("b", 6, ScriptContext.ENGINE_SCOPE);
			String s = "function max_num(a,b){return (a>b)?a:b;}max_num(a,b);";
			Object maxNum = engine.eval(s, context);
			System.out.println("max_num:" + maxNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main3(String[] args) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		try {
			engine.eval("function max_num(a,b){return (a>b)?a:b;}");
			Invocable invoke = (Invocable) engine;
			Object maxNum = invoke.invokeFunction("max_num", 4, 6);
			System.out.println(maxNum);
			maxNum = invoke.invokeFunction("max_num", 7, 6);
			System.out.println(maxNum);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public interface JSLib {
		public int max_num(int a, int b);
	}

	public static void main2(String[] args) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		try {
			engine.eval("function max_num(a,b){return (a>b)?a:b;}");
			Invocable invoke = (Invocable) engine;
			JSLib jslib = invoke.getInterface(JSLib.class);
			int maxNum = jslib.max_num(4, 6);
			System.out.println(maxNum);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void main1(String[] args) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		try {
			String script = "var list = java.util.ArrayList();list.add(\"kafka0102\");print(list.get(0));";
			engine.eval(script);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main0(String[] args) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		try {
			Compilable compEngine = (Compilable) engine;
			CompiledScript script = compEngine.compile("function max_num(a,b){return (a>b)?a:b;}");
			script.eval();
			Invocable invoke = (Invocable) engine;
			Object maxNum = invoke.invokeFunction("max_num", 4, 6);
			System.out.println(maxNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
