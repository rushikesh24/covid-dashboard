package coviddashboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coviddashboard.dao.PassDataDao;

@WebServlet("/admin/displayuserlist")
public class DisplayUserList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DisplayUserList() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("username") == null || request.getSession().getAttribute("username").equals("")) {
			response.sendRedirect("/coviddashboard/home.html");
		}else {
			response.sendRedirect("displayuserlist.html");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
