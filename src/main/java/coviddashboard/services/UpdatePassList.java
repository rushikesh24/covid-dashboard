package coviddashboard.services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import coviddashboard.dao.PassDataDao;

@WebServlet("/admin/updatepassstatus/*")
public class UpdatePassList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdatePassList() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pathString = request.getPathInfo().substring(1);
		int index = pathString.indexOf("/");
		String status = pathString.substring(0, index);
		int passID = Integer.valueOf(pathString.substring(index + 1));
		if(new PassDataDao().updatePasssStatus(status, passID)) {
			status = "success";
		}else {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
