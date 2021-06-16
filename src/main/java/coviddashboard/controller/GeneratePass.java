package coviddashboard.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.zxing.WriterException;

import coviddashboard.config.ApplicationConfig;
import coviddashboard.config.GenerateQRCode;
import coviddashboard.dao.PassDataDao;
import coviddashboard.model.PassData;

@WebServlet("/generatepass")
public class GeneratePass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GeneratePass() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("passform.html");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String aadharnumber = request.getParameter("aadharnumber");
		String sourceState = request.getParameter("sourceStateList");
		String sourceDistrict = request.getParameter("sourceDistrictList");
		String destinationState = request.getParameter("destinationStateList");
		String destinationDistrict = request.getParameter("destinationDistrictList");
		String reason = request.getParameter("reason");
		Date date = Date.valueOf(request.getParameter("date"));
		String comments = request.getParameter("comment");
		PassDataDao passDataDao = new PassDataDao();
		PassData passData = new PassData(name, aadharnumber, sourceState, sourceDistrict, destinationState,
				destinationDistrict, reason, date, comments);
		JSONObject json = passDataDao.getPass(passData);
		String status = json.getString("status");
		int passId = json.getInt("passID");
		String message;
		if (status.equals("Successful")) {
			String filePath = ApplicationConfig.IMAGE_PATH + passId + ".png";
			passData.setPassStatus(status);
			try {
				passData.setPassID(passId);
				new GenerateQRCode().createQRImage(filePath, passData.toString(), GenerateQRCode.SIZE, "png");
			} catch (WriterException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			message = "Your applicaiton ID is " + json.getInt("passID") + ". Your application is approved.";
		} else {
			message = "Your applicaiton ID is " + json.getInt("passID") + ". Your application is pending for approval.";
		}
		request.getSession().setAttribute("message", message);
		response.sendRedirect("passform.jsp");
	}

}
