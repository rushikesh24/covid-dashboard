package coviddashboard.services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coviddashboard.dao.StateDataDao;

@WebServlet("/state/all/*")
public class StateDataAllDays extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StateDataAllDays() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String stateName = request.getPathInfo().substring(1).replace('-', ' ');
		System.out.println(stateName);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		System.out.println(stateName);
		out.print(new StateDataDao().getStateAllData(stateName));
		out.flush();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
