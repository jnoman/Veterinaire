package servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BeanException;
import beans.User;
import services.ModelUser;
import services.UserService;

@WebServlet("/Connection")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/connexion.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		ModelUser modelUser = new ModelUser(email, password);
		try {
			UserService userService = new UserService();
			User user = userService.serviceConnexion(modelUser);
			if(user != null) {
                HttpSession session = request.getSession();
    			session.setAttribute("logged", user);
				response.sendRedirect(request.getContextPath() + "/");
			} else {
				request.setAttribute("erreur", "Email ou mot de passe est incorrect");
				doGet(request, response);
			}
		} catch (BeanException | NoSuchAlgorithmException | InvalidKeySpecException e) {
			request.setAttribute("erreur", e.getMessage());
			doGet(request, response);
		}
	}

}
