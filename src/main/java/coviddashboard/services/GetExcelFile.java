package coviddashboard.services;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coviddashboard.config.ApplicationConfig;
import coviddashboard.dao.DistrictDataDao;

@WebServlet("/downloadexcelfile")
public class GetExcelFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetExcelFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			sendReport(response);
		} catch (FileNotFoundException e) {
			new DistrictDataDao().generateReport();
			sendReport(response);
		} catch (IOException e) {
			e.getStackTrace();
		}

	}

	private void sendReport(HttpServletResponse response) throws FileNotFoundException, IOException {
		String filePath = ApplicationConfig.EXCEL_PATH + LocalDate.now().toString() + ".xlsx";
		File file = new File(filePath);
		response.setHeader("Content-Type", getServletContext().getMimeType(file.getName()));
		response.setHeader("Content-Length", Long.toString(file.length()));
		response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");

		BufferedInputStream input = null;
		BufferedOutputStream output = null;

		try {
			input = new BufferedInputStream(new FileInputStream(file));
			output = new BufferedOutputStream(response.getOutputStream());

			byte[] buffer = new byte[8192];
			for (int length = 0; (length = input.read(buffer)) > 0;) {
				output.write(buffer, 0, length);
			}
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException ignore) {
				}
			}
			if (input != null) {
				try {
					input.close();
				} catch (IOException ignore) {
				}
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
