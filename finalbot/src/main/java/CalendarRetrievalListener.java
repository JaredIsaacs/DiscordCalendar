import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.DateFormatSymbols;
import java.util.List;;

public class CalendarRetrievalListener implements MessageCreateListener {


    @Override
    public void onMessageCreate(MessageCreateEvent event) { //retrieves a calendar and displays 10 events.
         if(event.getMessageContent().startsWith("!events")) {
            try {
                List<Event> items = CalendarQuickstart.retrieveSchedule();

                if (items.isEmpty()) {
                    event.getChannel().sendMessage("No upcoming events found.");
                } else {
                    event.getChannel().sendMessage("Upcoming Events");
                    for (Event events : items) {
                        DateTime start = events.getStart().getDateTime();
                        if (start == null) {
                            start = events.getStart().getDate();
                        }
                        String[] temp = start.toString().split("[-T:]");
                        EmbedBuilder embed = new EmbedBuilder()
                                .setTitle(events.getSummary())
                                .setFooter("Year: " + temp[0] + "\nMonth: " + new DateFormatSymbols().getMonths()[Integer.parseInt(temp[1]) - 1] + "\nDay: " + temp[2] + "\nTime: " + CalendarListener.militaryToCivilian(temp[3], temp[4]))
                                .setColor(Color.PINK);
                        event.getChannel().sendMessage(embed);
                    }
                }
            } catch (GeneralSecurityException | IOException e) {
                event.getChannel().sendMessage("Something went wrong! Try again, or type \"help\" for help.");
            }
        }
    }
}
