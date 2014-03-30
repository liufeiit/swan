package undertow;

import io.undertow.Undertow;
import io.undertow.Undertow.ListenerType;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年2月21日 下午3:31:37
 */
public class HelloWorldServer {
	public static void main(final String[] args) {
        Undertow server = Undertow.builder()
        		.addListener(8080, "localhost", ListenerType.HTTP)
//        		.addListener(8080, "localhost")
//                .addHttpListener(8080, "localhost")
                .setHandler(new HttpHandler() {
                    @Override
                    public void handleRequest(final HttpServerExchange exchange) throws Exception {
                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                        exchange.getResponseSender().send("Hello World");
                    }
                }).build();
        server.start();
    }
}
