package servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanException;
import beans.User;
import dao.DaoException;
import dao.UserDaoImpl;
import methodes.PasswordHash;

@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
     

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/inscription.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nomComplet");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		try {
			UserDaoImpl userDaoImpl = new UserDaoImpl();
			userDaoImpl.inscription(new User(nom, email, PasswordHash.createHash(password), "client"));
			request.getSession().setAttribute("succes", "la création du compte est terminée avec succés");
			response.sendRedirect(request.getContextPath() + "/connexion");
		} catch (DaoException|BeanException|NoSuchAlgorithmException|InvalidKeySpecException e) {
			request.setAttribute("erreur", e.getMessage());
			doGet(request, response);
		} 
	}

}
