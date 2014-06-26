package org.jboss.reddeer.common.logging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jboss.reddeer.common.context.ExecutionSetting;

/**
 * Simple console logger for Reddeer
 * @author Jiri Peterka
 *
 */
public class Logger {

	private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
		
	private static final String error = "ERROR";
	private static final String warning = "WARNING";
	private static final String debug = "DEBUG";
	private static final String trace = "TRACE";
	private static final String info = "INFO";
	private static final String dump = "DUMP";
	private static final String fatal = "FATAL";
	
	private Class<? extends Object> loggerClass;
	
	/**
	 * Returns logger based on given class
	 * @param c given class
	 * @return logger instance
	 */
	public static Logger getLogger(Class<? extends Object> c) { 
		return new Logger(c);
	}
	
	/**
	 * Create Logger based on given class
	 * @param c given class
	 */
	public Logger(Class<? extends Object> c) {
		this.loggerClass = c;
	}

	/**
	 * log debug message
	 * @param msg message
	 */
	public void debug(String msg) {
		if (ExecutionSetting.getInstance().isDebugEnabled()) {
			print(debug,msg);
		}		
	}

	/**
	 * Log debug message using formatting string and arguments
	 *
	 * @param fmtString Formatting string
	 * @param args Arguments
	 * @see java.lang.String#format(String, Object...)
	 */
	public void debug(String fmtString, Object... args) {
		debug(String.format(fmtString, args));
	}

	/**
	 * log trace message
	 * @param msg message
	 */
	public void trace(String msg) {
		if (ExecutionSetting.getInstance().isDebugEnabled()) {
			print(trace,msg);
		}		
	}

	/**
	 * Log trace message using formatting string and arguments
	 *
	 * @param fmtString Formatting string
	 * @param args Arguments
	 * @see java.lang.String#format(String, Object...)
	 */
	public void trace(String fmtString, Object... args) {
		trace(String.format(fmtString, args));
	}

	/**
	 * log warning message
	 * @param msg message
	 */
	public void warn(String msg) {
		print(warning,msg);
	}

	/**
	 * Log warning message using formatting string and arguments
	 *
	 * @param fmtString Formatting string
	 * @param args Arguments
	 * @see java.lang.String#format(String, Object...)
	 */
	public void warn(String fmtString, Object... args) {
		warn(String.format(fmtString, args));
	}

	/**
	 * log error message
	 * @param msg message
	 */
	public void error(String msg) {
		print(error,msg);
	}

	/**
	 * Log error message using formatting string and arguments
	 *
	 * @param fmtString Formatting string
	 * @param args Arguments
	 * @see java.lang.String#format(String, Object...)
	 */
	public void error(String fmtString, Object... args) {
		error(String.format(fmtString, args));
	}

	/**
	 * log error message
	 * @param msg message
	 * @param t throwable
	 */
	public void error(String msg, Throwable t) {
		print(error,msg);
		printStackTraceRecursive(t);
	}

	/**
	 * Log error message using formatting string and arguments, and log corresponding exception
	 *
	 * @param fmtString Formatting string
	 * @param args Arguments
	 * @param t
	 * @see java.lang.String#format(String, Object...)
	 */
	public void error(String fmtString, Throwable t, Object... args) {
		error(String.format(fmtString, args), t);
	}

	/**
	 * log info message
	 * @param msg message
	 */
	public void info(String msg) {
		print(info,msg);
	}

	/**
	 * Log info message using formatting string and arguments
	 *
	 * @param fmtString Formatting string
	 * @param args Arguments
	 * @see java.lang.String#format(String, Object...)
	 */
	public void info(String fmtString, Object... args) {
		info(String.format(fmtString, args));
	}

	/**
	 * log dump message
	 * @param msg message
	 */
	public void dump(String msg) {
		print(dump,msg);
	}

	/**
	 * Log dump message using formatting string and arguments
	 *
	 * @param fmtString Formatting string
	 * @param args Arguments
	 * @see java.lang.String#format(String, Object...)
	 */
	public void dump(String fmtString, Object... args) {
		dump(String.format(fmtString, args));
	}

	/**
	 * log fatal message
	 * @param msg message
	 */
	public void fatal(String msg) {
		print(fatal,msg);
	}

	/**
	 * Log fatal message using formatting string and arguments
	 *
	 * @param fmtString Formatting string
	 * @param args Arguments
	 * @see java.lang.String#format(String, Object...)
	 */
	public void fatal(String fmtString, Object... args) {
		fatal(String.format(fmtString, args));
	}
	
	private void print(String severity, String msg) {
		StringBuilder sb = new StringBuilder();
		if (ExecutionSetting.getInstance().isDebugEnabled()) {
			sb.append(dateFormat.format(new Date()) + " "); 
		}				
		
		sb.append(severity);
		sb.append(" [");
		sb.append(getThreadName());
		sb.append("]");
		sb.append("[");		
		sb.append(loggerClass.getSimpleName());
		sb.append("] ");
		sb.append(msg);
		System.out.println(sb.toString());
	}
	
	private String getThreadName() {
		return Thread.currentThread().getName();
	}
	
	private void printStackTraceRecursive(Throwable t) {
		if ((t != null) && ( t.getStackTrace() != null)) {
			t.printStackTrace();
			printStackTraceRecursive(t.getCause());
		}
	}

	public boolean isDebugEnabled() {
		return ExecutionSetting.getInstance().isDebugEnabled();
	}
}
