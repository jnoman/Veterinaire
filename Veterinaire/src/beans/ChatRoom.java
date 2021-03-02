package beans;

import java.util.Hashtable;

import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

@ServerEndpoint(value = "/chatroom/{pseudo}", configurator = ChatRoom.EndpointConfigurator.class)
public class ChatRoom {
	private static ChatRoom singleton = new ChatRoom();

	private ChatRoom() {
	}

	public static ChatRoom getInstance() {
		System.out.println(singleton);
		return ChatRoom.singleton;
	}

	private Hashtable<String, Session> sessions = new Hashtable<>();


	@OnOpen
	public void open(Session session, @PathParam("pseudo") String pseudo) {
		session.getUserProperties().put("pseudo", pseudo);
		sessions.put(session.getId(), session);
	}


	@OnError
	public void onError(Throwable error) {
		System.out.println("Error: " + error.getMessage());
	}

	@OnMessage
	public void handleMessage(String message, Session session) {
		String fullMessage = message;

		sendMessage(fullMessage);
	}
	
	private void sendMessage(String fullMessage) {
		for (Session session : sessions.values()) {
			try {
				session.getBasicRemote().sendText(fullMessage);
			} catch (Exception exception) {
				System.out.println("ERROR: cannot send message to " + session.getId());
			}
		}
	}
	
	public static class EndpointConfigurator extends ServerEndpointConfig.Configurator {
		@Override
		@SuppressWarnings("unchecked")
		public <T> T getEndpointInstance(Class<T> endpointClass) {
			return (T) ChatRoom.getInstance();
		}
	}
}
