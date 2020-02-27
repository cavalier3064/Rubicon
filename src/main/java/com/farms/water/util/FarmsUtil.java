package com.farms.water.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Component;
@Component
public class FarmsUtil {
	/*
	 * Validate Date from GUI
	 */
	public static boolean isValidDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");
		sdf.setLenient(false);
		try {
			// if not valid, it will throw ParseException
			sdf.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public boolean getDate(String stringDate) {

		try {
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");
			Date date = formatter.parse(stringDate);
			Date currentDate = new Date();
			if(currentDate.getTime()<=date.getTime()) {
				return true;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/*
	 * Get the diffrence date between Existing and present order date
	 */
	public boolean diffrenceDate(String existingDateTime,String newDateTime,int durationhour) {
		
		try {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");
		Date exstartdate = formatter.parse(existingDateTime);
		
		Date newdate = formatter.parse(newDateTime);
		
		Date durationHour = addHoursToJavaUtilDate(exstartdate,durationhour);
		
		long durationTime = durationHour.getTime(); 
		long exstartdateTime = exstartdate.getTime();
		long newdateTime= newdate.getTime();
		
		long diff = exstartdate.getTime() - newdate.getTime();
        if(newdateTime>=exstartdateTime && newdateTime<=durationTime) {
        	return true;
        }
       	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
        return false;
	}
	
	/*
	 * Get the status between two times
	 */
	public String equalsDateTime(String dateTime,int durationhour) {
		String status = null;
		//boolean duration = false;
		try {
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");
		Date startdate = formatter.parse(dateTime);
		
		Date dt2 = new Date();
		long starttime =startdate.getTime();
		
		Date durationTime=addHoursToJavaUtilDate(startdate, durationhour);
		
		long currenttie = dt2.getTime();
		long diff = startdate.getTime() - dt2.getTime();
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000);
        int diffInDays = (int) ((dt2.getTime() - startdate.getTime()) / (1000 * 60 * 60 * 24));
       
       if (starttime>currenttie) {
    	   status="Requested";
            return status;
        }
       else if(starttime<=currenttie && durationTime.getTime()>=currenttie) {
    	   status="Started";
    	   return status;
       }
       else if (currenttie == durationTime.getTime()) {
    	   status="Stopped";
    	   return status;
       }
      
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	/*
	 * Add duration hours to date
	 */
	
	public Date addHoursToJavaUtilDate(Date date, int hours) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.HOUR_OF_DAY, hours);
	    return calendar.getTime();
	}
	/*
	 * Cancel order when order is not delivered
	 */
	public boolean cancelDate(String existingDateTime,int durationhour)throws Exception{
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");
		Date startdate = formatter.parse(existingDateTime);
		Date durationHour = addHoursToJavaUtilDate(startdate,durationhour);
		Date dt2 = new Date();
		long startDurationtime =durationHour.getTime();
		long currenttie = dt2.getTime();
		if(currenttie<=startDurationtime) {
			return true;
		}
		return false;
	}

}
