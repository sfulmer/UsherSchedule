package net.draconia.church.usherschedule.windows.main.menu.actions;

import java.awt.Window;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

public class Exit extends AbstractAction
{
	private static final long serialVersionUID = 2360291591044664965L;
	
	private Window mWndParent;
	
	public Exit(final Window wndParent)
	{
		super("Exit");
		
		putValue(MNEMONIC_KEY, KeyEvent.VK_X);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK));
		
		setParent(wndParent);
	}
	
	public void actionPerformed(final ActionEvent objActionEvent)
	{
		getParent().dispose();
	}
	
	protected Window getParent()
	{
		return(mWndParent);
	}
	
	protected void setParent(final Window wndParent)
	{
		mWndParent = wndParent;
	}
}