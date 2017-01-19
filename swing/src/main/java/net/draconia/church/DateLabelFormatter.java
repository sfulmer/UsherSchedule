package net.draconia.church;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;

public class DateLabelFormatter extends AbstractFormatter
{
	private static final long serialVersionUID = 2813949308943849800L;
	
	private String sDatePattern = "MM dd, yyyy";
    private SimpleDateFormat mObjDateFormatter = new SimpleDateFormat(sDatePattern);

    protected SimpleDateFormat getDateFormatter()
    {
    	return(mObjDateFormatter);
    }
    
    public Object stringToValue(final String sText) throws ParseException
    {
        return(getDateFormatter().parseObject(sText));
    }
    
    public String valueToString(final Object objValue) throws ParseException
    {
        if(objValue != null)
        	{
            Calendar objCalendar = ((Calendar)(objValue));
            
            return(getDateFormatter().format(objCalendar.getTime()));
        	}

        return("");
    }
}