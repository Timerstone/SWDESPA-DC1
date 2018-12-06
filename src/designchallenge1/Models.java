package designchallenge1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Models 
{
	private ArrayList<Events> list;
	private String CSVfilename;
	private String PSfilename;
	private BufferedWriter writer;
	private BufferedReader reader;
	
	public Models()
	{
		list = new ArrayList<Events>();
		CSVfilename = "Sample Files\\Philippine Holidays.csv";
		PSfilename = "Sample Files\\DLSU Unicalendar.psv";
	}
	
	public void loadEvents()
	{
		String temp = "";
		String[] split;
		String name;
		String color;
		int day, month, year;
		boolean check = true;
		Events event;
		
		//csv read
		//holidays
		try
		{
			reader = new BufferedReader(new FileReader(CSVfilename));
			
			while((temp = reader.readLine()) != null)
			{
				split = temp.split(",");
				
				name = split[1];
				color = split[2];
				color = color.replaceAll(" ","");
				
				temp = split[0];
				split = temp.split("/");
				
				day = Integer.valueOf(split[1]);
				month = Integer.valueOf(split[0]);
				year = 0;
				
				event = new Events(name, day, month, year, color);
				
				for(int i = 0; i < list.size(); i++)
				{
					if(list.get(i).equals(event)) {
						i = list.size();
						check = false;
					}
					else {
						check = true;
					}
				}
				
				if(check)
				{
					addEvent(event);
				}
			}
			
			reader.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		//psv
		try
		{
			reader = new BufferedReader(new FileReader(PSfilename));
			
			while((temp = reader.readLine()) != null)
			{
				split = temp.split("\\|");
				
				name = split[0];
				color = split[2].replaceAll(" ", "");
				
				temp = split[1].replaceAll(" ", "");
				split = temp.split("/");
				
				day = Integer.valueOf(split[1]);
				month = Integer.valueOf(split[0]);
				year = Integer.valueOf(split[2]);
				
				event = new Events(name, day, month, year, color);
				
				for(int i = 0; i < list.size(); i++)
				{
					if(list.get(i).equals(event)) {
						i = list.size();
						check = false;
					}
					else {
						check = true;
					}
				}
				
				if(check)
				{
					addEvent(event);
				}
			}
			
			reader.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void saveEvents()
	{
		Events event;
		
		try
		{
			writer = new BufferedWriter(new FileWriter(CSVfilename));
			for(int i = 0; i < list.size(); i++)
			{
				event = list.get(i);
				if(event.getYear() == 0) {
					writer.write(event.getMonth() + "/" + event.getDay() + "/");
					writer.write(event.getYear() + ", " + event.getName() + ", "+ event.getColor() + "\n");
				}
			}
			
			writer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		try
		{
			writer = new BufferedWriter(new FileWriter(PSfilename));
			for(int i = 0; i < list.size(); i++)
			{
				event = list.get(i);
				
				
				if(event.getYear() != 0) {
					writer.write(event.getName() + "| " + event.getMonth() + "/" + event.getDay() + "/");
					writer.write(event.getYear() + " | " + event.getColor() + "\n");
				}
			}
			
			writer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
/*	public void readSavedFiles()
	{
		String temp = "";
		String[] split;
		String name;
		String color;
		int day, month, year;
		boolean check = true;
		Events event;
		
		try
		{
			reader = new BufferedReader(new FileReader("SavedEvents.csv"));
			
			while((temp = reader.readLine()) != null)
			{
				split = temp.split(",");
				
				name = split[0];
				color = split[4];
				day = Integer.valueOf(split[1]);
				month = Integer.valueOf(split[2]);
				year = Integer.valueOf(split[3]);
				
				event = new Events(name, day, month, year, color);
				
				for(int i = 0; i < list.size(); i++)
				{
					if(list.get(i).equals(event)) {
						i = list.size();
						check = false;
					}
					else {
						check = true;
					}
				}
				
				if(check)
				{
					addEvent(event);
				}
			}
			
			reader.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	*/

	public void addEvent(Events event)
	{
		list.add(event);
	}
	
	public void removeEvent(Events event)
	{
		list.remove(event);
	}
	
	public Events getEvent(String name, int day, int month, int year)
	{
		int i;
		Events temp = null;
		
		for(i = 0; i < list.size(); i++){
			
			if(list.get(i).getName().equals(name) && list.get(i).getDay() == day &&
					list.get(i).getMonth() == month && list.get(i).getYear() == year){
				
				temp = list.get(i);
			}
		}
		
		return temp;
	}
	
	public ArrayList<Events> getEvents()
	{
		return list;
	}
}
