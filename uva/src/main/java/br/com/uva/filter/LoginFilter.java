package br.com.uva.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import br.com.uva.configuration.DatabaseConfig;
import br.com.uva.model.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/main/*" })
public class LoginFilter extends HttpServlet implements Filter {

	private static final long serialVersionUID = 1L;
	Connection connection;

	public void destroy() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {

			HttpServletRequest req = (HttpServletRequest) request;

			HttpSession session = req.getSession();
			String urlRequested = req.getServletPath();

			User user = (User) session.getAttribute("user");
			
			if (user == null) {
				request.setAttribute("message", "Please sign in");
				request.setAttribute("url", urlRequested);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				return;
			}

			chain.doFilter(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				request.setAttribute("message", e.getMessage());
				getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		DatabaseConfig.config();

	}

}
