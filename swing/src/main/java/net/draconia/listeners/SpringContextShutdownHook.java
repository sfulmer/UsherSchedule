package net.draconia.listeners;

import java.io.Serializable;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringContextShutdownHook implements Runnable, Serializable
{
	private static final long serialVersionUID = -8625247695126784965L;
	
	private ApplicationContext mObjApplicationContext;
	
	public SpringContextShutdownHook(final ApplicationContext objApplicationContext)
	{
		setApplicationContext(objApplicationContext);
	}
	
	protected ApplicationContext getApplicationContext()
	{
		return(mObjApplicationContext);
	}
	
	public void run()
	{
		((AnnotationConfigApplicationContext)(getApplicationContext())).close();
	}
	
	protected void setApplicationContext(final ApplicationContext objApplicationContext)
	{
		mObjApplicationContext = objApplicationContext;
	}
}