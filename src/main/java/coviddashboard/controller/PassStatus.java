package coviddashboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import coviddashboard.dao.PassDataDao;

@WebServlet("/pass_status")
public class PassStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PassStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("passform.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int passId = Integer.parseInt(request.getParameter("passID"));
		String aadharNumber = request.getParameter("aadharNumber");
		JSONObject json = new PassDataDao().getPassStatus(passId, aadharNumber);
		String status = json.getString("status");
		if (status.equals("Successful")) {
			request.getSession().setAttribute("imagePath", "images/" + passId + ".png");
			response.sendRedirect("displayQRcode.jsp");
		} else {
			String message = "Your applicaiton ID is " + json.getInt("passID")
					+ " Your application is pending for approval.";
			request.getSession().setAttribute("message", message);
			response.sendRedirect("passform.jsp");
		}
	}

}
