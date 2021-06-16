package coviddashboard.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


public class InitScheduler implements ServletContextListener {

	   @Override
       public final void contextInitialized(final ServletContextEvent sce) {
		   DataUpdateScript script = new DataUpdateScript();
       }

       @Override
       public final void contextDestroyed(final ServletContextEvent sce) {
    	   System.out.println("Schedular End");
       }
	}
