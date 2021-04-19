import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.DateFormatSymbols;
import java.time.Month;
import java.util.List;

public class CalendarListener implements MessageCreateListener {

    public static String militaryToCivilian(String hour, String minute) {
        if (Integer.parseInt(hour) <= 12 ) {
            return hour + ":" + minute + " AM";
        }else {
            return Integer.parseInt(hour) - 12 + ":" + minute + " PM";
        }

    }public static String validateMonth(String month){
        if(month.length() > 2){
            int numMonth = Month.valueOf(month.toUpperCase()).getValue();
            if(numMonth < 10){
                return "0" + numMonth;
            }else {
                return String.valueOf(numMonth);
            }
        }else {
            if(Integer.parseInt(month) < 10){
                return "0" + month;
            }
        }
        return month;
    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) { //Schedules events with the format "!s name year month day time PM/AM" or "!s name day time AM/PM"
        if (event.getMessageContent().startsWith("!s")) {

            String[] calendar = event.getMessageContent().substring(1).split(" ");

            try {

                try {

                    TimesTracker tracker = new TimesTracker(calendar[1], calendar[2], calendar[3], calendar[4], calendar[5], calendar[6]);
                    EmbedBuilder embed;

                    if (tracker.getMonth().length() > 2) {
                        embed = new EmbedBuilder()
                                .setTitle("You've created a new event")
                                .addField("Name: " + calendar[1], "On " + tracker.getMonth() + " " + tracker.getDay() + ", " + tracker.getYear())
                                .setColor(Color.PINK)
                                .setFooter("At " + tracker.getTime() + tracker.getAM_PM());
                    }else {
                        embed = new EmbedBuilder()
                                .setTitle("You've created a new event")
                                .addField("Name: " + calendar[1], "On " + new DateFormatSymbols().getMonths()[Integer.parseInt(tracker.getMonth()) - 1] + " " + tracker.getDay() + ", " + tracker.getYear())
                                .setColor(Color.PINK)
                                .setFooter("At " + tracker.getTime() + tracker.getAM_PM());
                    }
                    event.getChannel().sendMessage(embed);

                    tracker.month = validateMonth(tracker.getMonth());
                    CalendarQuickstart.Schedule(tracker.getName(), tracker.getYear(), tracker.getMonth(), tracker.getDay(), tracker.civilianToMilitary()); // switch this to tracker.getYear etc etc.

                } catch(ArrayIndexOutOfBoundsException f) {
                    try {
                        TimesTracker tracker = new TimesTracker(calendar[1], calendar[2], calendar[3], calendar[4]);
                        EmbedBuilder embed = new EmbedBuilder()
                                .setTitle("You've created a new event")
                                .addField("Name: " + calendar[1], "On " + new DateFormatSymbols().getMonths()[Integer.parseInt(tracker.getMonth()) - 1] + " " + tracker.getDay() + " " + tracker.getYear())
                                .setColor(Color.PINK)
                                .setFooter("At " + tracker.getTime() + tracker.getAM_PM());
                        event.getChannel().sendMessage(embed);
                        CalendarQuickstart.Schedule(tracker.getName(), tracker.getYear(), tracker.getMonth(), tracker.getDay(), tracker.civilianToMilitary()); // switch this to tracker.getYear etc etc.
                    }catch (ArrayIndexOutOfBoundsException s){
                        event.getChannel().sendMessage("Something went wrong. Try again or type \"!help\" for help.");
                    }
                }

            }catch(StringIndexOutOfBoundsException | IOException | GeneralSecurityException e) {
                event.getChannel().sendMessage("Something went wrong! Try again, or type \"help\" for help.");
            }

        }
    }
}
