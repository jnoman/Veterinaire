package chat;

import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/ChatRoomEndPoint/{chatroom}", configurator = ChatRoomServerconfigurator.class)
public class ChatRoomEndPoint {
	static Map<String, Set<Session>> chatRooms=(Map<String, Set<Session>>)Collections.synchronizedMap(new HashMap<String, Set<Session>>());
	public Set<Session> getChatRoom(String chatRoomId){
		Set<Session> chatRoom = chatRooms.get(chatRoomId);
		if(chatRoom == null) {
			chatRoom = Collections.synchronizedSet(new HashSet<Session>());
			chatRooms.put(chatRoomId, chatRoom);
		}
		return chatRoom;
	}
	@OnOpen
	public void handleOpen(EndpointConfig config, Session userSession, @PathParam ("chatroom") String chatroom) {
		String userId = (String) config.getUserProperties().get("userId");
		String role = (String) config.getUserProperties().get("role");
		userSession.getUserProperties().put("userId", userId);
		userSession.getUserProperties().put("role", role);
		userSession.getUserProperties().put("chatroom", chatroom);
		Set<Session> chatRoomUsers = getChatRoom(chatroom);
		chatRoomUsers.add(userSession);
	}
	@OnMessage
	public void handleMessage(String message, Session userSession) {
		String userId = (String) userSession.getUserProperties().get("userId");
		String role = (String) userSession.getUserProperties().get("role");
		String chatroom = (String) userSession.getUserProperties().get("chatroom");
		Set<Session> chatRoomUsers = getChatRoom(chatroom);
		chatRoomUsers.stream().forEach(x -> {
			try {
				x.getBasicRemote().sendText(buildJsonData(userId, message, role));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	@OnClose
	public void handleClose(Session userSession) {
		String chatroom = (String) userSession.getUserProperties().get("chatroom");
		Set<Session> chatRoomUsers = getChatRoom(chatroom);
		chatRoomUsers.remove(userSession);
	}
	@OnError
	public void onError(Throwable error) {
		System.out.println("Error: " + error.getMessage());
	}
	private String buildJsonData(String userId, String message, String role) {
		JsonObject jsonObject = Json.createObjectBuilder().add("message", message).add("role", role).build();
		StringWriter stringWriter = new StringWriter();
		try (JsonWriter jsonWriter = Json.createWriter(stringWriter)){
			jsonWriter.write(jsonObject);
		}
		return stringWriter.toString();
	}
}
