package swan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年2月13日 下午2:29:23
 */
public class ContextLoader {

	private static final String STRING = "\n";

	public static void main(String[] args) throws Exception {
		String file = "/home/lf/Temp.txt";
		String dir = "/home/lf/workspace/NetCore/src/test/java/net/demo/netty/udt/rendezvousBytes/";
		String name = "ByteEchoPeerTwo";
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		FileWriter writer = new FileWriter(new File(dir, name + ".java"), false);
		try {
			writer.write("package net.demo.netty.udt.rendezvousBytes;\n");
			
			String line = null;
			long lineCount = 0;
			int row = 1;
			while((line = reader.readLine()) != null) {
				if(lineCount > 0 && lineCount < 10) {
					row = 2;
				} else if(lineCount > 10 && lineCount < 90) {
					row = 2;
				} else if(lineCount > 90 && lineCount < 1000) {
					row = 3;
				} else if(lineCount > 1000 && lineCount < 10000) {
					row = 4;
				}
				if(line.length() > 2) {
					writer.write(line.substring(row) + STRING);
				} else {
					writer.write(line + STRING);
				}
				lineCount++;
			}
			System.out.println("ok");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			writer.flush();
			writer.close();
			writer = null;
			
			reader.close();
			reader = null;
		}
	}
}
