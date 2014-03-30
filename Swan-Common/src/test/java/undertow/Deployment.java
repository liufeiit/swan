package undertow;

import io.undertow.Undertow;
import io.undertow.Undertow.ListenerType;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.PathHandler;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ServletInfo;
import io.undertow.servlet.core.DeploymentManagerImpl;
import io.undertow.servlet.core.ServletContainerImpl;
import io.undertow.util.Headers;
import io.undertow.websockets.WebSocketConnectionCallback;
import io.undertow.websockets.client.WebSocketClient;
import io.undertow.websockets.core.AbstractReceiveListener;
import io.undertow.websockets.core.BufferedTextMessage;
import io.undertow.websockets.core.WebSocketChannel;
import io.undertow.websockets.core.WebSockets;
import io.undertow.websockets.spi.WebSocketHttpExchange;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.io.Connection;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年2月21日 下午3:35:28
 */
public class Deployment {
	
	public static void main01(final String[] args) {
        Undertow server = Undertow.builder()
                .addListener(8080, "localhost", ListenerType.HTTP)
                .setHandler(new HttpHandler() {
                    @Override
                    public void handleRequest(final HttpServerExchange exchange) throws Exception {
                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                        exchange.getResponseSender().send("Hello World");
                    }
                }).build();
        server.start();
    }
	
	
	public static void main1(String[] args) throws Exception {
		DeploymentInfo deploymentInfo = new DeploymentInfo().setClassLoader(Deployment.class.getClassLoader())
				.setContextPath("/xx")
				.setDefaultEncoding("UTF-8")
				.setDefaultSessionTimeout(30)
				.setHostName("127.0.0.1")
				.setUrlEncoding("UTF-8")
				.setDeploymentName("test")
				.addServlet(new ServletInfo("/*", HelloWord.class));
		DeploymentManager manager = new DeploymentManagerImpl(deploymentInfo, new ServletContainerImpl());
		manager.deploy();
		Undertow server = Undertow.builder()
		        .addListener(8080, "localhost")
		        .setHandler(manager.start())
		        .build();
		server.start();
	}

	public static void main0(String[] args) throws Exception {
		DeploymentInfo servletBuilder = new DeploymentInfo()
		        .setClassLoader(Deployment.class.getClassLoader())
		        .setContextPath("/myapp")
		        .setDeploymentName("test.war")
		        .addServlets(/*
		        		
		        		servlet("MessageServlet", MessageServlet.class)
		                        .addInitParam("message", "Hello World")
		                        .addMapping("/*"),
		                servlet("MyServlet", MessageServlet.class)
		                        .addInitParam("message", "MyServlet")
		                        .addMapping("/myservlet")*/);
		DeploymentManager manager = /*defaultContainer().addDeployment(servletBuilder)*/ null;
		manager.deploy();
		Undertow server = Undertow.builder()
		        .addListener(8080, "localhost")
		        .setHandler(manager.start())
		        .build();
		server.start();
	}
	
	public static class HelloWord extends HttpServlet {
		private static final long serialVersionUID = 1L;
		@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			resp.getWriter().write("hello : " + req.getParameter("name"));
		}
	}
}
