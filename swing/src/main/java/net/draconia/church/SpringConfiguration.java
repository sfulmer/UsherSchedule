package net.draconia.church;

import javax.sql.DataSource;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.apache.commons.dbcp.BasicDataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import net.draconia.ApplicationContextProvider;
import net.draconia.church.usherschedule.domain.Schedule;
import net.draconia.church.usherschedule.windows.schedule.panels.Day.models.DayScheduleComboBoxModel;
import net.draconia.ui.listdetails.ButtonsPanel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

@Configuration
@ComponentScan("net.draconia.church.usherschedule")
public class SpringConfiguration
{
	private ApplicationContextProvider mObjApplicationContextProvider;
	private ButtonsPanel mBtnPnlScheduleDetail, mBtnPnlScheduleList, mBtnPnlScheduleMain;
	private DataSource mObjDataSource;
	private DayScheduleComboBoxModel mObjDayScheduleComboBoxModel;
	private JComboBox<Schedule> mCboDaySchedule;
	private JDatePickerImpl mDPDayDateField;
	private JLabel mLblScheduleDetailsId, mLblScheduleDetailsName;
	private JScrollPane mScrPnlScheduleList;
	private JTable mTblScheduleList;
	private JTextField mTxtDayDetailsId;
	private JTextField mTxtScheduleDetailsId, mTxtScheduleDetailsName;
	private UtilDateModel mObjDayDateFieldDateModel;
	
	@Bean(name="applicationContextProvider")
	public ApplicationContextProvider getApplicationContextProvider()
	{
		if(mObjApplicationContextProvider == null)
			mObjApplicationContextProvider = new ApplicationContextProvider();
		
		return(mObjApplicationContextProvider);
	}
	
	@Bean(destroyMethod="close", name="dataSource")
	public DataSource getDataSource()
	{
		if(mObjDataSource == null)
			{
			mObjDataSource = new BasicDataSource();
			
			((BasicDataSource)(mObjDataSource)).setDriverClassName("com.mysql.jdbc.Driver");
			((BasicDataSource)(mObjDataSource)).setPassword("G0d 1$ sT!lL L1$t3n!nG!");
			((BasicDataSource)(mObjDataSource)).setUrl("jdbc:mysql://localhost:3306/newgoshucc");
			((BasicDataSource)(mObjDataSource)).setUsername("newgoshucc");
			}
		
		return(mObjDataSource);
	}
	
	@Bean(name="DayDetailsId")
	public JTextField getDayDetailsId()
	{
		if(mTxtDayDetailsId == null)
			{
			mTxtDayDetailsId = new JTextField(30);
			mTxtDayDetailsId.setBorder(BorderFactory.createLoweredBevelBorder());;
			mTxtDayDetailsId.setEnabled(false);
			}
		
		return(mTxtDayDetailsId);
	}
	
	@Bean(name="dayDateField")
	public JDatePickerImpl getDayDateField()
	{
		if(mDPDayDateField == null)
			mDPDayDateField = new JDatePickerImpl(new JDatePanelImpl(getDayDateFieldDateModel()), new DateLabelFormatter());
		
		return(mDPDayDateField);
	}
	
	@Bean(name="dayDateFieldDateModel")
	public UtilDateModel getDayDateFieldDateModel()
	{
		if(mObjDayDateFieldDateModel == null)
			mObjDayDateFieldDateModel = new UtilDateModel();
		
		return(mObjDayDateFieldDateModel);
	}
	
	@Bean(name="DayScheduleComboBox")
	public JComboBox<Schedule> getDayScheduleComboBox()
	{
		if(mCboDaySchedule == null)
			{
			mCboDaySchedule = new JComboBox<Schedule>(getDayScheduleComboBoxModel());
			mCboDaySchedule.setBorder(BorderFactory.createLoweredBevelBorder());;
			}
		
		return(mCboDaySchedule);
	}
	
	@Bean(name="DayScheduleComboBoxModel")
	public DayScheduleComboBoxModel getDayScheduleComboBoxModel()
	{
		if(mObjDayScheduleComboBoxModel == null)
			mObjDayScheduleComboBoxModel = new DayScheduleComboBoxModel();
		
		return(mObjDayScheduleComboBoxModel);
	}
	
	@Bean(name="ScheduleDetailsId")
	public JTextField getScheduleDetailsId()
	{
		if(mTxtScheduleDetailsId == null)
			{
			mTxtScheduleDetailsId = new JTextField(30);
			
			mTxtScheduleDetailsId.setBorder(BorderFactory.createLoweredBevelBorder());
			mTxtScheduleDetailsId.setEnabled(false);
			}
		
		return(mTxtScheduleDetailsId);
	}
	
	@Bean(name="ScheduleDetailsIdLabel")
	public JLabel getScheduleDetailsIdLabel()
	{
		if(mLblScheduleDetailsId == null)
			{
			mLblScheduleDetailsId = new JLabel("Id:");
			mLblScheduleDetailsId.setLabelFor(getScheduleDetailsId());
			}
		
		return(mLblScheduleDetailsId);
	}
	
	@Bean(name="ScheduleDetailsName")
	public JTextField getScheduleDetailsName()
	{
		if(mTxtScheduleDetailsName == null)
			{
			mTxtScheduleDetailsName = new JTextField(30);
			
			mTxtScheduleDetailsName.setBorder(BorderFactory.createLoweredBevelBorder());;
			mTxtScheduleDetailsName.setEnabled(false);
			}
		
		return(mTxtScheduleDetailsName);
	}
	
	@Bean(name="ScheduleDetailsNameLabel")
	public JLabel getScheduleDetailsNameLabel()
	{
		if(mLblScheduleDetailsName == null)
			{
			mLblScheduleDetailsName = new JLabel("Name:");
			mLblScheduleDetailsName.setLabelFor(getScheduleDetailsName());
			}
		
		return(mLblScheduleDetailsName);
	}
	
	@Bean(name="ScheduleDetailButtonPanel")
	public ButtonsPanel getScheduleDetailButtonPanel()
	{
		if(mBtnPnlScheduleDetail == null)
			mBtnPnlScheduleDetail = new ButtonsPanel();
		
		return(mBtnPnlScheduleDetail);
	}
	
	@Bean(name="ScheduleListButtonPanel")
	public ButtonsPanel getScheduleListButtonPanel()
	{
		if(mBtnPnlScheduleList == null)
			mBtnPnlScheduleList = new ButtonsPanel();
		
		return(mBtnPnlScheduleList);
	}
	
	@Bean(name="ScheduleListScrollPane")
	public JScrollPane getScheduleListScrollPane()
	{
		if(mScrPnlScheduleList == null)
			{
			mScrPnlScheduleList = new JScrollPane(getScheduleListTable(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			mScrPnlScheduleList.setEnabled(false);
			}
		
		return(mScrPnlScheduleList);
	}
	
	@Bean(name="tblScheduleList")
	public JTable getScheduleListTable()
	{
		if(mTblScheduleList == null)
			mTblScheduleList = new JTable();
		
		return(mTblScheduleList);
	}
	
	@Bean(name="ScheduleMainButtonPanel")
	public ButtonsPanel getScheduleMainButtonPanel()
	{
		if(mBtnPnlScheduleMain == null)
			mBtnPnlScheduleMain = new ButtonsPanel();
		
		return(mBtnPnlScheduleMain);
	}
}