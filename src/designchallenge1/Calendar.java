package designchallenge1;

import java.time.LocalDate;

public class Calendar 
{
	private Models models;
	private Observer notifier;
	private CalendarProgram view;
	private LocalDate date;
		
	public Calendar()
	{
		models = new Models();
		models.loadEvents();
		//models.readSavedFiles();
		//models.saveEvents();
		
		notifier = new Observer(models);
		
		view = new CalendarProgram();
		view.addCalendarMain(this);
		view.refreshCalendar(view.monthToday, view.yearToday);
		
		Thread T1 = new Thread(){
			public void run()
			{
				date = LocalDate.now();
				boolean end = false;
				int day = date.getDayOfMonth();
				int month = date.getMonthValue();
				int year = date.getYear();
				boolean sent = false;
				
				while(!end)
				{
					date = LocalDate.now();
					
					if(month != date.getMonthValue() || year != date.getYear() || day != date.getDayOfMonth())
					{
						month = date.getMonthValue();
						year = date.getYear();
						day = date.getDayOfMonth();
						notifier.checkUpdates();
						
						view.monthToday = month;
						view.yearToday = year;
						view.refreshCalendar(view.monthToday, view.yearToday);
					}
					else if(checkEvents(date.getDayOfMonth(), date.getMonthValue(), date.getYear()) && !sent)
					{
						notifier.checkUpdates();
						sent = true;
					}
				}
			}
		};
		
		T1.start();
	}
	
	public Models getModels()
	{
		return models;
	}
	
	public String searchEvents(int day, int month, int year)
	{
		Events event;
		String temp = "";
		
		for(int i = 0; i < models.getEvents().size(); i++)
		{
			event = models.getEvents().get(i);
			
			if(event.getDay() == day && event.getMonth() == month && (event.getYear() == year || event.getYear() == 0)) {
				temp = temp + "<br><font color=\"" + event.getColor() + "\">" + event.getName() + "</font>";
			}
		}
		
		return temp;
	}
	
	public boolean checkEvents(int day, int month, int year)
	{
		Events event;
		
		for(int i = 0; i < models.getEvents().size(); i++)
		{
			event = models.getEvents().get(i);
			
			if(event.getDay() == day && event.getMonth() == month && (event.getYear() == year || event.getYear() == 0)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void saveEvents()
	{
		models.saveEvents();
	}
	
	public void checkUpdates()
	{
		notifier.checkUpdates();
	}
}
