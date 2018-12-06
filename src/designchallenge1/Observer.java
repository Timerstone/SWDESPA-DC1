package designchallenge1;

import java.awt.Color;
import java.time.LocalDate;
import facebook.FBView;
import sms.SMS;
import sms.SMSView;
import java.util.Calendar;

public class Observer {
	
	private FBView fb;
	private SMSView sms;
	private Models model;
	
	public Observer(Models model)
	{
		this.model = model;
	}
	
	public void checkUpdates()
	{
		LocalDate date = LocalDate.now();
		Calendar cdate = Calendar.getInstance();
		Events temp;
		Color color = null;
		
		for(int i = 0; i < model.getEvents().size(); i++)
		{
			temp = model.getEvents().get(i);
			if(temp.getDay() == date.getDayOfMonth() 
					&& temp.getMonth() == date.getMonthValue() 
					&& (temp.getYear() == date.getYear() || temp.getYear() == 0)) {
				
				if(fb == null && sms == null)
				{
					fb = new FBView();
					sms = new SMSView();
				}
				
				switch(temp.getColor())
				{
					case "red": color = Color.RED; break;
					case "blue": color = Color.BLUE; break;
					case "green": color = Color.GREEN; break;
				}
				
				cdate.set(date.getYear(), date.getMonthValue()-1, date.getDayOfMonth());
				
				if(fb != null)
				{
					fb.showNewEvent(temp.getName(), temp.getMonth(), temp.getDay(), temp.getYear(), color);
					sms.sendSMS(new SMS(temp.getName(), cdate, color));
				}
			}
		}
	}
}
