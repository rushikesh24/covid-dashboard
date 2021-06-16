package coviddashboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coviddashboard.dao.UsersDao;
import coviddashboard.model.Users;

@WebServlet("/signup")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("login.html");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		UsersDao userDao = new UsersDao();
		if (userDao.isUsernmaeExist(username)) {
			request.setAttribute("error", "Username already exist");
		} else {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String mobileNumber = request.getParameter("mobileNumber");
			String role = "admin";
			Users user = new Users(name, username, password, email, mobileNumber, role, "pending");
			if (userDao.save(user)) {
				request.setAttribute("message", "User registered Successfully");
				response.sendRedirect("login.html");
			} else {
				doGet(request, response);
			}
		}

	}

}
