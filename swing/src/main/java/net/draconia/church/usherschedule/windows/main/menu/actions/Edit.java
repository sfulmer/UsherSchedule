package net.draconia.church.usherschedule.windows.main.menu.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.draconia.church.usherschedule.windows.schedule.ui.ScheduleDialog;

@Component("selectMenu")
public class Edit extends AbstractAction
{
	private static final long serialVersionUID = 8916921812553571584L;
	
	@Autowired
	private ScheduleDialog mObjDialog;
	
	public Edit()
	{
		super("Edit...");
		
		putValue(MNEMONIC_KEY, KeyEvent.VK_E);
	}
	
	public void actionPerformed(final ActionEvent objActionEvent)
	{
		getDialog().setVisible(true);
	}
	
	protected JDialog getDialog()
	{
		return(mObjDialog);
	}
	
	protected void setDialog(final ScheduleDialog objDialog)
	{
		mObjDialog = objDialog;
	}
}