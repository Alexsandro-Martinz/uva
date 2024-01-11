package br.com.uva.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.com.uva.dao.UserDAO;
import br.com.uva.model.Role;
import br.com.uva.model.User;
import br.com.uva.services.UserValidateData;
import jakarta.servlet.ServletException;
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

		if (role.equals(Role.ADMINISTRATOR)) {
			users = new UserDAO().getUsersSupports(search);
		}

		if (role.equals(Role.SUPPORT)) {
			users = new UserDAO().getUsers(search);
		}

		senderResponse(users, 200, resp);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			Part part = request.getPart("photo");
			byte[] photo = IOUtils.toByteArray(part.getInputStream());
			String photoBase64 = "data:image/" + part.getContentType().split("\\/")[1]+ ";base64," + Base64.encodeBase64String(photo);

			User user = new UserValidateData(request.getParameter("username"), request.getParameter("firstName"),
					request.getParameter("lastName"), request.getParameter("document"),
					request.getParameter("password")).getUser();
			
			user.setPhoto(photoBase64);
			user.setPhotoExtension(part.getContentType().split("\\/")[1]);
			
			if (new UserDAO().create(user, Long.parseLong(request.getParameter("userType")))) {
				HashMap<String, String> dataResponse = new HashMap<String, String>();
				dataResponse.put("detail", "User added");
				senderResponse(dataResponse, 201, response);
			} else {
				HashMap<String, String> dataResponse = new HashMap<String, String>();
				dataResponse.put("detail", "bad request: username or document wrong");
				senderResponse(dataResponse, 400, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			HashMap<String, String> dataResponse = new HashMap<String, String>();
			dataResponse.put("detail", "image not uploaded");
			senderResponse(dataResponse, 400, response);
		}
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

	void senderResponse(Object data, int status, HttpServletResponse response) throws IOException {

		response.setContentType("application/json");
		response.setStatus(status);
		String content = new Gson().toJson(data);
		PrintWriter out = response.getWriter();
		out.print(content);
		out.flush();
	}

}
