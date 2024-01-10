package br.com.uva.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.com.uva.dao.UserDAO;
import br.com.uva.model.Role;
import br.com.uva.model.User;
import br.com.uva.services.UserValidateData;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@MultipartConfig
@WebServlet("/api/users")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String search = req.getParameter("search");
		if (search == null || search.isEmpty()) {
			search = "";
		}
		

		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		
		Role role = user.getRole();
		
		List<User> users = null;
		
		if(role.equals(Role.ADMINISTRATOR)){
			users = new UserDAO().getUsersSupports(search);						
		}
		
		if(role.equals(Role.SUPPORT)){
			users = new UserDAO().getUsers(search);						
		}
		
		senderResponse(users, 200, resp);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("in post users...");
		
		Part filePart = request.getPart("photo");
		InputStream inputStream = filePart.getInputStream();
		System.out.println(inputStream);
		System.out.println(request.getParameter("username"));
//		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
//		User user = new UserValidateData(data.get("username").getAsString(), data.get("firstName").getAsString(),
//				data.get("lastName").getAsString(), data.get("document").getAsString(),
//				data.get("password").getAsString()).getUser();
//		
//		if(new UserDAO().create(user, data.get("userType").getAsLong())) {
//			HashMap<String, String> dataResponse = new HashMap<String, String>();
//			dataResponse.put("detail", "User added" );
//			senderResponse(dataResponse, 201, response);			
//		} else {
//			HashMap<String, String> dataResponse = new HashMap<String, String>();
//			dataResponse.put("detail", "bad request: username or document wrong" );
//			senderResponse(dataResponse, 400, response);
//		}
	}
	
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long id = Long.parseLong(req.getParameter("id"));
		new UserDAO().delete(id);
		
	}
	
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
		User user = new User();
		
		user.setId(data.get("id").getAsLong());
		user.setUsername(data.get("username").getAsString());
		user.setFirstName(data.get("firstName").getAsString());
		user.setLastName(data.get("lastName").getAsString());
		user.setDocument(data.get("document").getAsString());
		
		new UserDAO().update(user);
		
	}

	void senderResponse(Object data,  int status, HttpServletResponse response) throws IOException {

		response.setContentType("application/json");
		response.setStatus(status);
		String content = new Gson().toJson(data);
		PrintWriter out = response.getWriter();
		out.print(content);
		out.flush();
	}

}
