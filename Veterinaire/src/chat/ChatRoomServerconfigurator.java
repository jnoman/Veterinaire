package chat;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

import beans.User;

public class ChatRoomServerconfigurator extends ServerEndpointConfig.Configurator {
	public void modifyHandshake(ServerEndpointConfig ser, HandshakeRequest request, HandshakeResponse response) {
		User user = (User)((HttpSession) request.getHttpSession()).getAttribute("logged");
		ser.getUserProperties().put("userId", Long.toString(user.getId()));
		ser.getUserProperties().put("role", user.getRole());
	}
}
