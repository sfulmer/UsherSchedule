package net.draconia.church;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.draconia.church.usherschedule.ui.UsherScheduleMainFrame;
import net.draconia.listeners.SpringContextShutdownHook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UsherScheduleMain implements Runnable
{
	private ApplicationContext mObjApplicationContext;
	
	private static final Logger msObjLogger = LoggerFactory.getLogger(UsherScheduleMain.class); 
	
	public UsherScheduleMain()
	{ }
	
	protected ApplicationContext getApplicationContext()
	{
		if(mObjApplicationContext == null)
			{
			mObjApplicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
			//mObjApplicationContext = new ClassPathXmlApplicationContext("Beans.xml");
			
			Runtime.getRuntime().addShutdownHook(new Thread(new SpringContextShutdownHook(mObjApplicationContext)));
			}
		
		return(mObjApplicationContext);
	}
	
	public void run()
	{
		try
			{
			JFrame frmMain = ((JFrame)(getApplicationContext().getBean(UsherScheduleMainFrame.class)));
			
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			
			frmMain.setVisible(true);
			}
		catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException objException)
			{
			String sMessage = "Problem displaying Application window!";
			
			msObjLogger.error(sMessage, objException);
			
			JOptionPane.showMessageDialog(null, sMessage + " See Log  for details.");
			}
	}
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new UsherScheduleMain());
	}
}