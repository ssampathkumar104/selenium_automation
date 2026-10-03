package workflow;

public interface Log {

	void debug(String paramString);

	void debug(String paramString, Throwable paramThrowable);

	void info(String paramString);

	void info(String paramString, Throwable paramThrowable);

	void info(String paramString, Object... paramVarArgs);

	void warn(String paramString);

	void warn(String paramString, Throwable paramThrowable);

	void error(String paramString);

	void error(String paramString, Throwable paramThrowable);

	void initLogger(Class<?> paramClass);

}
