package swan;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年2月18日 下午4:25:23
 */
public class GsonUtil {
	public static Gson gson = new GsonBuilder().create();
	public static Gson gsonExpose = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	// public static Gson gsonDefault=new
	// GsonBuilder().registerTypeAdapter(Date.class,new DateAdapter()).create();
	public static Gson gsonPrettyPrinting = new GsonBuilder().setPrettyPrinting().create();
	
	public static void main(String[] args) {
		Person p1 = new Person();
		p1.setId(10);
		p1.setName("liufei1");
		p1.setPassdword("liufei1229");
		p1.setInfo("lll");
		
		Person p2 = new Person();
		p2.setId(11);
		p2.setName("liufei122");
		p2.setPassdword("liufei122229");
		p2.setInfo("222");
		
		Person p3 = new Person();
		p3.setId(113);
		p3.setName("liufei1322");
		p3.setPassdword("liufei12222933");
		p3.setInfo("333");
		
		Person p4 = new Person();
		p4.setId(113);
		p4.setName("liufei1322");
		p4.setPassdword("liufei12222933");
		p4.setInfo("333");
		
//		p1.addFriend(p2, p3, p4);
//		p2.addFriend(p1, p3, p4);
//		p3.addFriend(p1, p2, p4);
//		p4.addFriend(p1, p2, p3);
		
		String jsonp1 = gson.toJson(p1);
		String jsonp2 = gson.toJson(p2);
		String jsonp3 = gson.toJson(p3);
		String jsonp4 = gson.toJson(p4);
		
		System.out.println("jsonp1 : " + jsonp1);
		System.out.println("jsonp2 : " + jsonp2);
		System.out.println("jsonp3 : " + jsonp3);
		System.out.println("jsonp4 : " + jsonp4);
		
		Person _p1 = gson.fromJson(jsonp1, Person.class);
		Person _p2 = gson.fromJson(jsonp2, Person.class);
		Person _p3 = gson.fromJson(jsonp3, Person.class);
		Person _p4 = gson.fromJson(jsonp4, Person.class);
		
		System.out.println("_p1 : " + _p1);
		System.out.println("_p2 : " + _p2);
		System.out.println("_p3 : " + _p3);
		System.out.println("_p4 : " + _p4);
	}
	
	public static class Person {
		private long id;
		private String name;
		private String passdword;
		private String info;
		private List<Person> friend;
		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
		}
		public void addFriend(Person... persons) {
			if(friend == null) {
				friend = new ArrayList<Person>();
			}
			for (Person person : persons) {
				friend.add(person);
			}
		}
		/**
		 * @return the id
		 */
		public long getId() {
			return id;
		}
		/**
		 * @param id the id to set
		 */
		public void setId(long id) {
			this.id = id;
		}
		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * @return the passdword
		 */
		public String getPassdword() {
			return passdword;
		}
		/**
		 * @param passdword the passdword to set
		 */
		public void setPassdword(String passdword) {
			this.passdword = passdword;
		}
		/**
		 * @return the info
		 */
		public String getInfo() {
			return info;
		}
		/**
		 * @param info the info to set
		 */
		public void setInfo(String info) {
			this.info = info;
		}
		/**
		 * @return the friend
		 */
		public List<Person> getFriend() {
			return friend;
		}
		/**
		 * @param friend the friend to set
		 */
		public void setFriend(List<Person> friend) {
			this.friend = friend;
		}
	}
}
/*
 * Gson gson = new GsonBuilder() .excludeFieldsWithoutExposeAnnotation()
 * //不导出实体中没有用@Expose注解的属性 .enableComplexMapKeySerialization()
 * //支持Map的key为复杂对象的形式
 * .serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")//时间转化为特定格式
 * .setFieldNamingPolicy
 * (FieldNamingPolicy.UPPER_CAMEL_CASE)//会把字段首字母大写,注:对于实体上使用了
 * @SerializedName注解的不会生效. .setPrettyPrinting() //对json结果格式化. .setVersion(1.0)
 * //有的字段不是一开始就有的,会随着版本的升级添加进来,那么在进行序列化和返序列化的时候就会根据版本号来选择是否要序列化.
 * //@Since(版本号)能完美地实现这个功能.还的字段可能,随着版本的升级而删除,那么
 * //@Until(版本号)也能实现这个功能,GsonBuilder.setVersion(double)方法需要调用. .create();
 */

/*
 * @Expose标签的2个属性.
 * 
 * 1.1 deserialize (boolean) 反序列化 默认 true 1.2 serialize (boolean) 序列化 默认 true 使用
 * new
 * GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();创建Gson对象，没有@
 * Expose注释的属性将不会被序列化
 */

/*
 * 
 * @SerializedName("name") private String username;
 */