import java.util.Calendar;

public class TimesTracker {
    String name;
    String year;
    String month;
    String day;
    String time;
    String AM_PM;

    public TimesTracker(String name, String year, String month, String day, String time, String AM_PM) {
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
        this.time = time;
        this.AM_PM = AM_PM;
    }

    public TimesTracker(String name, String day, String time, String AM_PM) {
        this.name = name;
        this.day = day;
        this.time = time;
        this.AM_PM = AM_PM;
        this.year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        this.month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH));
        if(Calendar.getInstance().get(Calendar.MONTH) < 10) {
            this.month = "0" + String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
        }else {
            this.month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
        }
    }

    public String civilianToMilitary() { //YYYY-MM-DDThh:mm:ss+00:00. The +00:00 is the timezone offset.
        String[] hourMin = time.split(":");
        if(AM_PM.equals("AM")){
            if(Integer.parseInt(hourMin[0]) < 10){
                time = "0" + hourMin[0] + ":" + hourMin[1];
            }else{
                time = hourMin[0] + ":" + hourMin[1];
            }
        }else if(AM_PM.equals("PM")) {
            time = String.valueOf(Integer.parseInt(hourMin[0]) + 12)+ ":" + hourMin[1];
        }
        return time;
    }

    public String getName(){ return name; }
    public String getYear(){ return year; }
    public String getMonth(){ return month; }
    public String getDay(){ return day;}
    public String getTime(){ return time; }
    public String getAM_PM(){ return AM_PM; }
}
