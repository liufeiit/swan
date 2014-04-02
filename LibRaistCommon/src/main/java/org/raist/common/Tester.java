package org.raist.common;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import org.raist.common.permutation.Combination;
import org.raist.common.permutation.Permutation;
import org.springframework.util.DigestUtils;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年4月2日 下午6:48:02
 */
public class Tester {
	
	public static void main(String[] args) {
		List<String> src = Arrays.asList(
				"\"offline_game_invite\":\"\"", 
				"\"type\":1", 
				"\"stream_url\":\"\"",
				"\"from_user_id\":1404181",
				"\"content\":\"苦逼的等\"",
				"\"created_at\":\"2014-04-01T20:40:50+0800\"",
				"\"wallpaper\":\"\"",
				"\"click_url\":\"\"",
				"\"content_type\":0");
		
		String val_0 = "body={";
		String val_1 = "}from=nova1404181@ruoogle/807162to=waitingroom_2@conference.ruoogletype=groupchatkey=2esc%RGV7yjn)IKN*LB^bmgkldjh5903$#";
		long c = 0;
		Combination<String> comb = Combination.of(src, 9);
		Permutation<String> prem = Permutation.of(src, 9);
		for (List<String> l : prem) {
			String v = toString(l);
			String val = val_0 + v + val_1;
			if ("eec7e3c8984cfcc7f7cebbebf4c51bb6".equals(sign(val))) {
				System.out.println("ok,找到你了吧。。。");
				System.out.println(val);
			}
			c++;
		}
		System.out.println("次数：" + c + " = " + (9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1));
	}
	
	private static String toString(List<String> src) {
		String string = "";
		int len = src.size();
		for(int i = 0 ; i < len ; i++) {
			if(i == len - 1) {
				string += src.get(i);
			} else {
				string += (src.get(i) + ",");
			}
		}
		return string;
	}
	
	public static void main11(String[] args) throws Exception {
		LineNumberReader reader = new LineNumberReader(new FileReader("/home/lf/shell/error.log"));
		FileWriter writer = new FileWriter("/home/lf/shell/error-g.txt", true);
		try {
			long lenMex = Long.MIN_VALUE;
			long lenMin = Long.MAX_VALUE;
			String line = reader.readLine();
			while(line != null && !line.isEmpty()) {
				line = reader.readLine();
				if(line.contains("sign error message")) {
					lenMex = line.length();
					if(lenMin > lenMex) {
						lenMin = lenMex;
						writer.write("\n" + line + "\n\n");
					}
				}
			}
			writer.flush();
		} finally {
			reader.close();reader = null;
			writer.close();writer = null;
		}
	}

	public static String sign(String val) {
		String signature = null;
		try {
			signature = DigestUtils.md5DigestAsHex(val.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return signature;
	}
}
