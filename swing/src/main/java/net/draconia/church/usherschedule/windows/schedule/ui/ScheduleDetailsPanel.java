package net.draconia.church.usherschedule.windows.schedule.ui;

import net.draconia.ApplicationContextProvider;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.annotation.PostConstruct;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import net.draconia.church.usherschedule.domain.Schedule;

import net.draconia.church.usherschedule.windows.schedule.panels.Details.actions.ScheduleApply;
import net.draconia.church.usherschedule.windows.schedule.panels.Details.actions.ScheduleCancel;
import net.draconia.church.usherschedule.windows.schedule.panels.Details.actions.ScheduleSave;
import net.draconia.church.usherschedule.windows.schedule.panels.Details.listeners.ScheduleDetailsFieldBindingEditingChangeListener;
import net.draconia.church.usherschedule.windows.schedule.panels.Details.observers.ScheduleDetailsEditingObserver;
import net.draconia.church.usherschedule.windows.schedule.panels.Details.observers.ScheduleDetailsModelObserver;
import net.draconia.church.usherschedule.windows.schedule.ui.model.ScheduleDetailsPanelModel;
import net.draconia.ui.listdetails.ButtonsPanel;
import net.draconia.ui.listdetails.DetailsPanel;
import net.draconia.ui.listdetails.EnablablePanel;
import net.draconia.ui.listdetails.actions.Apply;
import net.draconia.ui.listdetails.actions.Cancel;
import net.draconia.ui.listdetails.actions.Save;
import net.draconia.ui.listdetails.model.DetailsPanelModel;
import net.draconia.ui.listdetails.observers.DirtyObserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Component;

@Component
public class ScheduleDetailsPanel extends DetailsPanel<Schedule>
{
	private static final long serialVersionUID = -3654518916162120544L;
	
	@Autowired
	private ApplicationContextProvider mObjApplicationContextProvider;
	
	private JLabel mLblId, mLblName;
	private JTextField mTxtId, mTxtName;
	
	private ScheduleDayPanel mPnlDays;
	
	public ScheduleDetailsPanel()
	{
		super(new BorderLayout(5, 5));
	}
	
	protected void addModelObservers()
	{
		String[] sArrFields = new String[] {"Id", "Name"};
		JTextComponent[] txtArrFields = new JTextComponent[] {getIdField(), getNameField()};
		
		getModel().addObserver(new DirtyObserver<Schedule>(getApplyAction(), getSaveAction()));
		getModel().addObserver(new ScheduleDetailsEditingObserver(this));
		getModel().addPropertyChangeListener(new ScheduleDetailsFieldBindingEditingChangeListener(sArrFields, txtArrFields));
		getModel().addObserver(((ScheduleDetailsModelObserver)(getBean(ScheduleDetailsModelObserver.class))));
	}
	
	protected ApplicationContextProvider getApplicationContextProvider()
	{
		return(mObjApplicationContextProvider);
	}
	
	protected Apply<Schedule> getApplyAction()
	{
		if(super.getApplyAction() == null)
			setApplyAction((ScheduleApply)(getBean(ScheduleApply.class)));
		
		return(super.getApplyAction());
	}
	
	protected Object getBean(final Class<?> clsBean)
	{
		return(getContext().getBean(clsBean));
	}
	
	protected Object getBean(final String sName)
	{
		return(getContext().getBean(sName));
	}
	
	protected ApplicationContext getContext()
	{
		return(getApplicationContextProvider().getApplicationContext());
	}
	
	protected ScheduleDayPanel getDaysPanel()
	{
		if(mPnlDays == null)
			setDaysPanel((ScheduleDayPanel)(getBean(ScheduleDayPanel.class)));
		
		return(mPnlDays);
	}
	
	protected JPanel getFieldsPanel()
	{
		EnablablePanel pnlFields = new EnablablePanel(new GridBagLayout());
		GridBagConstraints objConstraints = new GridBagConstraints();
		
		objConstraints.gridx = objConstraints.gridy = 0;
		objConstraints.gridwidth = objConstraints.gridheight = 1;
		objConstraints.fill = GridBagConstraints.BOTH;
		objConstraints.anchor = GridBagConstraints.NORTHWEST;
		objConstraints.insets = new Insets(5, 5, 5, 5);
		pnlFields.add(getIdLabel(), objConstraints);
		
		objConstraints.gridx++;
		pnlFields.add(getIdField(), objConstraints);
		
		objConstraints.gridx = 0;
		objConstraints.gridy++;
		pnlFields.add(getNameLabel(), objConstraints);
		
		objConstraints.gridx++;
		pnlFields.add(getNameField(), objConstraints);

		objConstraints.gridwidth = 2;
		objConstraints.gridx = 0;
		objConstraints.gridy++;
		pnlFields.add(getDaysPanel(), objConstraints);
		
		return(pnlFields);
	}
	
	protected Cancel<Schedule> getCancelAction()
	{
		if(super.getCancelAction() == null)
			setCancelAction(new ScheduleCancel());
		
		return(super.getCancelAction());
	}
	
	protected JTextField getIdField()
	{
		if(mTxtId == null)
			setIdField((JTextField)(getBean("ScheduleDetailsId")));
		
		return(mTxtId);
	}
	
	protected JLabel getIdLabel()
	{
		return(mLblId);
	}
	
	public DetailsPanelModel<Schedule> getModel()
	{
		if(super.getModel() == null)
			setModel((ScheduleDetailsPanelModel)(getBean(ScheduleDetailsPanelModel.class)));
		
		return(super.getModel());
	}
	
	protected JTextField getNameField()
	{
		if(mTxtName == null)
			setNameField((JTextField)(getBean("ScheduleDetailsName")));
		
		return(mTxtName);
	}
	
	protected JLabel getNameLabel()
	{
		return(mLblName);
	}
	
	protected Save<Schedule> getSaveAction()
	{
		if(super.getSaveAction() == null)
			setSaveAction(((ScheduleSave)(getBean(ScheduleSave.class))));
		
		return(super.getSaveAction());
	}
	
	@PostConstruct
	protected void initPanel()
	{
		super.initPanel();
		
		addFocusListener(new FocusAdapter()
		{
			public void focusGained(final FocusEvent objFocusEvent)
			{
				getNameField().requestFocusInWindow();
			}
		});
	}
	
	@Autowired
	protected void setApplyAction(final ScheduleApply actApply)
	{
		super.setApplyAction(actApply);
	}
	
	@Autowired
	@Qualifier("ScheduleDetailButtonPanel")
	protected void setButtonsPanel(ButtonsPanel pnlButtons)
	{
		super.setButtonsPanel(pnlButtons);
		
		getButtonsPanel().setButtons(new Action[] {getApplyAction(), getSaveAction(), getCancelAction()});
		
		getButtonsPanel().setEnabled(isEnabled());
		
		for(JButton btn : getButtonsPanel().getButtons())
			btn.setEnabled(false);
	}
	
	@Autowired
	protected void setCancelAction(ScheduleCancel actCancel)
	{
		super.setCancelAction(actCancel);
	}
	
	@Autowired
	protected void setDaysPanel(final ScheduleDayPanel pnlDays)
	{
		if(pnlDays == null)
			mPnlDays = ((ScheduleDayPanel)(getBean(ScheduleDayPanel.class)));
		else
			mPnlDays = pnlDays;
		
		mPnlDays.setSchedule(getModel().getWorkingModel());
	}
	
	@Autowired
	@Qualifier("ScheduleDetailsId")
	protected void setIdField(final JTextField txtId)
	{
		mTxtId = txtId;
		
		mTxtId.setBorder(BorderFactory.createLoweredBevelBorder());
		mTxtId.setEditable(false);
		mTxtId.setEnabled(false);
		mTxtId.setFont(getFont());
	}
	
	@Autowired
	@Qualifier("ScheduleDetailsIdLabel")
	protected void setIdLabel(final JLabel lblId)
	{
		mLblId = lblId;
		
		mLblId.setDisplayedMnemonic(KeyEvent.VK_I);
		mLblId.setEnabled(false);
		mLblId.setFont(getFont().deriveFont(Font.BOLD));
		mLblId.setLabelFor(getIdField());
		mLblId.setOpaque(false);
	}
	
	@Autowired
	public void setModel(final ScheduleDetailsPanelModel objModel)
	{
		super.setModel(objModel);
		
		getModel().addPropertyChangeListener(new PropertyChangeListener()
		{	
			public void propertyChange(PropertyChangeEvent objPropertyChangeEvent)
			{
				if(objPropertyChangeEvent.getNewValue().equals(true))
				{
				setEnabled(true);
				getNameField().requestFocusInWindow();
				}
			}
		});
		
	}
	
	@Autowired
	@Qualifier("ScheduleDetailsName")
	protected void setNameField(final JTextField txtName)
	{
		mTxtName = txtName;
		
		mTxtName.setBorder(BorderFactory.createLoweredBevelBorder());
		mTxtName.setEditable(true);
		mTxtName.setEnabled(false);
		mTxtName.setFont(getFont());
	}
	
	@Autowired
	@Qualifier("ScheduleDetailsNameLabel")
	protected void setNameLabel(final JLabel lblName)
	{
		mLblName = lblName;
		
		mLblName.setDisplayedMnemonic(KeyEvent.VK_N);
		mLblName.setEnabled(false);
		mLblName.setFont(getFont().deriveFont(Font.BOLD));
		mLblName.setLabelFor(getNameField());
		mLblName.setOpaque(false);
	}
	
	@Autowired
	protected void setSaveAction(final ScheduleSave actSave)
	{
		super.setSaveAction(actSave);
	}
}