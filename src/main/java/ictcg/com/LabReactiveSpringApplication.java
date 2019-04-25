package ictcg.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LabReactiveSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabReactiveSpringApplication.class, args);
	}
	
	 @Bean
	    public EchoHandler echoHandler() {
	        return new EchoHandler();
	    }
	 
	    @Bean
	    public HandlerMapping handlerMapping() {
	        Map<String, WebSocketHandler> map = new HashMap<>();
	        map.put("/echo", echoHandler());
	 
	        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
	        mapping.setUrlMap(map);
	        mapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
	        return mapping;
	    }
	 
	    @Bean
	    public WebSocketHandlerAdapter handlerAdapter() {
	        return new WebSocketHandlerAdapter(webSocketService());
	    }
	 
	    @Bean
	    public WebSocketService webSocketService() {
	        return new HandshakeWebSocketService(new ReactorNettyRequestUpgradeStrategy());
	    }

}
