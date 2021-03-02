package servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BeanException;
import beans.Message;
import beans.User;
import dao.MessageDaoImpl;
import services.MessageService;
import services.UserService;

/**
 * Servlet implementation class ChatClient
 */
@WebServlet("/ChatClient")
public class ChatClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("logged") != null) {
			User user = (User) session.getAttribute("logged");
			if(user.getRole().equals("client")) {
				MessageService messageService = new MessageService();
				try {
					List<Message> ListMessage = messageService.getUserMessages(user);
					request.setAttribute("ListMessage", ListMessage);
				} catch (NoSuchAlgorithmException|InvalidKeySpecException|BeanException e) {
					e.printStackTrace();
				} 
				this.getServletContext().getRequestDispatcher("/WEB-INF/chatClient.jsp").forward(request, response);
			} else {
				UserService userService = new UserService();
				try {
					List<User> ListUsers = userService.getUsersClient();
					request.setAttribute("ListUsers", ListUsers);
				} catch (NoSuchAlgorithmException|InvalidKeySpecException|BeanException e) {
					e.printStackTrace();
				}
				this.getServletContext().getRequestDispatcher("/WEB-INF/chatAdmin.jsp").forward(request, response);
			}
			 
		} else {
			response.sendRedirect(request.getContextPath() + "/");
		}
	}
	
	@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String textMessage = request.getParameter("textMessage");
		HttpSession session = request.getSession();
		if(session.getAttribute("logged") != null) {
			User user = (User) session.getAttribute("logged");
			MessageDaoImpl messageDaoImpl = new MessageDaoImpl();
			Message message = new Message(user, textMessage, true, new Date());;
			if(user.getRole().equals("admin")) {
				UserService userService = new UserService();
				message.setSender(false);
				User user1;
				try {
					user1 = userService.getUserById(Long.parseLong(request.getParameter("id")));
					message.setUser(user1);
				} catch (NumberFormatException|NoSuchAlgorithmException|InvalidKeySpecException|BeanException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			messageDaoImpl.addMessage(message);
			response.setContentType("text/xml");
		    response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("<message>"+message.isSender()+"</message>");
		}
	}
}
