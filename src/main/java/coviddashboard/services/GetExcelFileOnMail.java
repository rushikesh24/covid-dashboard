package coviddashboard.services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import coviddashboard.config.SendMail;

@WebServlet("/mailfile/*")
public class GetExcelFileOnMail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetExcelFileOnMail() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getPathInfo().substring(1);
		String status;
		if (new SendMail().sendEmail(email)) {
			status = "success";
		} else {
			status = "failed";
		}

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", status);
		out.print(jsonObject);
		out.flush();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
