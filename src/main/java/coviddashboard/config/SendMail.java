package coviddashboard.config;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {

	public boolean sendEmail(String emailId) {
		final String username = "rushisd24@gmail.com"; // gmail
		final String password = "password"; // password of gmail

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); // TLS

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("zrushi24@gmail.com")); // from email address
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailId) // to emailId
			);
			message.setSubject("Covid 19 Report"); // subject

			Date date = Date.valueOf(LocalDate.now());
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText("Covid 19 report of " + date.toString());

			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			String filename = ApplicationConfig.EXCEL_PATH + date.toString() + ".xlsx";// change accordingly
			DataSource source = new FileDataSource(filename);
			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName("report.xlsx");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart1);
			multipart.addBodyPart(messageBodyPart2);
			message.setContent(multipart);

			Transport.send(message);

			return true;

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void sendScheduledMails() {
		String sql = "select email from SubscriptionData;";
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(ApplicationConfig.DATABASEURL, ApplicationConfig.DATABASEUSERNAME,
					ApplicationConfig.DATABASEPAASWORD);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				sendEmail(resultSet.getString("email"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}