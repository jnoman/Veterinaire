package servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import beans.BeanException;
import beans.Message;
import beans.User;
import services.MessageService;

@WebServlet("/ChatAdmin")
public class ChatAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("logged") != null) {
			MessageService messageService = new MessageService();
			List<Message> ListMessage = null;
			try {
				Long id = Long.parseLong(request.getParameter("id"));
				User user = new User();
				user.setId(id);
				ListMessage = messageService.getUserMessages(user);
			} catch (NoSuchAlgorithmException|InvalidKeySpecException|BeanException e) {
				e.printStackTrace();
			}
			response.setContentType("application/json");
			new Gson().toJson(ListMessage, response.getWriter());
		}
	}

}
