package coviddashboard.config;

public class ApplicationConfig {
	public static final String DATABASENAME = "covid_dashboard";
	public static final String DATABASEURL = "jdbc:mysql://localhost:3306/" + DATABASENAME;
	public static final String DATABASEUSERNAME = "root";
	public static final String DATABASEPAASWORD = "Test@1234";
	public static final String PATH = "/spring_workspace/coviddashboard/";
	public static final String IMAGE_PATH = PATH + "src/main/webapp/images/";
	public static final String EXCEL_PATH = PATH + "src/main/webapp/excel/";
}
