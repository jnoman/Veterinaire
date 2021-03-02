package servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BeanException;
import beans.RendezVous;
import beans.User;
import dao.RendezVousDaoImpl;
import services.RendezVousService;

/**
 * Servlet implementation class RendezVousServlet
 */
@WebServlet("/RendezVousServlet")
public class RendezVousServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("logged") != null) {
			User user = (User) session.getAttribute("logged");
			RendezVousService rendezVousService = new RendezVousService();
			List<RendezVous> listRendezVous;
			if(user.getRole().equals("client")) {
				try {
					listRendezVous = rendezVousService.getUserRendezVous(user);
					request.setAttribute("listRendezVous", listRendezVous);
				} catch (NoSuchAlgorithmException|InvalidKeySpecException|BeanException e) {
					e.printStackTrace();
				} 
				this.getServletContext().getRequestDispatcher("/WEB-INF/rendezVousClient.jsp").forward(request, response);
			} else {
				try {
					listRendezVous = rendezVousService.getAdminRendezVous();
					request.setAttribute("listRendezVous", listRendezVous);
				} catch (NoSuchAlgorithmException|InvalidKeySpecException|BeanException e) {
					e.printStackTrace();
				}
				this.getServletContext().getRequestDispatcher("/WEB-INF/rendezVousAdmin.jsp").forward(request, response);
			}
			 
		} else {
			response.sendRedirect(request.getContextPath() + "/");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RendezVousDaoImpl rendezVousDaoImpl = new RendezVousDaoImpl();
		if (request.getParameter("ajouter") != null) {
			String titre = request.getParameter("titre");
			String availableDate = request.getParameter("datedebut");
			try {
				Date dateDebut = new SimpleDateFormat("yyyy-MM-dd").parse(availableDate);
				int duree = Integer.parseInt(request.getParameter("duree"));
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute("logged");
				RendezVous rendezVous = new RendezVous(user, titre, dateDebut, duree, 0);
				rendezVousDaoImpl.addRendezVous(rendezVous);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Long id = Long.parseLong(request.getParameter("id"));
			RendezVousService rendezVousService = new RendezVousService();
			try {
				RendezVous rendezVous = rendezVousService.getRendezVousById(id);
				if (request.getParameter("supprimer") != null) {
					rendezVousDaoImpl.deleteRendezVous(id);
				} else if (request.getParameter("accepter") != null) {
					rendezVous.setEtat(1);
					rendezVousDaoImpl.updateRendezVous(rendezVous);
				} else if (request.getParameter("refusée") != null) {
					rendezVous.setEtat(2);
					rendezVousDaoImpl.updateRendezVous(rendezVous);
				}
			} catch (NoSuchAlgorithmException | InvalidKeySpecException | BeanException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		doGet(request, response);
	}

}
