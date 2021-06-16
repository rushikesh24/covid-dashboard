package coviddashboard.services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coviddashboard.dao.DistrictDataDao;

@WebServlet("/district/all/*")
public class DistrictDataAllDays extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pathString = request.getPathInfo().substring(1);
		int index = pathString.indexOf("/");
		String stateName = pathString.substring(0, index);
		String districtName = pathString.substring(index + 1);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(new DistrictDataDao().getAllDistrictData(stateName, districtName));
		out.flush();
	}
}