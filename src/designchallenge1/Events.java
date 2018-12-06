package designchallenge1;


public class Events 
{
	protected String name;
	protected int day;
	protected int month;
	protected int year;
	protected String color;
	
	public Events(String name, int day, int month, int year, String color)
	{
		this.name = name;
		this.day = day;
		this.month = month;
		this.year = year;
		this.color = color;
	}

	public String getName() 
	{
		return name;
	}

	public int getDay() 
	{
		return day;
	}

	public int getMonth() 
	{
		return month;
	}

	public int getYear() 
	{
		return year;
	}

	public String getColor() 
	{
		return color;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public void setDay(int day) 
	{
		this.day = day;
	}

	public void setMonth(int month) 
	{
		this.month = month;
	}

	public void setYear(int year) 
	{
		this.year = year;
	}

	public void setColor(String color) 
	{
		this.color = color;
	}
	
	public boolean equals(Events event)
	{
		if(this.name.equals(event.name) && this.day == event.day && event.month == this.month &&
				this.year == event.year)
		{
			return true;
		}
		else
			return false;
	}
	
}
