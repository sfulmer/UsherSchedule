package net.draconia.church.usherschedule.windows.main.menu.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.SwingUtilities;

import net.draconia.church.usherschedule.windows.schedule.panels.List.actions.ScheduleNew;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class Create extends AbstractAction
{
	private static final long serialVersionUID = 5428163108501659011L;
	
	@Autowired
	private ScheduleNew mActNew;
	
	@Autowired
	private Edit mActSelect;
	
	public Create()
	{
		super("Create...");
		
		putValue(MNEMONIC_KEY, KeyEvent.VK_C);
	}
	
	public void actionPerformed(final ActionEvent objActionEvent)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				getNewAction().actionPerformed(objActionEvent);
			}
		});
		
		getSelectAction().actionPerformed(objActionEvent);
	}
	
	protected Action getSelectAction()
	{
		return(mActSelect);
	}
	
	protected ScheduleNew getNewAction()
	{
		return(mActNew);
	}
	
	protected void setSelectAction(final Edit actSelect)
	{
		mActSelect = actSelect;
	}
	
	@Autowired
	protected void setNewAction(final ScheduleNew actNew)
	{
		mActNew = actNew;
	}
}