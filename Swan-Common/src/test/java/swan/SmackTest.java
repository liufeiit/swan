package swan;

import java.util.Calendar;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.XMPPConnection;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年2月14日 上午11:46:52
 */
public class SmackTest {

	public static void main(String[] args) throws Exception {
		ConnectionConfiguration config = new ConnectionConfiguration("app01", 5222);
		XMPPConnection connection = new XMPPConnection(config);
		connection.connect();
		if (!connection.isAuthenticated()) {
			connection.login("admin_back_ucloud_app02_1@ruoogle", "admin_back_ucloud_app02_1",
					String.valueOf(Calendar.getInstance().getTime()));
		}
		connection.addConnectionListener(new ConnectionListener() {
			@Override
			public void reconnectionSuccessful() {
				System.out.println("reconnectionSuccessful");
			}

			@Override
			public void reconnectionFailed(Exception arg0) {
				arg0.printStackTrace();
			}

			@Override
			public void reconnectingIn(int arg0) {
				System.out.println("reconnectingIn");
			}

			@Override
			public void connectionClosedOnError(Exception arg0) {
				arg0.printStackTrace();
			}

			@Override
			public void connectionClosed() {
				System.out.println("connectionClosed");
			}
		});
		System.out.println(connection.isConnected());
		System.out.println(connection.isAuthenticated());
		System.out.println(connection.getUser());
	}
}
