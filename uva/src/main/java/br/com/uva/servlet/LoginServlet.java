package br.com.uva.servlet;

import java.io.IOException;

import br.com.uva.dao.LoginDAO;
import br.com.uva.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
				request.setAttribute("message", "Enter a valid credentials!");
				getServletContext().getRequestDispatcher("/").forward(request, response);
				return;
			}

			User user = LoginDAO.getUserIfAuthenticated(username, password);
			String url = request.getParameter("url");

			if (url == null || url.isEmpty() || url.equals("null")) {
				url = "/main/home.jsp";
			}

			if (user != null) {
				request.getSession().setAttribute("user", user);
				getServletContext().getRequestDispatcher(url).forward(request, response);
			} else {
				request.setAttribute("message", "User not exists!");
				getServletContext().getRequestDispatcher("/").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				request.setAttribute("message", e.getMessage());
				getServletContext().getRequestDispatcher("error.jsp").forward(request, response);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}

		}

	}

}
