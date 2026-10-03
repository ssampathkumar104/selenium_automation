package scripts;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoggerUnitTest {

	private static final String PATTERN = "[%-5p] %d{yyyy-MM-dd HH:mm:ss.SSS} - %m%n";

	public static Logger initLogFormatter(String testClassName) {
	    Logger logger = LogManager.getLogger(testClassName);

	    // Get the LoggerContext
	    LoggerContext context = (LoggerContext) LogManager.getContext(false);
	    Configuration config = context.getConfiguration();

	    // Ensure root logger is OFF to avoid external logs
	    config.getRootLogger().setLevel(Level.OFF);

//	    // Create and set a specific LoggerConfig for "io.github.bonigarcia" and turn it OFF
//	    for(String s : Arrays.asList("io.github.bonigarcia", "org.openqa.selenium")) {
//	    	LoggerConfig bonigarciaLoggerConfig = new LoggerConfig(s, Level.OFF, false);
//	  	    config.addLogger(s, bonigarciaLoggerConfig);
//	    }
//	  
//
	    // Create and set LoggerConfig for your own package to DEBUG or INFO
	    LoggerConfig appLoggerConfig = config.getLoggerConfig(testClassName); // Assuming 'testClassName' is your application package
//	    if (appLoggerConfig == null) {
	        appLoggerConfig = new LoggerConfig(testClassName, Level.DEBUG, false);
	        config.addLogger(testClassName, appLoggerConfig);
//	    } else {
//	        appLoggerConfig.setLevel(Level.DEBUG);
//	    }
	    
	    // Remove any existing console appenders to avoid duplicate logs
	    config.getRootLogger().getAppenders().forEach((name, appender) -> {
	        if (appender instanceof ConsoleAppender) {
	            config.getLoggerConfig(logger.getName()).removeAppender(name);
	        }
	    });

	    // Create pattern layout
	    PatternLayout layout = PatternLayout.newBuilder().withPattern(PATTERN).build();

	    // Console appender
	    ConsoleAppender consoleAppender = ConsoleAppender.newBuilder()
	            .setName("ConsoleAppender")
	            .setLayout(layout)
	            .setTarget(ConsoleAppender.Target.SYSTEM_OUT)
	            .build();
	    consoleAppender.start();

	    // Create a LoggerConfig for the custom logger and set level
	    LoggerConfig loggerConfig = config.getLoggerConfig(logger.getName());
	    loggerConfig.setLevel(Level.DEBUG);  // Set the level to DEBUG to ensure all logs are captured

	    // Attach the appender to the custom logger
	    loggerConfig.addAppender(consoleAppender, Level.DEBUG, null);

	    // Update the LoggerContext to apply the configuration
	    context.updateLoggers();

	    return logger;
	}

	
	@Test
	public void TCLN_ContextPassing2() throws Exception {

		// Initialize logging
	    Logger logger1 =  initLogFormatter("TestLog");

	    // Continue with your test code
	    
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("--remote-allow-origins=*");

	    WebDriver d = WebDriverManager.chromedriver().capabilities(options).create();
	    d.get("https://google.com");
	    d.findElement(By.name("q")).click();
	    
	    // Log at various levels
	    logger1.info("========= INFO");
	    logger1.debug("========= DEBUG");
	    logger1.error("========= ERROR");

//	    d.quit();
	}

}
