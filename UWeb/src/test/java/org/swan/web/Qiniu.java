package org.swan.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.rsf.ListItem;
import com.qiniu.api.rsf.ListPrefixRet;
import com.qiniu.api.rsf.RSFClient;
import com.qiniu.api.rsf.RSFEofException;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年3月18日 下午6:39:55
 */
public class Qiniu {

	public static void main(String[] args) throws Exception {
		Config.ACCESS_KEY = "rvreni1oYkEyICNJKjaEYnpz_neTIxTbSdvxpPDA";
		Config.SECRET_KEY = "dqRnqLaTePqVoUoYrp7gwhJWh-vrS6g3E0J4Z1Gn";

		Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
		// String baseUrl = URLUtils.makeBaseUrl("<domain>", "<key>");
		// GetPolicy getPolicy = new GetPolicy();
		// String downloadUrl = getPolicy.makeRequest(baseUrl, mac);

		RSFClient client = new RSFClient(mac);
		String marker = "";

		String bucketName1 = "ruoogle1";
		String bucketName2 = "mamabang";
		String bucketName3 = "nova";

		String baseURL = "http://staticnova.ruoogle.com/photo/1265667/201403181848022_619360_783x1044.jpg";
		String baseUR1 = "http://staticnova.ruoogle.com/photo/1279702/201403170521050_847457_234x234.jpg";

		int count = 0;

		ListPrefixRet ret = null;
		while (true) {
			ret = client.listPrifix(bucketName3, "photo", marker, 10);
			marker = ret.marker;

			if (ret.results != null && !ret.results.isEmpty()) {
				for (ListItem listItem : ret.results) {
					String imageURL = "http://staticnova.ruoogle.com/" + listItem.key;
					String name = imageURL.substring(imageURL.indexOf("/photo/") + "/photo/".length());
					String file = "/home/lf/photo/" + name;
					save(imageURL, file);
				}
			}
			count++;
			if (count > 100) {
				break;
			}
			if (!ret.ok()) {
				// no more items or error occurs
				break;
			}
		}
		if (ret.exception.getClass() != RSFEofException.class) {
			// error handler
		}
	}

	static void save(String url, String file) throws Exception {
		URL u = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) u.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5 * 1000);
		InputStream inStream = conn.getInputStream();
		byte[] data = readInputStream(inStream);
		File imageFile = new File(file);
		imageFile.getParentFile().mkdirs();
		FileOutputStream outStream = new FileOutputStream(imageFile);
		outStream.write(data);
		outStream.close();
	}

	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}
}
