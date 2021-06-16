package coviddashboard.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import coviddashboard.dao.DistrictDataDao;

@WebListener
public class DataUpdateScript implements ServletContextListener {

	   @Override
    public final void contextInitialized(final ServletContextEvent sce) {
		   System.out.println("Scheduler started");
		   updateData();
    }

    @Override
    public final void contextDestroyed(final ServletContextEvent sce) {
 	   System.out.println("Schedular End");
    }
    
	public static void updateData() {
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

		scheduler.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				try {
					System.out.println("Executing python script.....");
					String path = new File(ApplicationConfig.PATH + "script.py").getAbsolutePath();
					System.out.println("File path for script.py : " + path);
					Process p = Runtime.getRuntime().exec("python " +  path);

					String s = null;
					BufferedReader stdout = new BufferedReader(new InputStreamReader(p.getInputStream()));
					System.out.println("Here is the output of script  (if any):\n");
					while ((s = stdout.readLine()) != null) {
						System.out.println(s);
					}

					BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
					System.out.println("Here is the standard error of the command (if any):\n");

					while ((s = stdError.readLine()) != null) {
						System.out.println(s);
					}
					System.out.println("Python script executed");
					new DistrictDataDao().generateReport();
					new SendMail().sendScheduledMails();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}, 1, 1, TimeUnit.DAYS);
	}
}