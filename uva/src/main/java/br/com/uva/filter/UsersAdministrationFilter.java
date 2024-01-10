package br.com.uva.filter;

import java.io.IOException;

import br.com.uva.model.Role;
import br.com.uva.model.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@WebFilter(urlPatterns = { "/api/users", "/main/users.jsp" })
public class UsersAdministrationFilter extends HttpFilter implements Filter {
       
	private static final long serialVersionUID = 1L;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		Role role = user.getRole();
		
		if(role == null || role.equals(Role.USER)) {
			request.getRequestDispatcher("/main/home.jsp").forward(request, response);
			return;
		} 
			
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
