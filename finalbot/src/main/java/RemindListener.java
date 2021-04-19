import com.google.api.services.calendar.model.Event;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RemindListener implements MessageCreateListener {


    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if(event.getMessageContent().toLowerCase().startsWith("!remindme")){
            try {
                DateTime start = new DateTime();
                String[] input = event.getMessageContent().substring(10).split(" ");
                List<Event> items = CalendarQuickstart.retrieveSchedule();
                for(Event events : items){
                    if(events.getSummary().equals(input[0])){
                        DateTime end = new DateTime(events.getStart().getDateTime().toString());
                        event.getMessageAuthor().asUser().get().sendMessage("You will be notified five minutes before " + input[0] + " starts.");
                        Runnable test = () -> event.getMessageAuthor().asUser().get().sendMessage(input[0] + " is starting in five minutes.");
                        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor(); // creates a new thread in order to remind user.
                        executorService.schedule(test, (new Interval(start, end).toDuration().getStandardMinutes()) - 5, TimeUnit.MINUTES);
                    }
                }
            } catch (GeneralSecurityException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
