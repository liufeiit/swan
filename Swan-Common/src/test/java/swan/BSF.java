//package swan;
//
///**
// * 
// * @author 刘飞 E-mail:liufei_it@126.com
// * @version 1.0
// * @since 2014年2月11日 下午2:26:08
// */
//public class BSF {
//	public static void main(String[] args) {
//
//		// Script脚本
//		String script = "function dealBean()" + "{" + " str = bsf.lookupBean('u');"
//				+ "return \"ID:\"+str.id+\"用户名:\"+str.uname+\"地址:\"+str.address+\"_调用方法:\"+str.toString()+"
//				+ "'\t'+\"的bean内容被获得了;\";" + "}";
//
//		try {
//			// BSF管理器
//			BSFManager bsfManager = new BSFManager();
//
//			// BSF引擎
//			BSFEngine bsfEngine = bsfManager.loadScriptingEngine("javascript");
//
//			/**
//			 * Bean
//			 */
//			User u = new User();
//			u.setId(1);
//			u.setUname("archie");
//			u.setAddress("上海松江");
//
//			// 执行Script脚本
//			bsfEngine.eval("javascript", 0, 0, script);
//
//			// BSFManager注册一个Bean
//			bsfManager.registerBean("u", u);
//			// bsfManager.registerBean("u", "archie");
//
//			// 执行脚本中方法并返回
//			Object result = bsfEngine.eval("javascript", 0, 0, "dealBean();");
//
//			System.out.println(result.toString());
//
//		} catch (BSFException e) {
//			e.printStackTrace();
//		}
//	}
//}
