package net.draconia.church.usherschedule.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.annotation.PostConstruct;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.draconia.church.usherschedule.domain.Model;

import net.draconia.church.usherschedule.ui.model.UsherScheduleTableModel;

import net.draconia.church.usherschedule.windows.main.menu.actions.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class UsherScheduleMainFrame extends JFrame
{
	private static final long serialVersionUID = 243406244713224011L;
	
	private Action mActExit, mActScheduleRemove;
	
	@Autowired
	private Create mActScheduleCreateMenu;
	
	@Autowired
	private Edit mActScheduleSelect;
	
	@Autowired
	private Model mObjModel;
	
	private JTable mTblSchedule;
	
	@Autowired
	private UsherScheduleTableModel mObjScheduleTableModel;
	
	public UsherScheduleMainFrame()
	{
		super("New Goshenhoppen Usher Schedule");
	}
	
	protected JMenuItem createMenuItemFromAction(final Action actItem)
	{
		JMenuItem mnu = new JMenuItem(actItem);
		
		mnu.setFont(getFont().deriveFont(Font.BOLD));
		
		return(mnu);
	}
	
	protected Action getCreateScheduleMenuAction()
	{
		return(mActScheduleCreateMenu);
	}
	
	protected Action getExitAction()
	{
		if(mActExit == null)
			mActExit = new Exit(this);
		
		return(mActExit);
	}
	
	protected Model getModel()
	{
		return(mObjModel);
	}
	
	protected Action getRemoveScheduleAction()
	{
		if(mActScheduleRemove == null)
			mActScheduleRemove = new Remove();
		
		return(mActScheduleRemove);
	}
	
	protected JMenu getScheduleMenu()
	{
		JMenu mnuSchedule = new JMenu("Schedule");
		
		mnuSchedule.setMnemonic(KeyEvent.VK_S);
		mnuSchedule.setFont(getFont().deriveFont(Font.BOLD));
		
		mnuSchedule.add(createMenuItemFromAction(getCreateScheduleMenuAction()));
		mnuSchedule.add(createMenuItemFromAction(getSelectScheduleAction()));
		mnuSchedule.add(createMenuItemFromAction(getRemoveScheduleAction()));
		mnuSchedule.addSeparator();
		mnuSchedule.add(createMenuItemFromAction(getExitAction()));
		
		return(mnuSchedule);
	}
	
	protected JScrollPane getScheduleTableScrollPane()
	{
		JScrollPane scrSchedule = new JScrollPane(getScheduleTable(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		return(scrSchedule);
	}
	
	protected JTable getScheduleTable()
	{
		if(mTblSchedule == null)
			{
			mTblSchedule = new JTable(getScheduleTableModel());
			
			mTblSchedule.setFont(getFont());
			}
		
		return(mTblSchedule);
	}
	
	protected UsherScheduleTableModel getScheduleTableModel()
	{
		return(mObjScheduleTableModel);
	}
	
	protected Action getSelectScheduleAction()
	{
		if(mActScheduleSelect == null)
			mActScheduleSelect = new Edit();
		
		return(mActScheduleSelect);
	}
	
	protected void initControl()
	{
		setLayout(new BorderLayout(5, 5));
		
		add(getScheduleTableScrollPane(), BorderLayout.CENTER); 
	}
	
	@PostConstruct
	protected void initFrame()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setFont(new Font("Tahana", Font.PLAIN, 10));
		
		initControl();
		initMenu();
		
		pack();
	}
	
	protected void initMenu()
	{
		JMenuBar barMenu = new JMenuBar();
		
		barMenu.add(getScheduleMenu());
		
		setJMenuBar(barMenu);
	}
}