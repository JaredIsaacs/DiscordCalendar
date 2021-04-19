import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.io.IOException;
import java.security.GeneralSecurityException;

import java.util.List;


public class AddAttendeeListener implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageContent().toLowerCase().startsWith("!join")){
            String [] input = event.getMessageContent().substring(6).split(" ");
            EventAttendee attendee = new EventAttendee().setEmail(input[1]);
            try {
                List<Event> items = CalendarQuickstart.retrieveSchedule();
                for(Event events : items){
                    if(events.getSummary().equalsIgnoreCase(input[0])){
                        CalendarQuickstart.addAttendee(attendee, events.getId());
                        event.getChannel().sendMessage("You've signed up of \"" + input[0] + "\"");
                    }
                }
            } catch (GeneralSecurityException | IOException e) {
                e.printStackTrace();
            }

        }
    }
}