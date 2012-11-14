package data;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MyDate {

	private int Year;
	private int Month;
	private int Day;
	
	public MyDate(int _year,int _month,int _day){
		Year = _year;
		Month = _month;
		Day = _day;
	}//MyDate
	
	public MyDate(){
		Calendar cal1 = new GregorianCalendar();
		Year = cal1.get(Calendar.YEAR);
		Month = cal1.get(Calendar.MONTH)+1;
		Day = cal1.get(Calendar.DAY_OF_MONTH);
	}//MyDate
	
	public int GetYear(){return Year;}
	public int GetMonth(){return Month;}
	public int GetDay(){return Day;}
	
	public void SetYear(int _y){Year = _y;}
	public void SetMonth(int _m){Month = _m;}
	public void SetDay(int _d){Day = _d;}
	
	public int DistanceFromDate(MyDate _date){
		Calendar cal1 = new GregorianCalendar();
		Calendar cal2 = new GregorianCalendar();
		cal1.set(this.GetYear(), this.GetMonth()-1,this.GetDay() );
		cal2.set(_date.GetYear(), _date.GetMonth()-1, _date.GetDay());
		
		int days =  DaysBetween(cal1.getTime(),cal2.getTime());
		
		return days;
	}//DistanceFromDate
	
	
	private static int DaysBetween(Date d1, Date d2){
		 return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}//DaysBetween
	
	public String toString(){
		return (this.GetDay()+"-"+this.GetMonth()+"-"+this.GetYear());
	}//toString()
	
	
	public static int DISTANCE_DAYS = 0;
	
	
}//MyDate