package net.draconia.church.usherschedule.windows.day.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.text.JTextComponent;

import net.draconia.ApplicationContextProvider;
import net.draconia.church.usherschedule.domain.Day;
import net.draconia.church.usherschedule.windows.day.panels.Details.actions.DayApply;
import net.draconia.church.usherschedule.windows.day.panels.Details.actions.DayCancel;
import net.draconia.church.usherschedule.windows.day.panels.Details.actions.DaySave;
import net.draconia.church.usherschedule.windows.day.panels.Details.listeners.DayDetailsFieldBindingEditingChangeListener;
import net.draconia.church.usherschedule.windows.day.panels.Details.observers.DayDetailsEditingObserver;
import net.draconia.church.usherschedule.windows.day.panels.Details.observers.DayDetailsModelObserver;
import net.draconia.church.usherschedule.windows.day.ui.model.DayDetailsPanelModel;
import net.draconia.ui.listdetails.DetailsPanel;
import net.draconia.ui.listdetails.EnablablePanel;
import net.draconia.ui.listdetails.actions.Apply;
import net.draconia.ui.listdetails.actions.Save;
import net.draconia.ui.listdetails.observers.DirtyObserver;
import net.sourceforge.jdatepicker.DateModel;

import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Component;

@Component
public class DayDetailsPanel extends DetailsPanel<Day>
{
	private static final long serialVersionUID = 7267421373897787571L;
	
	@Autowired
	private ApplicationContextProvider mObjApplicationContextProvider;
	
	@Autowired
	private DateModel<Date> mObjDate;
	
	@Autowired
	private DayServicesPanel mPnlService;
	
	private JDatePickerImpl mDPDate;
	
	private JTextField mTxtId;
	
	public DayDetailsPanel()
	{
		super(new BorderLayout(5, 5));
	}
	
	protected void addModelObservers()
	{
		String[] sArrFields = new String[] {"Id"};
		JTextComponent[] txtArrFields = new JTextComponent[] {getIdField()};
		
		getModel().addObserver(new DirtyObserver<Day>(getApplyAction(), getSaveAction()));
		getModel().addObserver(new DayDetailsEditingObserver(this));
		getModel().addPropertyChangeListener(new DayDetailsFieldBindingEditingChangeListener(sArrFields, txtArrFields));
		getModel().addObserver(((DayDetailsModelObserver)(getBean(DayDetailsModelObserver.class))));
	}
	
	protected ApplicationContext getApplicationContext()
	{
		return(getApplicationContextProvider().getApplicationContext());
	}
	
	protected ApplicationContextProvider getApplicationContextProvider()
	{
		return(mObjApplicationContextProvider);
	}
	
	protected Apply<Day> getApplyAction()
	{
		if(super.getApplyAction() == null)
			setApplyAction((DayApply)(getBean(DayApply.class)));
		
		return(super.getApplyAction());
	}
	
	protected Object getBean(final Class<?> clsBean)
	{
		return(getApplicationContext().getBean(clsBean));
	}
	
	protected Object getBean(final String sBeanName)
	{
		return(getApplicationContext().getBean(sBeanName));
	}
	
	protected JDatePickerImpl getDateField()
	{
		if(mDPDate == null)
			setDateField((JDatePickerImpl)(getBean("dayDateField")));
		
		return(mDPDate);
	}
	
	protected JLabel getDateLabel()
	{
		JLabel lblDate = new JLabel("Date:");
		
		lblDate.setFont(getFont().deriveFont(Font.BOLD));
		lblDate.setLabelFor(getDateField());
		lblDate.setOpaque(false);
		
		return(lblDate);
	}
	
	@SuppressWarnings("unchecked")
	protected DateModel<Date> getDateModel()
	{
		if(mObjDate == null)
			mObjDate = (DateModel<Date>)(getBean("dayDateFieldDateModel"));
		
		return(mObjDate);
	}
	
	protected JPanel getFieldsPanel()
	{
		GridBagConstraints objConstraints = new GridBagConstraints();
		JPanel pnl = new EnablablePanel(new GridBagLayout());
		
		objConstraints.gridx = objConstraints.gridy = 0;
		objConstraints.gridwidth = objConstraints.gridheight = 1;
		objConstraints.fill = GridBagConstraints.BOTH;
		objConstraints.anchor = GridBagConstraints.NORTHWEST;
		objConstraints.insets = new Insets(5, 5, 5, 5);
		pnl.add(getIdLabel(), objConstraints);
		
		objConstraints.gridx++;
		pnl.add(getIdField(), objConstraints);
		
		objConstraints.gridx = 0;
		objConstraints.gridy++;
		pnl.add(getDateLabel(), objConstraints);
		
		objConstraints.gridx++;
		pnl.add(getDateField(), objConstraints);
		
		objConstraints.gridwidth = 2;
		objConstraints.gridx = 0;
		objConstraints.gridy++;
		pnl.add(getServicePanel(), objConstraints);
		
		return(pnl);
	}
	
	protected JTextField getIdField()
	{
		if(mTxtId == null)
			setIdField((JTextField)(getBean("DayDetailsId"))); 
		
		return(mTxtId);
	}
	
	protected JLabel getIdLabel()
	{
		JLabel lblId = new JLabel("Id:");
		
		lblId.setFont(getFont().deriveFont(Font.BOLD));
		lblId.setLabelFor(getIdField());
		lblId.setOpaque(false);
		
		return(lblId);
	}
	
	protected Save<Day> getSaveAction()
	{
		if(super.getSaveAction() == null)
			setSaveAction((DaySave)(getBean(DaySave.class)));
		
		return(super.getSaveAction());
	}
	
	protected DayServicesPanel getServicePanel()
	{
		return(mPnlService);
	}
	
	@Autowired
	protected void setApplyAction(final DayApply actApply)
	{
		if(actApply == null)
			super.setApplyAction((DayApply)(getBean(DayApply.class)));
		else
			super.setApplyAction(actApply);
	}
	
	@Autowired
	protected void setCancelAction(final DayCancel actCancel)
	{
		if(actCancel == null)
			super.setCancelAction((DayCancel)(getBean(DayCancel.class)));
		else
			super.setCancelAction(actCancel);
	}
	
	@Autowired
	@Qualifier("dayDateField")
	protected void setDateField(final JDatePickerImpl dpDate)
	{
		if(dpDate == null)
			mDPDate = (JDatePickerImpl)(getBean("dayDateField"));
		else
			mDPDate = dpDate;
		
		mDPDate.getComponent(1).setEnabled(false);
	}
	
	@Autowired
	@Qualifier("DayDetailsId")
	protected void setIdField(final JTextField txtId)
	{
		mTxtId = txtId;
		
		mTxtId.setFont(getFont());
	}
	
	@Autowired
	public void setModel(final DayDetailsPanelModel objModel)
	{
		super.setModel(objModel);
	}
	
	@Autowired
	protected void setSaveAction(final DaySave actSave)
	{
		if(actSave == null)
			super.setSaveAction((DaySave)(getBean(DaySave.class)));
		else
			super.setSaveAction(actSave);
	}
	
	protected void setServicesPanel(final DayServicesPanel pnlServices)
	{
		mPnlService = pnlServices;
	}
}