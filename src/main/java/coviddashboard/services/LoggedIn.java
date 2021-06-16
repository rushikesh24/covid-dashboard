package coviddashboard.services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.sun.mail.imap.protocol.Status;

import coviddashboard.dao.DistrictDataDao;


@WebServlet("/isuserloggedin")
public class LoggedIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	public LoggedIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String status = "";
		if(request.getSession().getAttribute("username") == null || request.getSession().getAttribute("username").equals("")) {
			status = "";
		}else {
			status = "success";
		}
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		json.put("status", status);
		out.print(json);
		out.flush();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
