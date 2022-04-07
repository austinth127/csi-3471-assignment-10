package edu.baylor.ecs.si;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Timer {

	// Get a logger for this class
	private static Logger logger = Logger.getLogger(Timer.class.getName());
	
	static {
		try {
			InputStream configFile = Timer.class.getClassLoader().getResourceAsStream("logger.properties");
			LogManager.getLogManager().readConfiguration(configFile);
			configFile.close();
		} catch (IOException ex) {
			System.out.println("WARNING: Could not open configuration file");
		    System.out.println("WARNING: Logging not configured (console output only)");
		}
		logger.info("starting the app");
	}
	
	/*
	 * Just sleep
	 */
	private static void method(long time) throws InterruptedException {
		Thread.sleep(time);
	}
	
	public static long timeMe(long timeToWait) throws TimerException {
		Long timeNow;
		try {
		  if (timeToWait < 0) {
			  logger.severe("Time to wait cannot be less than zero.");
			  throw new TimerException("Cannot be less than zero");
		  }
		  timeNow = System.currentTimeMillis();
		  method(timeToWait);
		  logger.info("Calling took: "+ (System.currentTimeMillis() - timeNow));
		  logger.info("* should take: "+ timeToWait);

		} catch (InterruptedException e) {
			logger.severe("InterruptedException rised");
			throw new TimerException("Sleep exception", e);
		}

		return timeNow;
	}
}
