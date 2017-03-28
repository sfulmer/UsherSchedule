package net.draconia.church.usherschedule.windows.day.ui;

import net.draconia.ApplicationContextProvider;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.domain.Schedule;
import net.draconia.church.usherschedule.windows.day.ui.model.DayListDetailPanelModel;
import net.draconia.church.usherschedule.windows.day.ui.observers.DayListDetailPanelModelObserver;
import net.draconia.ui.listdetails.EnablablePanel;
import net.draconia.ui.listdetails.ListDetailsPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DayListDetailPanel extends ListDetailsPanel<Day>
{
	private static final long serialVersionUID = 1695462707595523428L;
	
	@Autowired
	private ApplicationContextProvider mObjApplicationContextProvider;
	
	private DayListDetailPanelModel mObjModel;
	
	private JComboBox<Schedule> mCboSchedule;
	
	public DayListDetailPanel()
	{
		super();
	}
	
	protected ApplicationContext getApplicationContext()
	{
		return(getApplicationContextProvider().getApplicationContext());
	}
	
	protected ApplicationContextProvider getApplicationContextProvider()
	{
		return(mObjApplicationContextProvider);
	}
	
	protected Object getBean(final Class<?> clsBean)
	{
		return(getApplicationContext().getBean(clsBean));
	}
	
	protected Object getBean(final String sBeanName)
	{
		return(getApplicationContext().getBean(sBeanName));
	}
	
	protected DayListDetailPanelModel getModel()
	{
		if(mObjModel == null)
			setModel((DayListDetailPanelModel)(getBean(DayListDetailPanelModel.class)));
		
		return(mObjModel);
	}
	
	protected DayListDetailPanelModelObserver getModelObserver()
	{
		return((DayListDetailPanelModelObserver)(getBean(DayListDetailPanelModelObserver.class)));
	}
	
	@SuppressWarnings("unchecked")
	protected JComboBox<Schedule> getScheduleDropDown()
	{
		if(mCboSchedule == null)
			setScheduleDropDown((JComboBox<Schedule>)(getBean("DayScheduleComboBox")));
		
		return(mCboSchedule);
	}
	
	protected JLabel getScheduleDropDownLabel()
	{
		JLabel lbl = new JLabel("Schedule:");
		
		lbl.setDisplayedMnemonic(KeyEvent.VK_S);
		lbl.setFont(getFont().deriveFont(Font.BOLD));
		lbl.setLabelFor(getScheduleDropDown());
		lbl.setOpaque(false);
		
		return(lbl);
	}
	
	protected EnablablePanel getScheduleDropDownPanel()
	{
		EnablablePanel pnl = new EnablablePanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		pnl.add(getScheduleDropDownLabel());
		pnl.add(getScheduleDropDown());
		
		return(pnl);
	}
	
	protected void initControls()
	{
		super.initControls();
				
		add(getScheduleDropDownPanel(), BorderLayout.NORTH);
	}
	
	@Autowired
	protected void setDetailsPanel(final DayDetailsPanel pnlDetails)
	{
		if(pnlDetails == null)
			super.setDetailsPanel((DayDetailsPanel)(getBean(DayDetailsPanel.class)));
		else
			super.setDetailsPanel(pnlDetails);
	}
	
	@Autowired
	protected void setListPanel(final DayListPanel pnlList)
	{
		if(pnlList == null)
			super.setListPanel((DayListPanel)(getBean(DayListPanel.class)));
		else
			super.setListPanel(pnlList);
	}
	
	@Autowired
	protected void setModel(final DayListDetailPanelModel objModel)
	{
		if(mObjModel != null)
			mObjModel.deleteObservers();
		
		if(objModel == null)
			mObjModel = ((DayListDetailPanelModel)(getBean(DayListDetailPanelModel.class)));
		else
			mObjModel = objModel;
		
		mObjModel.addObserver(getModelObserver());
	}
	
	@Autowired
	@Qualifier("DayScheduleComboBox")
	@SuppressWarnings("unchecked")
	protected void setScheduleDropDown(final JComboBox<Schedule> cboSchedule)
	{
		if(cboSchedule == null)
			mCboSchedule = ((JComboBox<Schedule>)(getBean("DayScheduleComboBox")));
		else
			mCboSchedule = cboSchedule;
		
		mCboSchedule.setBorder(BorderFactory.createLoweredBevelBorder());
		mCboSchedule.setFont(getFont());
	}
}