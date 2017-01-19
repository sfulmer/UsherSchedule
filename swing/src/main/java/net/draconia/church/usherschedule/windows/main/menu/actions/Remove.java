package net.draconia.church.usherschedule.windows.main.menu.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;

import net.draconia.church.usherschedule.domain.Model;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component("MenuScheduleRemove")
public class Remove extends AbstractAction
{
	private static final long serialVersionUID = -5006095513872246136L;
	
	@Autowired
	private Model mObjModel;
	
	public Remove()
	{
		super("Remove");
		
		putValue(MNEMONIC_KEY, KeyEvent.VK_C);
		putValue(SHORT_DESCRIPTION, "Not yet finished!");
		
		setEnabled(false);
	}
	
	public void actionPerformed(final ActionEvent objActionEvent)
	{
		getModel().setSchedule(null);
	}
	
	protected Model getModel()
	{
		return(mObjModel);
	}
	
	protected void setModel(final Model objModel)
	{
		mObjModel = objModel;
	}
}